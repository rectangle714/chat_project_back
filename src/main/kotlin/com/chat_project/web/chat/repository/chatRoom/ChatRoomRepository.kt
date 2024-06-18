package com.chat_project.web.chat.repository.chatRoom

import com.chat_project.web.chat.entity.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRoomRepository: JpaRepository<ChatRoom, Long> {

}