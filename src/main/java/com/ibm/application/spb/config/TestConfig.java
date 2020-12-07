package com.ibm.application.spb.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ibm.application.spb.services.DBService;
import com.ibm.application.spb.services.EmailService;
import com.ibm.application.spb.services.MockEmailService;

@Configuration
@Profile("test")
public class TestConfig {

	@Autowired
	private DBService db;

	@Bean
	public boolean instantiateDataBase() throws ParseException {
		db.instantiateDataBase();

		return true;
	}

	@Bean
	public EmailService instantiateMockEmailService() {
		return new MockEmailService();
	}
}
