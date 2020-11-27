package com.ibm.application.spb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.application.spb.domain.City;

public interface CityRepository extends JpaRepository<City, Long>{

}
