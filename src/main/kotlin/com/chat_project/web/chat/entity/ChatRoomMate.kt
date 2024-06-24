package com.chat_project.web.chat.entity

import com.chat_project.common.BaseEntity
import com.chat_project.web.member.entity.Member
import jakarta.persistence.*

@Entity
class ChatRoomMate(
    member:Member?,
    chatRoom:ChatRoom
): BaseEntity() {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_mate_id")
    var id:Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member = member

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id")
    var chatRoom = chatRoom
}