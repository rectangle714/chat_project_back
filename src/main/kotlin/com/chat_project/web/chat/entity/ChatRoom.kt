package com.chat_project.web.chat.entity

import com.chat_project.common.BaseEntity
import jakarta.persistence.*

@Entity
class ChatRoom(
    roomName: String
) : BaseEntity() {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chat_room_id")
    var id:Long? = null

    var roomName = roomName
        protected set

    @OneToMany(mappedBy = "chatRoom")
    var chat:MutableList<Chat> = ArrayList()
        protected set
}