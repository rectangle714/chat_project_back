package com.chat_project.web.member.entity

import com.chat_project.web.chat.entity.Chat
import com.chat_project.web.chat.entity.ChatRoomMate
import com.chat_project.common.BaseEntity
import com.chat_project.common.constant.Role
import com.chat_project.web.member.dto.MemberDTO
import groovy.transform.builder.Builder
import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Entity
@Builder
class Member (
    email: String = "",
    password: String = "",
    nickname: String = "",
    role: Role = Role.USER
): BaseEntity() {
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
    var chattings:MutableList<Chat> = ArrayList()
        protected set

    @OneToMany(mappedBy = "member")
    var chatRoomMates:MutableList<ChatRoomMate> = ArrayList()
        protected set

    companion object{
        fun from(memberDTO: MemberDTO, encoder: BCryptPasswordEncoder) = Member(
            email = memberDTO.email,
            password = encoder.encode(memberDTO.password),
            nickname = memberDTO.nickname,
            role = memberDTO.role
        )
    }

    fun update(newMember: MemberDTO, encoder: BCryptPasswordEncoder) {
        this.email = newMember.email
        this.password = encoder.encode(newMember.password)
        this.nickname = newMember.nickname
    }

}