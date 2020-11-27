package com.ibm.application.spb.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.application.spb.domain.Client;
import com.ibm.application.spb.dto.ClientNewDTO;
import com.ibm.application.spb.repositories.ClientRepository;
import com.ibm.application.spb.resources.exceptions.FieldMessage;

public class ClientInsertValidator implements ConstraintValidator<ClientInsert, ClientNewDTO> {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public void initialize(ClientInsert ann) {
	}

	@Override
	public boolean isValid(ClientNewDTO clientDTO, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();

		Client cli = clientRepository.findByEmail(clientDTO.getEmail());

		if (cli != null) {
			list.add(new FieldMessage("Email", "This email already exists, please recover your password!"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}

		return list.isEmpty();
	}
}
