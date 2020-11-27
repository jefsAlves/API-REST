package com.ibm.application.spb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ibm.application.spb.domain.PaymentWithCard;
import com.ibm.application.spb.domain.PaymentWithInvoice;

@Configuration
public class JacksonConfig {

	@Bean
	public Jackson2ObjectMapperBuilder objectMapperBuilder() {
		Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {
			public void configure(ObjectMapper objectMapper) {
				objectMapper.registerSubtypes(PaymentWithCard.class);
				objectMapper.registerSubtypes(PaymentWithInvoice.class);
				super.configure(objectMapper);
			}
		};
		return builder;
	}
}
