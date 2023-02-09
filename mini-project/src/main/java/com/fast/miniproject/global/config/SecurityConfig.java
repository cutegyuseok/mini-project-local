package com.fast.miniproject.global.config;

import com.fast.miniproject.auth.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private static final String[] PUBLIC_URLS = {
            "/register", "/login" , "/index", "/products**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{

        return http
                .authorizeRequests()
                .mvcMatchers(PUBLIC_URLS).permitAll()
                .and()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().loginPage("/login").permitAll()//로그인 유도 화면 추후 수정
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(
                        jwtFilter,
                        UsernamePasswordAuthenticationFilter.class).build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() { //시큐리티 filter 제외, 그러나 OncePerRequestFilter는 시큐리티 필터가 아니라서 로직실행
        return (web) -> web.ignoring().mvcMatchers(PUBLIC_URLS);
    }


}
