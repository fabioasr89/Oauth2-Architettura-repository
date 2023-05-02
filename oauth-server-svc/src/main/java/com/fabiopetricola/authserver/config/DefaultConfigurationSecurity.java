package com.fabiopetricola.authserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class DefaultConfigurationSecurity {
		/*

		@Bean
		public WebSecurityCustomizer webSecurityCustomizer() {
			return (web) -> web.ignoring().antMatchers("/error");
		}
*/
	
	 	@Bean
	    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	        http.authorizeRequests(authorizeRequests ->
	          authorizeRequests.anyRequest().authenticated()
	        )
	          .formLogin(Customizer.withDefaults());
	        
	        return http.build();
	    }
	 	
	 	@Bean
	 	UserDetailsService users() {
	 		PasswordEncoder pe=PasswordEncoderFactories.createDelegatingPasswordEncoder();
	 	    UserDetails user = User.
	 	      withUsername("fabio")
	 	      .password(pe.encode("12345"))
	 	      .roles("libri.read")
	 	      .build();
	 	    return new InMemoryUserDetailsManager(user);
	 	}
}
