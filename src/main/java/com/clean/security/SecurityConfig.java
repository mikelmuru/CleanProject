package com.clean.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.clean.jwt.JwtConverter;

@Configuration
public class SecurityConfig {
	
	@Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
	private String jwkSetUri;
	
	@Autowired
	private JwtConverter converter;
	
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
//	UserDetails user =
//			User.withUsername("user").password(this.passwordEncoder().encode("user")).roles("USER").build();
//	UserDetails admin = 
//			User.withUsername("admin").password(this.passwordEncoder().encode("admin")).roles("ADMIN").build();
//	return new InMemoryUserDetailsManager(user, admin);
//	}

//	@Bean
//	public BCryptPasswordEncoder passwordEncoder() {
//	return new BCryptPasswordEncoder();
//	}
	
	/*.authorizeRequests().antMatchers("/v1/demos/login").permitAll() --- PARA TOKENS*/
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
		jwtConverter.setJwtGrantedAuthoritiesConverter(converter);
		
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/default/private").hasRole("user")
		.antMatchers("/default/private").hasRole("admin")
		.antMatchers("/default/public").anonymous()
		.antMatchers("/management/*").permitAll()
		.anyRequest().authenticated()
		.and().oauth2ResourceServer().jwt().jwtAuthenticationConverter(jwtConverter);
		
		return http.build();
	}
	
	@Bean
	public JwtDecoder jwtDecoder(){
	return NimbusJwtDecoder.withJwkSetUri(jwkSetUri).build();
	}

}
