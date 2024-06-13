package com.chat_project.web.member.dto

import com.chat_project.web.member.Role
import java.time.LocalDateTime

data class MemberDTO (
    var memberId: Long = 0,
    var email: String = "",
    var password: String = "",
    var nickname: String = "",
    var role: Role = Role.USER,
    var registerDate: LocalDateTime,
    var updateDate: LocalDateTime
)