package com.ibm.application.spb.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ibm.application.spb.domain.Category;
import com.ibm.application.spb.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("SELECT DISTINCT prod FROM Product prod INNER JOIN prod.categories cat WHERE prod.name LIKE %:name% AND cat IN :categories")
	Page<Product> search(@Param("name") String name, @Param("categories") List<Category> categories, Pageable requestPage);
}
