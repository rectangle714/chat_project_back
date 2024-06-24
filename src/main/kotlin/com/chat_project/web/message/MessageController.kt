package com.chat_project.web.message

import com.chat_project.common.constant.ChatType
import com.chat_project.common.logger
import com.chat_project.security.TokenProvider
import com.chat_project.web.chat.dto.ChatDTO
import com.chat_project.web.chat.entity.ChatRoomMate
import com.chat_project.web.chat.repository.chatRoomMate.ChatRoomMateRepository
import com.chat_project.web.member.entity.Member
import com.chat_project.web.member.repository.MemberRepository
import jakarta.servlet.http.HttpServletRequest
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.security.core.Authentication
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestHeader

@Controller
class MessageController(
    private val tokenProvider: TokenProvider,
    private val memberRepository: MemberRepository,
    private val messageService: MessageService
    ) {


    @MessageMapping("/message")
    fun message(chatDTO: ChatDTO) {
        val member: Member? = chatDTO.sender.let { memberRepository.findByEmail(it) }
        messageService.sendMessage(chatDTO, member)
    }

}