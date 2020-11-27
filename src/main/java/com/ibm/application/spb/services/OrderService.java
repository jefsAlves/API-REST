package com.ibm.application.spb.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.application.spb.domain.Order;
import com.ibm.application.spb.repositories.OrderRepository;
import com.ibm.application.spb.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public Order findById(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		return order.orElseThrow(() -> new ObjectNotFoundException("Object Not Found, SORRY!!"));
	}

}
