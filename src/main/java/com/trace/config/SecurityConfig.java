package com.trace.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/**").permitAll()
                .antMatchers("/blockchain/**").permitAll()
                .antMatchers("/system/**").permitAll()
                .antMatchers("/traceProduct/**").permitAll()
                .antMatchers("/traceBatch/**").permitAll()
                .antMatchers("/traceTrans/**").permitAll()
                .antMatchers("/traceEvaluate/**").permitAll()
                .antMatchers("/traceWelfare/**").permitAll()
                .antMatchers("/traceAlarm/**").permitAll()
                .antMatchers("/traceActivity/**").permitAll()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
