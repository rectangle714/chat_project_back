package com.chat_project.member

import com.chat_project.web.chat.repository.ChatRepository
import com.chat_project.web.common.Role
import com.chat_project.web.member.entity.Member
import com.chat_project.web.member.repository.MemberRepository
import lombok.extern.slf4j.Slf4j
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.password.PasswordEncoder

@Slf4j
@SpringBootTest
class MemberTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var chatRepository: ChatRepository

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Test
    @DisplayName("사용자 생성")
    fun createMember() {
        val password: String = passwordEncoder.encode("123")
        val newMember: Member = Member(null, "test", password, "nickname", Role.USER)
        memberRepository.save(newMember)
    }

    @Test
    @DisplayName("사용자 조회")
    fun selectMember() {
        val findByEmail = memberRepository.findByEmail("test")
        println("사용자 정보 ${findByEmail.email}");
    }

}