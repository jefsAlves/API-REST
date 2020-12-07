package com.ibm.application.spb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ibm.application.spb.domain.Category;
import com.ibm.application.spb.domain.Product;
import com.ibm.application.spb.repositories.CategoryRepository;
import com.ibm.application.spb.repositories.ProductRepository;
import com.ibm.application.spb.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ProductRepository productRepository;

	public Product find(Long id) {
		Optional<Product> pro = productRepository.findById(id);
		
		return pro.orElseThrow(() -> new ObjectNotFoundException("Object Not Found: " + id + "Exception: " + Product.class.getName()));
	}

	public Page<Product> find(String name, List<Long> ids, Integer page, Integer linesPerPage, String direction, String orderBy) {

		PageRequest requestPage = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = categoryRepository.findAllById(ids);

		return productRepository.search(name, categories, requestPage);
	}
}
