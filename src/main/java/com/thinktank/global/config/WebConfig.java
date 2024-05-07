package com.thinktank.global.config;

import static com.thinktank.global.common.util.AuthConstants.*;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.thinktank.global.auth.handler.AuthUserArgumentResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new AuthUserArgumentResolver());
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/api/**")
			.allowedOrigins("http://localhost:8080")
			.allowedMethods("GET", "POST", "PUT", "DELETE")
			.exposedHeaders(ACCESS_TOKEN_HEADER)
			.allowCredentials(true);
	}
}
