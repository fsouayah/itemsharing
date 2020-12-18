package com.itemsharing.zuulserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests().antMatchers("/v1/user/**").permitAll().anyRequest()
                .hasRole("USER").and()
                // Possibly more configuration ...
                .formLogin() // enable form based log in
                // set permitAll for all URLs associated with Form Login
                .permitAll()
				.and().csrf().disable();
    }
	 */

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/api/user/**").permitAll()
				.antMatchers("/api/item/**").permitAll()
				.anyRequest()
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