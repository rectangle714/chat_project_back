package com.chat_project.web.member.entity

import com.chat_project.web.chat.entity.Chat
import com.chat_project.web.common.Base
import com.chat_project.web.common.Role
import com.chat_project.web.member.dto.MemberDTO
import groovy.transform.builder.Builder
import jakarta.persistence.*

@Entity
@Builder
class Member (
    email: String,
    password: String,
    nickname: String,
    role: Role,
    chattings: MutableList<Chat> = ArrayList()
): Base() {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var id:Long = 0
    var email = email
        protected set
    var password = password
        protected set
    var nickname = nickname
        protected set
    @Enumerated(EnumType.STRING)
    var role = role
        protected set
    @OneToMany(mappedBy = "member")
    var chattings= chattings
        protected set

    fun updateMemberInfo(memberDTO: MemberDTO) {
        email = memberDTO.email
        password = memberDTO.password
        nickname = memberDTO.nickname
    }

}