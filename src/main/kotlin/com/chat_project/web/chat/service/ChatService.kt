package com.chat_project.web.chat.service

import com.chat_project.exception.CustomException
import com.chat_project.exception.CustomExceptionCode
import com.chat_project.web.chat.dto.ChatDTO
import com.chat_project.web.chat.dto.ChatRoomDTO
import com.chat_project.web.chat.entity.Chat
import com.chat_project.web.chat.entity.ChatRoom
import com.chat_project.web.chat.repository.chat.ChatRepository
import com.chat_project.web.chat.repository.chatRoom.ChatRoomRepository
import com.chat_project.web.member.entity.Member
import com.chat_project.web.member.repository.MemberRepository
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [ Exception::class ])
class ChatService(
    private val memberRepository: MemberRepository,
    private val chatRepository: ChatRepository,
    private val chatRoomRepository: ChatRoomRepository,
    private val channelTopic: ChannelTopic,
    private val redisTemplate: StringRedisTemplate
) {

    fun sendMessage(chatRoomDTO: ChatRoomDTO, member: Member?) {
        val chatRoom = chatRoomDTO.chatRoomId
            ?.let { chatRoomRepository.findById(it).get() }
            ?: throw CustomException(CustomExceptionCode.CHAT_ROOM_NOT_FOUND)
        val chat = Chat("", member, chatRoom)

        chatRepository.save(chat)
        val topic = channelTopic.topic

        //그룹 채팅일 경우
        redisTemplate.convertAndSend(topic, chatRoomDTO)

    }

    @Transactional(readOnly = true)
    fun getChatList(chatRoomId: Long): MutableList<Chat> {
        return chatRepository.findByChatRoomId(chatRoomId)
    }

    fun addChat(user: User, chatDTO: ChatDTO) {
        val member = memberRepository.findByEmail(user.username)
    }

}