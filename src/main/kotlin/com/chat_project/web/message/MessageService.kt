package com.chat_project.web.message

import com.chat_project.exception.CustomException
import com.chat_project.exception.CustomExceptionCode
import com.chat_project.web.chat.dto.ChatDTO
import com.chat_project.web.chat.dto.ChatRoomDTO
import com.chat_project.web.chat.entity.Chat
import com.chat_project.web.chat.repository.chat.ChatRepository
import com.chat_project.web.chat.repository.chatRoom.ChatRoomRepository
import com.chat_project.web.member.entity.Member
import com.fasterxml.jackson.databind.ObjectMapper
import org.modelmapper.ModelMapper
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [ Exception::class ])
class MessageService(
    private val chatRoomRepository: ChatRoomRepository,
    private val chatRepository: ChatRepository,
    private val channelTopic: ChannelTopic,
    private val redisTemplate: StringRedisTemplate,
    private val modelMapper: ModelMapper
) {

    fun sendMessage(chatDTO: ChatDTO, member: Member?) {
        val chatRoom = chatDTO.chatRoomId
            ?.let { chatRoomRepository.findById(it).get() }
            ?: throw CustomException(CustomExceptionCode.CHAT_ROOM_NOT_FOUND)
        val chat: Chat = Chat(chatDTO.content, member, chatRoom)
        chatRepository.save(chat)

        val chatDTO: ChatDTO = modelMapper.map(chat, ChatDTO::class.java)

        redisTemplate.valueSerializer = Jackson2JsonRedisSerializer(String::class.java)
        redisTemplate.convertAndSend(channelTopic.topic, chatDTO)

    }

}