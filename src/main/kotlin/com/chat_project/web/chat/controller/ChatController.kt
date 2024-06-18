package com.chat_project.web.chat.controller

import com.chat_project.web.chat.dto.ChatDTO
import com.chat_project.web.chat.entity.Chat
import com.chat_project.web.chat.service.ChatService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chat")
@Tag(name = "채팅 API", description = "채팅 관련 전체 API")
class ChatController(
    private val chatService: ChatService
) {

    @GetMapping("/list")
    @Operation(method = "GET", summary = "채팅 목록 조회")
    fun list(chatRoomId: Long): MutableList<Chat>
        = chatService.getChatList(chatRoomId)

    @PostMapping("/add")
    @Operation(method = "POST", summary = "채팅 입력")
    fun add(@AuthenticationPrincipal user: User, chatDTO: ChatDTO): ResponseEntity<String> {
        chatService.addChat(user, chatDTO)
        return ResponseEntity.ok("success")
    }

}