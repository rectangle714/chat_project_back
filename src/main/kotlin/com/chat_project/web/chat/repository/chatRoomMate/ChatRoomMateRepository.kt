package com.chat_project.web.chat.repository.chatRoomMate

import com.chat_project.web.chat.entity.ChatRoomMate
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRoomMateRepository: JpaRepository<ChatRoomMate, Long> {

}