package com.masai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * Configuration class for application-specific settings.
 */
@Configuration
public class AppConfig {

	/**
     * Configures the security filter chain.
     *
     * @param http The HttpSecurity object to configure.
     * @return The configured SecurityFilterChain.
     * @throws Exception If an error occurs during configuration.
     */
	@Bean
	public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {

		http
		.cors().and() // Enable CORS
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers(HttpMethod.POST, "/api/create_data").permitAll()
		.requestMatchers("/swagger-ui*/**","/v3/api-docs/**").permitAll()
		.requestMatchers(HttpMethod.GET, "/api/get_users/**").hasAnyRole("READER")
		.requestMatchers(HttpMethod.PATCH,"/api/update_data/**").hasRole("ADMIN")
		.requestMatchers(HttpMethod.DELETE, "/api/delete_user/**").hasRole("ADMIN")
		.anyRequest().authenticated().and()
		.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.formLogin()
		.and()
		.httpBasic();

		return http.build();

	}
	

	/**
     * Creates a PasswordEncoder bean for password hashing.
     *
     * @return The PasswordEncoder bean.
     */
	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}
	

}
