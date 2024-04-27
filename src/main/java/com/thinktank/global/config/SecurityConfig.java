package com.thinktank.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import com.thinktank.api.repository.auth.TokenRepository;
import com.thinktank.api.service.auth.AuthorizationService;
import com.thinktank.api.service.auth.JwtAuthenticationService;
import com.thinktank.api.service.auth.JwtProviderService;
import com.thinktank.global.auth.filter.AuthorizationFilter;
import com.thinktank.global.auth.filter.CustomLogoutFilter;
import com.thinktank.global.auth.filter.JwtLoginFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final AuthenticationConfiguration authenticationConfiguration;

	private final JwtProviderService jwtProviderService;

	private final TokenRepository tokenRepository;

	private final JwtAuthenticationService jwtAuthenticationService;

	private final AuthorizationService authorizationService;

	private final TokenConfig tokenConfig;

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws
		Exception {

		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

		httpSecurity.csrf(AbstractHttpConfigurer::disable);

		httpSecurity.formLogin(AbstractHttpConfigurer::disable);

		httpSecurity.httpBasic(AbstractHttpConfigurer::disable);

		httpSecurity.authorizeHttpRequests((auth) -> auth
			.requestMatchers("/login", "/api/signup").permitAll()
			.requestMatchers("/api/reissue").permitAll()
			.anyRequest().authenticated()
		);

		httpSecurity.addFilterBefore(
			new AuthorizationFilter(jwtAuthenticationService, authorizationService),
			JwtLoginFilter.class
		);

		httpSecurity.addFilterBefore(
			new CustomLogoutFilter(jwtProviderService, jwtAuthenticationService, tokenRepository),
			LogoutFilter.class
		);

		httpSecurity.addFilterAt(
			new JwtLoginFilter(
				tokenConfig, authenticationManager(authenticationConfiguration), jwtProviderService,
				authorizationService
			),
			UsernamePasswordAuthenticationFilter.class
		);

		httpSecurity.sessionManagement((session) -> session
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		);

		return httpSecurity.build();
	}
}
