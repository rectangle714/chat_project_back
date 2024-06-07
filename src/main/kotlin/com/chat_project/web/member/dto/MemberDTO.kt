package com.chat_project.web.member.dto

import com.chat_project.web.common.Role
import java.time.LocalDateTime

data class MemberDTO (
    var memberId: String = "",
    var email: String = "",
    var password: String = "",
    var nickname: String = "",
    var role: Role = Role.USER
)