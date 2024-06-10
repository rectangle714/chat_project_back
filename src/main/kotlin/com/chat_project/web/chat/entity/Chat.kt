package com.chat_project.web.chat.entity

import com.chat_project.web.common.Base
import com.chat_project.web.member.entity.Member
import jakarta.persistence.*

@Entity
class Chat(
    content: String,
    member: Member
): Base() {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Long? = null
    var content = content
        protected set
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member = member
        protected set

}