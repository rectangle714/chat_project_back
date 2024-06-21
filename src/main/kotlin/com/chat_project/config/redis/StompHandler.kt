package com.chat_project.config.redis

import com.chat_project.common.logger
import com.chat_project.security.TokenProvider
import com.chat_project.web.member.entity.Member
import com.chat_project.web.member.repository.MemberRepository
import com.chat_project.web.security.CustomUserDetails
import org.springframework.messaging.Message
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.simp.stomp.StompCommand
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.messaging.support.ChannelInterceptor
import org.springframework.stereotype.Component

@Component
class StompHandler(
    private val tokenProvider: TokenProvider,
    private val memberRepository: MemberRepository
): ChannelInterceptor {

    val logger = logger()

    /* websocket 요청 전 처리 */
    override fun preSend(message: Message<*>, channel: MessageChannel): Message<*>? {
        val accessor = StompHeaderAccessor.wrap(message)

        if(StompCommand.CONNECT == accessor.command) {
            logger.info("socket connect")
            val token: String? = accessor.getFirstNativeHeader("Authorization")
            val email: String = tokenProvider.parseTokenInfo(token).username

            val member: Member? = memberRepository.findByEmail(email)
        } else if(StompCommand.DISCONNECT == accessor.command) {
            logger.info("socket disconnect")
        }

        return message
    }

}