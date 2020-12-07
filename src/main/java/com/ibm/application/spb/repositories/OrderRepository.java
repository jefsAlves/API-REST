package com.ibm.application.spb.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.application.spb.domain.Client;
import com.ibm.application.spb.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	Page<Order> findByClient(Client client, Pageable pageRequest);
}
