package com.chat_project.web.member.entity

import com.chat_project.web.chat.entity.Chat
import com.chat_project.web.common.BaseEntity
import com.chat_project.web.common.Role
import com.chat_project.web.member.dto.MemberDTO
import jakarta.persistence.*

@Entity
class Member (
    var email: String,
    var password: String,
    var nickname: String,
    @Enumerated(EnumType.STRING)
    var role: Role,
    @OneToMany(mappedBy = "member")
    var chattings: MutableList<Chat> = ArrayList()
): BaseEntity() {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    var id: Long? = null

    fun updateMemberInfo(memberDTO: MemberDTO) {
        email = memberDTO.email
        password = memberDTO.password
        nickname = memberDTO.nickname
    }
}