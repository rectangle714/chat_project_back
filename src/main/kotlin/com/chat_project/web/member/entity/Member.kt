package com.chat_project.web.member.entity

import com.chat_project.web.chat.entity.Chat
import com.chat_project.web.common.BaseEntity
import com.chat_project.web.common.Role
import com.chat_project.web.member.dto.MemberDTO
import jakarta.persistence.*

@Entity
class Member (
    email: String,
    password: String,
    nickname: String,
    role: Role,
    chattings: MutableList<Chat> = ArrayList()
): BaseEntity() {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var id: Long? = null
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