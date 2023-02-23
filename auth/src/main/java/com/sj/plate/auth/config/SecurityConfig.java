package com.sj.plate.auth.config;

import com.mco.pc_store.admin.security.JwtAccessDeniedHandler;
import com.mco.pc_store.admin.security.JwtAuthenticationEntryPoint;
import com.mco.pc_store.admin.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PERMIT_URL_ARRAY = {
        "/v2/api-docs",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/admin/auth/**",
        "/**/swagger-*/**",
        "/**/v2/**",
        "/**/system/healthCheck",
        "/error"
    };
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final TokenProvider tokenProvider;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()

            //exception handling 할 때 우리가 만든 클래스를 추가
            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)

            //h2 console을 위한 설정
            .and()
            .headers()
            .frameOptions()
            .sameOrigin()

            //security는 기본적으로 세션을 사용
            // 여기서는 세션 설정을 사용하지 않기 때문에 세션 설정을 Stateless로 설정
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            //로그인, 회원가입 API는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정
            //나머지 API는 전부 인증 필요
            .and()
            .authorizeRequests()
            .antMatchers(PERMIT_URL_ARRAY).permitAll()
            .anyRequest()
            .authenticated()

            //JwtFilter를 addFilterBefore로 등록했던 JwtSecurityConfig 클래스를 적용
            .and()
            .apply(new JwtSecurityConfig(tokenProvider));
    }
}
