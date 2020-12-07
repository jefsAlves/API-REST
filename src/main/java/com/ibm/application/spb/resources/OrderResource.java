package com.ibm.application.spb.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ibm.application.spb.domain.Order;
import com.ibm.application.spb.services.OrderService;

@RestController
@RequestMapping(value = "/api/orders")
public class OrderResource {

	@Autowired
	private OrderService service;

	@Autowired
	private OrderService orderService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order order = service.findById(id);
		return ResponseEntity.ok().body(order);
	}

	@PostMapping
	public ResponseEntity<Order> insertOrder(@RequestBody Order order) {
		Order or = service.insertOrder(order);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(or.getId()).toUri();
		return ResponseEntity.created(uri).body(or);
	}

	@GetMapping
	public ResponseEntity<Page<Order>> findOrders(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
		Page<Order> order = orderService.findOrders(page, linesPerPage, direction, orderBy);

		return ResponseEntity.ok().body(order);
	}
}
