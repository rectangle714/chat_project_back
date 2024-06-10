package com.chat_project.web.config

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
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http.httpBasic(HttpBasicConfigurer<HttpSecurity>::disable)
            .csrf(CsrfConfigurer<HttpSecurity>::disable)
            .sessionManagement(SessionManagementConfigurer<HttpSecurity>::disable)
        http.authorizeHttpRequests{
            it.requestMatchers("/api-docs/**","/swagger-ui/**").permitAll()
            it.requestMatchers("/member/login").permitAll()
            it.requestMatchers("/manager/**").hasAnyRole("MANAGER","ADMIN")
            it.requestMatchers("/admin/**").hasRole("ADMIN")
            it.anyRequest().authenticated()
        }
        return http.build()
    }

}