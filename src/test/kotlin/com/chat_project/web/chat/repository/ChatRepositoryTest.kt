package com.chat_project.web.chat.repository

import com.chat_project.config.QuerydslConfig
import com.chat_project.web.chat.dto.ChatRoomDTO
import com.chat_project.web.chat.entity.Chat
import com.chat_project.web.chat.repository.chat.ChatRepository
import com.chat_project.web.chat.repository.chatRoom.ChatRoomRepository
import com.chat_project.web.member.entity.Member
import com.chat_project.web.member.repository.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import

@DataJpaTest
@Import(QuerydslConfig::class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ChatRepositoryTest(
    @Autowired private val memberRepository: MemberRepository,
    @Autowired private val chatRepository: ChatRepository,
    @Autowired private var chatRoomRepository: ChatRoomRepository
) {
    @Test
    fun 채팅입력() {
        var member: Member? = memberRepository.findByEmail("test")
//        val chat: Chat? = member?.let { Chat( "test", it) }
//        chatRepository.save(chat)
    }

    @Test
    fun 채팅조회() {
        val member: Member? = memberRepository.findByEmail("test")
        val chatList: List<Chat>? = member?.let { chatRepository.findByMemberEmail(it.email) }
    }

    @Test
    fun 채팅방_전체조회() {
        val chatroomList: List<ChatRoomDTO> = chatRoomRepository.getChatroomList()
        Assertions.assertEquals(chatroomList.size, 1);
    }

}