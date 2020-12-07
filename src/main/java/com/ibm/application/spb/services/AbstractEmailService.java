package com.ibm.application.spb.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.ibm.application.spb.domain.Client;
import com.ibm.application.spb.domain.Order;

public abstract class AbstractEmailService implements EmailService {

	@Value("${default.sender}")
	private String sender;

	@Override
	public void sendConfirmationEmail(Order order) {
		SimpleMailMessage ms = prepareSimpleMailMessageFromOrder(order);
		sendEmail(ms);
	}

	public SimpleMailMessage prepareSimpleMailMessageFromOrder(Order order) {
		SimpleMailMessage ms = new SimpleMailMessage();
		ms.setTo(order.getClient().getEmail());
		ms.setFrom(sender);
		ms.setSubject("Confirmated Order! " + order.getId());
		ms.setSentDate(new Date());
		ms.setText(order.toString());

		return ms;
	}

	@Override
	public void sendNewEmailPassword(Client client, String newPass) {
		SimpleMailMessage smm = prepareSimpleMailMessage(client, newPass);
		sendEmail(smm);
	}

	protected SimpleMailMessage prepareSimpleMailMessage(Client client, String newPass) {
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setTo(client.getEmail());
		smm.setFrom(sender);
		smm.setSubject("Password changed Sucessfuly!!");
		smm.setSentDate(new Date());
		smm.setText("New Password: " + newPass);

		return smm;
	}
}
