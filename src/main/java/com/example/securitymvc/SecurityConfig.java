package com.example.securitymvc;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Log
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    // There is no PasswordEncoder mapped for the id "null"
//java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
// 스프링 시큐리티 5버전으로 넘어오면서 passwordEncoder가 생겨서 필수로 암호화를 지정해줘야한다.

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        log.info("security Config....");
        httpSecurity.authorizeRequests().antMatchers("/guest/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/member/**").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");
        httpSecurity.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
        httpSecurity.authorizeRequests().antMatchers("/boards/list").permitAll();
        httpSecurity.authorizeRequests().antMatchers("/boards/register").hasAnyRole("ADMIN", "MANAGER", "BASIC");
//        antMatchers는 특정경로, authorizeRequests()는 HttpServletReuest를 이용한다는 것.

//       Login httpSecurity.formLogin(); //별도의 로그인 화면 지원
        httpSecurity.formLogin().loginPage("/login");

//        Logout httpSecurity.logout().invalidateHttpSession(true); 별도의 로그아웃 화면 없는 경우
        httpSecurity.logout().logoutUrl("/logout").invalidateHttpSession(true);
        httpSecurity.rememberMe().key("zero").userDetailsService(customUserDetailService);
    }
}

