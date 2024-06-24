package com.chat_project.web.message

import com.chat_project.common.constant.ChatType
import com.chat_project.common.logger
import com.chat_project.exception.CustomException
import com.chat_project.exception.CustomExceptionCode
import com.chat_project.web.chat.dto.ChatDTO
import com.chat_project.web.chat.dto.ChatRoomDTO
import com.chat_project.web.chat.entity.Chat
import com.chat_project.web.chat.entity.ChatRoomMate
import com.chat_project.web.chat.repository.chat.ChatRepository
import com.chat_project.web.chat.repository.chatRoom.ChatRoomRepository
import com.chat_project.web.chat.repository.chatRoomMate.ChatRoomMateRepository
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
    private val chatRepository: ChatRepository,
    private val chatRoomRepository: ChatRoomRepository,
    private val chatRoomMateRepository: ChatRoomMateRepository,
    private val channelTopic: ChannelTopic,
    private val redisTemplate: StringRedisTemplate,
    private val modelMapper: ModelMapper
) {

    fun sendMessage(chatDTO: ChatDTO, member: Member?) {
        val chatRoom = chatDTO.chatRoomId
            ?.let { chatRoomRepository.findById(it).get() }
            ?: throw CustomException(CustomExceptionCode.CHAT_ROOM_NOT_FOUND)
        val chat: Chat = Chat(chatDTO.message, member, chatRoom)

        if(chatDTO.chatType == ChatType.ENTER.name) {
            logger().info("채팅방 입장")
            val chatRoomMate =ChatRoomMate(member, chatRoom)
            chatRoomMateRepository.save(chatRoomMate)
            chatDTO.message = chatDTO.sender + "님이 입장했습니다."
        } else {
            logger().info("채팅 발송")
        }
        chatRepository.save(chat)

        val chatDTO: ChatDTO = modelMapper.map(chat, ChatDTO::class.java)

        redisTemplate.valueSerializer = Jackson2JsonRedisSerializer(String::class.java)
        redisTemplate.convertAndSend(channelTopic.topic, chatDTO)
    }

}