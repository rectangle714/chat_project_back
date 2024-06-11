package com.chat_project.web.member.service

import com.chat_project.common.logger
import com.chat_project.web.member.dto.MemberDTO
import com.chat_project.web.member.entity.Member
import com.chat_project.web.member.repository.MemberRepository
import com.chat_project.web.member.service.MemberService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class MemberServiceImpl(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: BCryptPasswordEncoder
): MemberService {

    val logger = logger()

    override fun insertMember(memberDTO: MemberDTO): String {
        return try {
            val member: Member = Member.from(memberDTO, passwordEncoder)
            memberRepository.save(member)
            "success"
        } catch (e: Exception) {
            "fail"
        }
    }

    override fun updateMember(memberDTO: MemberDTO): String {
        return try {
            val member = memberRepository.findByEmail(memberDTO.email)
                .takeIf { passwordEncoder.matches(memberDTO.password, it.password) }
            member?.update(memberDTO, passwordEncoder)
            memberRepository.flush()
            "success"
        } catch (e: Exception) {
            logger.error(e.message)
            "fail"
        }
    }
}