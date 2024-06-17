package com.chat_project.web.member.service

import com.chat_project.web.member.dto.MemberDTO
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

interface MemberService {

    fun login(email: String, password: String): String
    fun logout(email: String): String
    fun signUp(memberDTO: MemberDTO): String

    fun info(email: String): MemberDTO
    fun update(memberDTO: MemberDTO): String
    fun delete(email: String): String




}