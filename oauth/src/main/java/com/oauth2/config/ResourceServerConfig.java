package com.oauth2.config;

import com.oauth2.config.sms.SmsCodeAuthenticationSecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    protected AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    protected SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/auth/login")
                .loginProcessingUrl("/auth/form")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);
               // .successForwardUrl("/hello");
        http
             //   .apply(smsCodeAuthenticationSecurityConfig)
            //    .and()
                .authorizeRequests()
                .antMatchers("/oauth/authorize", "/auth/login", "/login", "/auth/form","http://www.baidu.com")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and().httpBasic()
                .and()
                .csrf().disable();

    }
}
