package com.chat_project.web.member.dto

import com.chat_project.web.common.Role

data class MemberDTO (
    var memberId: Long = 0,
    var email: String = "",
    var password: String = "",
    var nickname: String = "",
    var role: Role = Role.USER
)