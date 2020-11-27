package com.ibm.application.spb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.application.spb.domain.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
