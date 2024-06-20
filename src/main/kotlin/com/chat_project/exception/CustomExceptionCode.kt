package com.chat_project.exception

import org.springframework.http.HttpStatus

enum class CustomExceptionCode(
    val status: Int,
    val message: String,
) {
    CHAT_ROOM_NOT_FOUND(HttpStatus.BAD_REQUEST.value(), "해당 채팅방을 찾을 수 없습니다."),
    NOT_FOUND_MEMBER(HttpStatus.BAD_REQUEST.value(),"사용자를 찾을 수 없습니다."),
    BAD_TOKEN_INFO(HttpStatus.BAD_REQUEST.value(),"유효하지 않은 토큰입니다."),
    BAD_REFRESH_TOKEN_INFO(HttpStatus.BAD_REQUEST.value(), "잘못된 refresh 토큰 정보 입니다.")
}