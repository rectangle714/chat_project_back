package com.chat_project.web.member.service

import com.chat_project.common.logger
import com.chat_project.web.member.dto.MemberDTO
import com.chat_project.web.member.entity.Member
import com.chat_project.web.member.repository.MemberRepository
import org.modelmapper.ModelMapper
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: BCryptPasswordEncoder,
    private val modelMapper: ModelMapper,
): MemberService {
    val logger = logger()

    @Transactional(readOnly = true)
    override fun login(email: String, password: String): MemberDTO {
        val member: Member = memberRepository.findByEmail(email)
            .takeIf { passwordEncoder.matches(password, it.password) }
            ?: throw IllegalArgumentException("아이디 또는 번호가 일치하지 않습니다.")

        return modelMapper.map(member, MemberDTO::class.java)
    }

    override fun insertMember(memberDTO: MemberDTO): String {
        val member: Member = Member.from(memberDTO, passwordEncoder)
        memberRepository.save(member)
        return "success"
    }

    override fun updateMember(memberDTO: MemberDTO): String {
        val member = memberRepository.findByEmail(memberDTO.email)
            .takeIf { passwordEncoder.matches(memberDTO.password, it?.password) }
        member?.update(memberDTO, passwordEncoder)
        memberRepository.flush()
        return "success"
    }
}