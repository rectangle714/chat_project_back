package com.chat_project.web.member.service

import com.chat_project.web.member.dto.MemberDTO
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

interface MemberService {

    fun login(email: String, password: String): MemberDTO
    fun insertMember(memberDTO: MemberDTO): String
    fun updateMember(memberDTO: MemberDTO): String


}