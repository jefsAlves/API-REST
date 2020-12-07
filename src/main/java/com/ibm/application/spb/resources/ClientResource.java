package com.ibm.application.spb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ibm.application.spb.domain.Client;
import com.ibm.application.spb.dto.ClientDTO;
import com.ibm.application.spb.dto.ClientNewDTO;
import com.ibm.application.spb.services.ClientService;

@RestController
@RequestMapping(value = "/api/clients")
public class ClientResource {

	@Autowired
	private ClientService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> findById(@PathVariable Long id) {
		Client cli = service.find(id);

		return ResponseEntity.ok().body(cli);
	}

	@PreAuthorize("hasAnyRole('ADM')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		service.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<ClientDTO>> findAll(Client client) {
		List<Client> list = service.findAll(client);
		List<ClientDTO> clientDTO = list.stream().map(e -> new ClientDTO(e)).collect(Collectors.toList());
		return ResponseEntity.ok().body(clientDTO);
	}

	@PreAuthorize("hasAnyRole('ADM')")
	@GetMapping(value = "/page")
	public ResponseEntity<Page<ClientDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {
		Page<Client> list = service.findByClient(page, linesPerPage, direction, orderBy);
		Page<ClientDTO> clientDTO = list.map(e -> new ClientDTO(e));
		return ResponseEntity.ok().body(clientDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Client> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
		Client cli = service.fromDTO(clientDTO);
		cli.setId(id);
		cli = service.updateClient(cli);
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<Client> insertClient(@RequestBody ClientNewDTO clientNewDTO) {
		Client cli = service.fromDTO(clientNewDTO);
		cli = service.insertClient(cli);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cli.getId()).toUri();
		return ResponseEntity.created(uri).body(cli);
	}
}
