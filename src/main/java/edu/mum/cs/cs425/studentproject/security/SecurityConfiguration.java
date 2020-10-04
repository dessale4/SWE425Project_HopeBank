//package edu.mum.cs.cs425.studentproject.security;
//
//import javax.servlet.ServletContext;
//import javax.servlet.ServletException;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
////@Configuration 
////@EnableWebSecurity
//public class SecurityConfiguration  extends WebSecurityConfigurerAdapter{
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//		auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin"))
//		.roles("ADMIN").authorities("ACCESS_TEST2")
//		.and()
//		.withUser("guest").password(passwordEncoder().encode("guest"))
//		.roles("USER")
//		.and()
//		.withUser("manager").password(passwordEncoder().encode("manager"))
//		.roles("MANAGER").authorities("ACCESS_TEST1");
//	}
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception{
//		http.authorizeRequests()
////		.anyRequest().authenticated() //  any authenticated user is authorized for all links
////		.antMatchers("/index.html").permitAll()
//		.antMatchers("/index.html").hasRole("ADMIN")
//		.antMatchers("/users/list").hasRole("MANAGER")
//		.antMatchers("/accounts/list").hasAnyRole("MANAGER", "USER")
//		.antMatchers("/index.html").hasRole("ADMIN")
//		.antMatchers("/transactions/list").hasAuthority("ACCES_TEST1")
//		.antMatchers("/index.html").hasRole("ADMIN")
//		.and()
//		.httpBasic();
//	}
//	
//	@Bean 
//	PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder();
//	}
////	
////	@Override
////	public void onStartup(ServletContext servletContext) throws ServletException {
////	    super.onStartup(servletContext);
////	    servletContext.addListener(new SessionListener());
////	}
//}
