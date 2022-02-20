package com.server.gmt.security

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfigurer : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var userDetailsServiceImplementation: UserDetailsServiceImplementation

    @Autowired
    private lateinit var jwtRequestFilter: JWTRequestFilter

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth!!.userDetailsService(userDetailsServiceImplementation).passwordEncoder(passwordEncoder())
    }

    override fun configure(http: HttpSecurity?) {
        http!!.csrf().disable().authorizeRequests().antMatchers("/login").permitAll()
            .antMatchers("/").permitAll()
            .antMatchers("/swagger-ui/index.html").permitAll()
            .antMatchers("/swagger-ui/favicon-16x16.png").permitAll()
            .antMatchers("/swagger-ui/favicon-32x32.png").permitAll()
            .antMatchers("/swagger-ui/swagger-ui.css").permitAll()
            .antMatchers("/swagger-ui/swagger-ui-bundle.js").permitAll()
            .antMatchers("/swagger-ui/swagger-ui-standalone-preset.js").permitAll()
            .antMatchers("/v3/api-docs").permitAll()
            .antMatchers("/v3/api-docs/swagger-config").permitAll()
            .antMatchers(HttpMethod.OPTIONS).anonymous().anyRequest().authenticated().and().sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}