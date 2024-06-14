package com.chat_project.web.member.service

import com.chat_project.web.member.dto.MemberDTO
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

interface MemberService {

    fun login(email: String, password: String): MemberDTO
    fun signUp(memberDTO: MemberDTO): String

    fun info(email: String): MemberDTO
    fun update(memberDTO: MemberDTO): String




}