package com.ibm.application.spb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.application.spb.domain.OrderItem;

public interface OrderITemRepository extends JpaRepository<OrderItem, Long>{

}
