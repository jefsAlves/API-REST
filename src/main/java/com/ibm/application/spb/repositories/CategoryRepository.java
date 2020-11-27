package com.ibm.application.spb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.application.spb.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
