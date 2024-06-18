package com.chat_project.web.chat.dto

import java.time.LocalDateTime

data class ChatDTO(

    val chatId: String,
    val memberId: String,
    val content: String,
    val registerDate: LocalDateTime,
    val updateDate: LocalDateTime

)