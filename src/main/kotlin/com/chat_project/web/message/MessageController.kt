package com.chat_project.web.message

import com.chat_project.common.logger
import com.chat_project.security.TokenProvider
import com.chat_project.web.chat.dto.ChatDTO
import com.chat_project.web.member.entity.Member
import com.chat_project.web.member.repository.MemberRepository
import jakarta.servlet.http.HttpServletRequest
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestHeader

@Controller
class MessageController(
    private val tokenProvider: TokenProvider,
    private val memberRepository: MemberRepository,
    private val messageService: MessageService
) {
    val logger = logger()

    @MessageMapping("/enter")
    fun enter(chatDTO: ChatDTO) {
        logger.info("채팅 입장")
    }

    @MessageMapping("/message")
    fun message(chatDTO: ChatDTO) {
        logger.info("채팅 발송")
//        val email: String? = tokenProvider.parseBearerToken(chatDTO.token!!)
//            .also { tokenProvider.parseTokenInfo(it) }
//            .also { it!!.split(":")[0] }//
        val email: String = tokenProvider.parseTokenInfo(chatDTO.token).username
        val member: Member? = email.let { memberRepository.findByEmail(it) }
        messageService.sendMessage(chatDTO, member)
    }

}