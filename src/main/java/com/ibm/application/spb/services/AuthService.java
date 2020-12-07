package com.ibm.application.spb.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ibm.application.spb.domain.Client;
import com.ibm.application.spb.repositories.ClientRepository;
import com.ibm.application.spb.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private EmailService emailService;

	private Random random = new Random();

	public void sendNewPassword(String email) {
		Client client = clientRepository.findByEmail(email);

		if (client == null) {
			throw new ObjectNotFoundException("Email Not Found!!");
		}

		String newPass = newPass();
		client.setPassword(bCryptPasswordEncoder.encode(newPass));
		clientRepository.save(client);

		emailService.sendNewEmailPassword(client, newPass);
	}

	private String newPass() {
		char[] vect = new char[10];
		for (int i = 0; i < 10; i++) {
			vect[i] = randonChar();
		}
		return new String(vect);
	}

	private char randonChar() {
		int numbers = random.nextInt(3);

		if (numbers == 0) {
			return (char) random.nextInt(10 + 30);
		}
		else if (numbers == 1) {
			return (char) random.nextInt(26 + 41);
		}
		else {
			return (char) random.nextInt(26 + 97);
		}
	}
}