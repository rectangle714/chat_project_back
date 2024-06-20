package com.chat_project.web.chat.dto

import java.time.LocalDateTime

data class ChatRoomDTO(

    var chatRoomId: Long?,
    var chatRoomName: String,
    var registerDate: LocalDateTime?,
    var updateDate: LocalDateTime?

)
