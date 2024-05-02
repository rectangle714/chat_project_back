package com.chat_project.web.member.dto

data class MemberDTO(
    val memberId : String,
    val email : String,
    val password : String,
    val nickname : String
)