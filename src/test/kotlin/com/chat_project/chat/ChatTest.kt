package com.chat_project.chat

import com.chat_project.web.chat.entity.Chat
import com.chat_project.web.chat.repository.chat.ChatRepository
import com.chat_project.web.member.entity.Member
import com.chat_project.web.member.repository.MemberRepository
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
class ChatTest {
    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var chatRepository: ChatRepository

    @Test
    @DisplayName("채팅 생성")
    @Transactional
    fun createChat() {
        var member: Member? = memberRepository.findByEmail("test")
        val chat: Chat? = member?.let { Chat( "test", it) }
        chatRepository.save(chat)
    }

    @Test
    @DisplayName("채팅 조회")
    @Transactional(readOnly = true)
    fun selectChat() {
        val member: Member? = memberRepository.findByEmail("test")
        val chatList: List<Chat>? = member?.let { chatRepository.findByMemberEmail(it.email) }
    }

}