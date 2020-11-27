package com.ibm.application.spb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.application.spb.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
