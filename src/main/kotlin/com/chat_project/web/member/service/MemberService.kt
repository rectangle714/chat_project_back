package com.chat_project.web.member.service

import com.chat_project.web.member.dto.MemberDTO
import org.springframework.stereotype.Service

interface MemberService {

    fun insertMember(memberDTO: MemberDTO): String
    fun updateMember(memberDTO: MemberDTO): String

}