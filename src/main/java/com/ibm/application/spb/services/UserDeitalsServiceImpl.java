package com.ibm.application.spb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ibm.application.spb.domain.Client;
import com.ibm.application.spb.repositories.ClientRepository;
import com.ibm.application.spb.security.UserSS;

@Service
public class UserDeitalsServiceImpl implements UserDetailsService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Client client = clientRepository.findByEmail(email);
		if (client == null) {
			throw new UsernameNotFoundException(email);
		}

		return new UserSS(client.getId(), client.getEmail(), client.getPassword(), client.getProfiles());
	}
}
