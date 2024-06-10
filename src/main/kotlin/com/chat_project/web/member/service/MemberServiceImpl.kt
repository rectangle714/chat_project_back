package com.chat_project.web.member.service

import com.chat_project.web.common.logger
import com.chat_project.web.member.dto.MemberDTO
import com.chat_project.web.member.repository.MemberRepository
import com.chat_project.web.member.service.MemberService
import org.springframework.stereotype.Service


@Service
class MemberServiceImpl(
    private val memberRepository: MemberRepository
): MemberService {

    val logger = logger()

    override fun signUp(memberDTO: MemberDTO): String {
        logger.info("회원가입 테스트")
        return "success"
    }

}