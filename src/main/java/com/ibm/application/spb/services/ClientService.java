package com.ibm.application.spb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibm.application.spb.domain.Adress;
import com.ibm.application.spb.domain.City;
import com.ibm.application.spb.domain.Client;
import com.ibm.application.spb.domain.enums.CustomerType;
import com.ibm.application.spb.domain.enums.Profiles;
import com.ibm.application.spb.dto.ClientDTO;
import com.ibm.application.spb.dto.ClientNewDTO;
import com.ibm.application.spb.repositories.AdressRepository;
import com.ibm.application.spb.repositories.ClientRepository;
import com.ibm.application.spb.security.UserSS;
import com.ibm.application.spb.security.UserService;
import com.ibm.application.spb.services.exceptions.AuthenticatedException;
import com.ibm.application.spb.services.exceptions.DataIntegrityException;
import com.ibm.application.spb.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Autowired
	private AdressRepository adressRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptePasswordEncoder;

	public Client find(Long id) {
		UserSS user = UserService.authenticated();
		
		if(user == null || !user.hasRole(Profiles.ADM)&& !id.equals(user.getId())) {
			throw new AuthenticatedException("Access Denied!");
		}
		
		Optional<Client> cli = clientRepository.findById(id);
		
		return cli.orElseThrow(() -> new ObjectNotFoundException("Object Not Found " + id + "Exception: " + Client.class.getName()));
	}

	public void deleteCategory(Long id) {
		try {
			clientRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"It's not possible to delete this record because there are entities associated with it!");
		}
	}

	public List<Client> findAll(Client client) {
		return clientRepository.findAll();
	}

	public Page<Client> findByClient(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageResquest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clientRepository.findAll(pageResquest);
	}

	public Client updateClient(Client client) {
		Client newClient = find(client.getId());
		updateData(newClient, client);
		
		return clientRepository.save(newClient);
	}

	public Client fromDTO(ClientDTO clientDTO) {
		return new Client(clientDTO.getId(), clientDTO.getName(), clientDTO.getEmail(), null, null, null);
	}

	public void updateData(Client newClient, Client client) {
		newClient.setName(client.getName());
		newClient.setEmail(client.getEmail());
	}

	@Transactional
	public Client insertClient(Client client) {
		client.setId(null);
		client = clientRepository.save(client);
		adressRepository.saveAll(client.getAdresses());
		
		return client;
	}

	public Client fromDTO(ClientNewDTO clientNewDTO) {
		Client client = new Client(null, clientNewDTO.getName(), bCryptePasswordEncoder.encode(clientNewDTO.getPassword()), clientNewDTO.getPassword(), clientNewDTO.getSourceSecurity(), CustomerType.toEnum(clientNewDTO.getCustomerType()));
		City city = new City(clientNewDTO.getCityId(), null, null);
		Adress adress = new Adress(null, clientNewDTO.getPublicPlace(), clientNewDTO.getNumber(), clientNewDTO.getComplement(), clientNewDTO.getNeighborhood(), clientNewDTO.getZipCode(), client, city);

		client.getAdresses().add(adress);
		client.getPhones().add(clientNewDTO.getPhoneOne());

		if (clientNewDTO.getPhoneTwo() != null) {
			client.getPhones().add(clientNewDTO.getPhoneTwo());
		}
		return client;
	}
}
