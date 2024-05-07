package com.chat_project.chat

import com.chat_project.web.chat.entity.Chat
import com.chat_project.web.chat.repository.ChatRepository
import com.chat_project.web.member.entity.Member
import com.chat_project.web.member.repository.MemberRepository
import jakarta.transaction.Transactional
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ChatTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var chatRepository: ChatRepository

    @Test
    @DisplayName("채팅 생성")
    @Transactional
    fun createChat() {
        var member: Member = memberRepository.findByEmail("test")
        val chat: Chat = Chat( "test", member)
        chatRepository.save(chat)
    }

    @Test
    @DisplayName("채팅 조회")
    fun selectChat() {

    }

}