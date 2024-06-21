package com.chat_project.web.chat.dto

import java.io.Serializable
import java.time.LocalDateTime

data class ChatDTO(

    val chatId: Long? = 0,
    val chatRoomId: Long? = 0,
    val memberId: Long? = 0,
    val content: String = "",
    val message: String? = "",
    val token: String? = "",

    )