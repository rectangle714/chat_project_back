package com.chat_project.web.chat.controller

import com.chat_project.web.chat.dto.ChatDTO
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chat")
@Tag(name = "Chatting API", description = "채팅 관련 API")
class ChatController {

    @ResponseBody
    @PostMapping("/chat")
    fun chat(chat: ChatDTO) : ChatDTO {

        return chat;
    }
}