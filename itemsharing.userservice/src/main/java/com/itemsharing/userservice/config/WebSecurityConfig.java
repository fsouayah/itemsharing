package com.itemsharing.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import com.itemsharing.userservice.utils.SecurityUtility;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("user")
                .password(passwordEncoder().encode("password")).roles("USER");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    */

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

   	@Override
   	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
   		auth
   		// enable in memory based authentication with a user named "user" and "admin"
   		.inMemoryAuthentication().withUser("user").password("password").roles("USER")
   				.and().withUser("admin").password("password").roles("USER", "ADMIN");
   	}
}