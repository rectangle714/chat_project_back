package com.chat_project.web.security

import com.chat_project.web.common.Role
import com.chat_project.web.member.dto.MemberDTO
import com.chat_project.web.member.entity.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder

class CustomUserDetails private constructor(
    private val email: String,
    private val password: String,
    private val nickname: String,
    private val role: Role
): UserDetails  {
    companion object {
        fun from(member: Member) : CustomUserDetails {
            return with(member) {
                CustomUserDetails(
                    email = member.email,
                    password =  member.password,
                    nickname = member.nickname,
                    role = member.role
                )
            }
        }
    }

    override fun getUsername(): String {
        return email
    }

    override fun getPassword(): String {
        return password
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        TODO("Not yet implemented")
    }

    override fun isAccountNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isAccountNonLocked(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isCredentialsNonExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEnabled(): Boolean {
        TODO("Not yet implemented")
    }
}
