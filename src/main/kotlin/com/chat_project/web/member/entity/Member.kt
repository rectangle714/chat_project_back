package com.chat_project.web.member.entity

import com.chat_project.web.common.Role
import jakarta.persistence.*

@Entity
class Member(
    email: String,
    password: String,
    role: Role
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", )
    var id: Long = 0L
    var email: String = email
    var password: String = password
    var name: String = ""
    var nickname: String = ""
    @Enumerated(EnumType.STRING)
    var role: Role = role
}