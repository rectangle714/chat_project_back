package com.chat_project.web.security

import com.chat_project.web.member.repository.MemberRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService() : UserDetailsService {
    @Autowired
    lateinit var memberRepository: MemberRepository

    override fun loadUserByUsername(username: String): UserDetails {
        return memberRepository.findByEmail(username).let { CustomUserDetails.from(it) }
    }
}