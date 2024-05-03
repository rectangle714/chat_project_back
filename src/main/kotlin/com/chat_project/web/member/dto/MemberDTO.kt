package com.chat_project.web.member.dto

import com.chat_project.web.common.Role

data class MemberDTO (
    val memberId: String,
    val email: String,
    val password: String,
    val nickname: String,
    val role: Role
)