package com.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


//@Configuration
//@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    protected AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    protected AuthenticationFailureHandler authenticationFailureHandler;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/auth/login2")
                .loginProcessingUrl("/auth/form2")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .successForwardUrl("/hello");
        http
                //   .apply(smsCodeAuthenticationSecurityConfig)
                //    .and()
                .authorizeRequests()
                .antMatchers("/auth/login2", "/login", "/auth/form2")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().httpBasic()
                .and()
                .csrf().disable();
    }
}
