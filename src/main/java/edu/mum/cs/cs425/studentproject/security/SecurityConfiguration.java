package edu.mum.cs.cs425.studentproject.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration 
@EnableWebSecurity
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
		.and()
		.withUser("guest").password(passwordEncoder().encode("guest")).roles("USER")
		.and()
		.withUser("manager").password(passwordEncoder().encode("manager")).roles("MANAGER");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		.anyRequest().authenticated() //  any authenticated user is authorized for all links
//		.antMatchers("/index.html").permitAll()
//		.antMatchers("/index.html").hasRole("ADMIN")
//		.antMatchers("/users/list").hasRole("MANAGER")
//		.antMatchers("/accounts/list").hasAnyRole("MANAGER", "USER")
		.and()
		.httpBasic();
	}
	
	@Bean 
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
