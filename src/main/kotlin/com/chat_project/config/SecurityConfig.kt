package com.chat_project.config

import org.codehaus.groovy.vmplugin.v8.Java8
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.HttpSecurityBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class SecurityConfig {

    /* 접근 허용하는 uri 배열 */
    private val allowedUrls = arrayOf("/api-docs/**","/swagger-ui/**","/member/signUp", "/member/login")

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity) {
        http.httpBasic{ it.disable() }
            .csrf { it.disable() }
            .headers { it.frameOptions { frameOptions -> frameOptions.disable() } }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

        http.authorizeHttpRequests {
            it.requestMatchers(*allowedUrls).permitAll()
            it.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
            it.requestMatchers("/admin/**").hasRole("ADMIN")
            it.anyRequest().authenticated()
        }

        http.build()!!
    }

}