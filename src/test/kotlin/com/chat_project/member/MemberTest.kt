package com.chat_project.member

import com.chat_project.web.common.Role
import com.chat_project.web.member.repository.MemberRepository
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Test
    fun createMember() {

    }

}