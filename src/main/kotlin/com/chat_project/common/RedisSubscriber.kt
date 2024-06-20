package com.chat_project.common

import com.chat_project.web.chat.dto.ChatDTO
import com.chat_project.web.chat.dto.ChatRoomDTO
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Service

@Service
class RedisSubscriber(
    val objectMapper: ObjectMapper,
    val redisTemplate: StringRedisTemplate,
    val messagingTemplate: SimpMessageSendingOperations
): MessageListener {

    val logger = logger()

    override fun onMessage(message: Message, pattern: ByteArray?) {
        try {
            val publishMessage: String = redisTemplate.stringSerializer.deserialize(message.body)
            val chatRoomDTO: ChatRoomDTO = objectMapper.readValue(publishMessage, ChatRoomDTO::class.java)

            messagingTemplate.convertAndSend("/sub/chat/room/"+ chatRoomDTO.chatRoomId)

        } catch(e: Exception) {
            logger.error("채팅 에러 발생 : {}",e.message)
        }
    }

}