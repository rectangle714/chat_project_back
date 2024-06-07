package com.chat_project.web.chat.repository

import com.chat_project.web.chat.entity.Chat
import com.chat_project.web.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRepository: JpaRepository<Chat, Long> {
    fun findByMember(member:Member): List<Chat>;

}