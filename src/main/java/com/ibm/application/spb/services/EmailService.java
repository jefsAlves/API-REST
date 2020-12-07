package com.ibm.application.spb.services;

import org.springframework.mail.SimpleMailMessage;

import com.ibm.application.spb.domain.Client;
import com.ibm.application.spb.domain.Order;

public interface EmailService {

	void sendConfirmationEmail(Order order);

	void sendEmail(SimpleMailMessage ms);

	void sendNewEmailPassword(Client client, String newPass);
}
