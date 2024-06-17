package com.chat_project.web.member.service

import com.chat_project.common.logger
import com.chat_project.common.util.RedisUtil
import com.chat_project.exception.CustomException
import com.chat_project.exception.CustomExceptionCode
import com.chat_project.security.TokenProvider
import com.chat_project.web.member.dto.MemberDTO
import com.chat_project.web.member.entity.Member
import com.chat_project.web.member.repository.MemberRepository
import org.modelmapper.ModelMapper
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional(rollbackFor = [Exception::class])
class MemberServiceImpl(
    @Value("\${expiration-hours}")
    private val expirationHours: Long,
    private val memberRepository: MemberRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val modelMapper: ModelMapper,
    private val tokenProvider: TokenProvider,
    private val redisUtil: RedisUtil
): MemberService {
    val logger = logger()

    @Transactional(readOnly = true)
    override fun login(email: String, password: String): String {
        val member: Member = memberRepository.findByEmail(email)
            .takeIf { passwordEncoder.matches(password, it?.password) }
            ?: throw IllegalArgumentException("아이디 또는 번호가 일치하지 않습니다.")

        val token = tokenProvider.createToken("${member.email}:${member.role}")
        redisUtil.setData(member.email, token, expirationHours)
        return token
    }

    override fun logout(email: String): String {
        redisUtil.hasKey(email).takeIf { redisUtil.deleteData(email) }
        return "success"
    }

    override fun signUp(memberDTO: MemberDTO): String {
        val member: Member = Member.from(memberDTO, passwordEncoder)
        memberRepository.save(member)
        return "success"
    }

    override fun update(memberDTO: MemberDTO): String {
        val member = memberRepository.findByEmail(memberDTO.email)
            .takeIf { passwordEncoder.matches(memberDTO.password, it?.password) }
            ?: throw CustomException(CustomExceptionCode.NOT_FOUND_MEMBER)
        member.update(memberDTO, passwordEncoder)
        memberRepository.flush()
        return "success"
    }

    @Transactional(readOnly = true)
    override fun info(email: String): MemberDTO {
        val member: Member = memberRepository.findByEmail(email)
            ?: throw CustomException(CustomExceptionCode.NOT_FOUND_MEMBER)
        return modelMapper.map(member, MemberDTO::class.java).also { it.password = "" }
    }

    override fun delete(email: String): String {
        val member: Member = memberRepository.findByEmail(email)
            ?: throw CustomException(CustomExceptionCode.NOT_FOUND_MEMBER)
        memberRepository.delete(member)
        return "success"
    }

}