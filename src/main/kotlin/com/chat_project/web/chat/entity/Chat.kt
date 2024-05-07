package com.chat_project.web.chat.entity

import com.chat_project.web.common.BaseEntity
import com.chat_project.web.member.entity.Member
import jakarta.persistence.*

@Entity
class Chat(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id:Long? = null,
    var content: String,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member
): BaseEntity()