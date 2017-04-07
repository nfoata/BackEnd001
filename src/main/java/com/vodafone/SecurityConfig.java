package com.vodafone;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .withUser("client1") .password("secret1").roles("CLIENT").and()
        .withUser("client2") .password("secret2").roles("CLIENT").and()
        .withUser("operator").password("secret3").roles("OPERATOR").and()
        .withUser("support") .password("secret4").roles("SUPPORT").and()
        .withUser("admin")   .password("secret5").roles("ADMIN")
       ;
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*http.authorizeRequests().anyRequest().fullyAuthenticated();
        http.httpBasic();
        http.csrf().disable();*/
        http.headers().cacheControl().disable();
        http.httpBasic().and().authorizeRequests()
        .antMatchers(HttpMethod.GET, "/service/**").hasRole("CLIENT")
        .antMatchers(HttpMethod.DELETE, "/service/**").hasRole("CLIENT")
        .antMatchers(HttpMethod.GET, "/actuator/**").hasRole("OPERATOR")
        .antMatchers(HttpMethod.PATCH, "/service/**").hasRole("ADMIN").and()
        .csrf().disable();
/*
         http.authorizeRequests()
        .antMatchers("/", "/home").access("hasRole('USER')")
        .antMatchers("/admin/**").access("hasRole('ADMIN')")
        .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
        .and().formLogin().loginPage("/login").successHandler(customSuccessHandler)
        .usernameParameter("ssoId").passwordParameter("password")
        .and().csrf()
        .and().exceptionHandling().accessDeniedPage("/Access_Denied");*/
    }
}
