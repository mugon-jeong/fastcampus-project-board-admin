package com.fastcampus.projectboardadmin.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 일반적인 Bean을 모아 설정 <br/>
 * - RestTemplate 등록
 */
@Configuration
public class BeanConfig {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}
