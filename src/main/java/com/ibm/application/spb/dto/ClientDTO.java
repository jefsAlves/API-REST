package com.ibm.application.spb.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ibm.application.spb.domain.Client;

public class ClientDTO implements Serializable {
	static final long serialVersionUID = 1L;

	private Long id;

	@NotEmpty(message = "Mandatory Filling!")
	@Length(min = 5, max = 100, message = "This Field Must Contains 5 and 100 Characters!")
	private String name;

	@NotEmpty
	@Email(message = "Invalid Email")
	private String email;

	public ClientDTO() {
	}

	public ClientDTO(Client client) {
		id = client.getId();
		name = client.getName();
		email = client.getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
