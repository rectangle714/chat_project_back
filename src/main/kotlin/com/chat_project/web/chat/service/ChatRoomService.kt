package com.chat_project.web.chat.service

import com.chat_project.web.chat.entity.ChatRoom
import com.chat_project.web.chat.repository.chatRoom.ChatRoomRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [ Exception::class ])
class ChatRoomService(
    private val chatRoomRepository: ChatRoomRepository
) {

    @Transactional(readOnly = true)
    fun getChatRoomList(): MutableList<ChatRoom> {
        return chatRoomRepository.findAll()
    }

}