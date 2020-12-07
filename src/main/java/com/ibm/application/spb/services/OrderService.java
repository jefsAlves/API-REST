package com.ibm.application.spb.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ibm.application.spb.domain.Client;
import com.ibm.application.spb.domain.Order;
import com.ibm.application.spb.domain.OrderItem;
import com.ibm.application.spb.domain.PaymentWithInvoice;
import com.ibm.application.spb.domain.enums.StatusPayment;
import com.ibm.application.spb.repositories.OrderItemRepository;
import com.ibm.application.spb.repositories.OrderRepository;
import com.ibm.application.spb.repositories.PaymentRepository;
import com.ibm.application.spb.security.UserSS;
import com.ibm.application.spb.security.UserService;
import com.ibm.application.spb.services.exceptions.AuthenticatedException;
import com.ibm.application.spb.services.exceptions.ObjectNotFoundException;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ClientService clientService;

	@Autowired
	private PaymentRepository paymentyRepository;

	@Autowired
	private InvoiceService invoiceService;

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private EmailService emailService;

	public Order findById(Long id) {
		Optional<Order> order = orderRepository.findById(id);
		return order.orElseThrow(() -> new ObjectNotFoundException("Object Not Found, SORRY!!"));
	}

	public Order insertOrder(Order order) {
		order.setId(null);
		order.setMoment(new Date());
		order.setClient(clientService.find(order.getClient().getId()));
		order.getPayment().setStatus(StatusPayment.PEDING);
		order.getPayment().setOrder(order);
		if (order.getPayment() instanceof PaymentWithInvoice) {
			PaymentWithInvoice payment = (PaymentWithInvoice) order.getPayment();
			invoiceService.invoice(payment, order.getMoment());
		}

		order = orderRepository.save(order);
		paymentyRepository.save(order.getPayment());

		for (OrderItem oi : order.getOrderItems()) {
			oi.setDiscount(0);
			oi.setProduct(productService.find(oi.getProduct().getId()));
			oi.setPrice(oi.getProduct().getPrice());
			oi.setOrder(order);
		}

		orderItemRepository.saveAll(order.getOrderItems());
		emailService.sendConfirmationEmail(order);

		return order;
	}

	public Page<Order> findOrders(Integer page, Integer linesPerPage, String direction, String orderBy) {
		UserSS user = UserService.authenticated();

		if (user == null) {
			throw new AuthenticatedException("Access Denied!");
		}

		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Client cli = clientService.find(user.getId());

		return orderRepository.findByClient(cli, pageRequest);
	}
}
