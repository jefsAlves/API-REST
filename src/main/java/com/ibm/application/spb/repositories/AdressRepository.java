package com.ibm.application.spb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.application.spb.domain.Adress;

public interface AdressRepository extends JpaRepository<Adress, Long> {

}
