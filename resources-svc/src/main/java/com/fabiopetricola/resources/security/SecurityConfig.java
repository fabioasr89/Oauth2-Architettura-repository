package com.fabiopetricola.resources.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fabiopetricola.resources.auth.CustomAuthenticationEntryPoint;

@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
          
		http.requestMatchers().antMatchers("/services/**")
          .and().authorizeRequests().antMatchers("/services/**").authenticated()
          .and().authorizeRequests().antMatchers("/services/libri/**")
          .hasAnyRole("libri.read").and().exceptionHandling()
          .authenticationEntryPoint(authenticationEntryPointCustom())
          .and().addFilterBefore(securityFilter(),UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
	
	@Bean
	public SecurityFilter securityFilter() {
		return new SecurityFilter();
	}
	@Bean
	public AuthenticationEntryPoint authenticationEntryPointCustom() {
		return new CustomAuthenticationEntryPoint();
	}
	
	
}
