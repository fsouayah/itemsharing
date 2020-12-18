package com.itemsharing.itemservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.itemsharing.itemservice.utils.SecurityUtility;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .httpBasic().and()
            .authorizeRequests()
            .antMatchers("/v1/*")
            .permitAll().anyRequest().authenticated()
            .and().csrf().disable().httpBasic();

    }
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/v1/item/**").permitAll().anyRequest()
                .hasRole("USER").and()
                // Possibly more configuration ...
                .formLogin() // enable form based log in
                // set permitAll for all URLs associated with Form Login
                .permitAll()
                .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                // enable in memory based authentication with a user named "user" and "admin"
                .inMemoryAuthentication().withUser("user").password("password").roles("USER")
                .and().withUser("admin").password("password").roles("USER", "ADMIN");
    }
}