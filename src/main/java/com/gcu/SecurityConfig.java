package com.gcu;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
	


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    
	@Bean
	protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception 
	{
		
		http
            .authorizeHttpRequests((authz) -> authz
	            		.requestMatchers("/login", "/register", "/", "/project-portfolio", "/static/**", "/css/**", "/js/**", "/assets/**", "img/**", "/favicon.ico", "/error").permitAll()
	                    .requestMatchers("/admin/**").hasRole("ADMIN")
	                    .requestMatchers("/user**").hasRole("USER")
	                    .anyRequest().authenticated()
			)
			
			.formLogin((form) -> form
					.loginPage("/login")
					.loginProcessingUrl("/perform_login")
					.successHandler(successHandler())
					.usernameParameter("username")
		            .passwordParameter("password")
		            .failureForwardUrl("/login")
		            .defaultSuccessUrl("/login/defaultRedirect", true)
			)
			
			
			.logout((logout) -> logout
	                .logoutUrl("/logout")
	                .logoutSuccessUrl("/")
		            .invalidateHttpSession(true)
		            .clearAuthentication(true)
		            .deleteCookies("JSESSIONID")
	            );
		
		return http.build();
	}
	
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    
    public AuthenticationSuccessHandler successHandler() {
        return (HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
            if (AuthorityUtils.authorityListToSet(authentication.getAuthorities()).contains("ROLE_ADMIN")) {
                response.sendRedirect("/admin/dashboard");
            } else {
                response.sendRedirect("/user/dashboard");
            }
        };
    }
    
}
