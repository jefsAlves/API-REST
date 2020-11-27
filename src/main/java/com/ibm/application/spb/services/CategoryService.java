package com.ibm.application.spb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.ibm.application.spb.domain.Category;
import com.ibm.application.spb.dto.CategoryDTO;
import com.ibm.application.spb.repositories.CategoryRepository;
import com.ibm.application.spb.services.exceptions.DataIntegrityException;
import com.ibm.application.spb.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public Category findById(Long id) {
		Optional<Category> find = repository.findById(id);

		return find.orElseThrow(() -> new ObjectNotFoundException(
				"Object Not Found " + id + " Excpetion: " + Category.class.getName()));
	}

	public Category insertCategory(Category category) {
		return category = repository.save(category);
	}

	public Category updateCategory(Long id, Category category) {
		category.setId(id);
		return repository.save(category);
	}

	public void deleteCategory(Long id) {
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("It's not possible to delete instances that have an entity for it!");
		}
	}

	public List<Category> findAll(Category category) {
		return repository.findAll();
	}

	public Page<Category> findByCategory(Integer page, Integer linesPerPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(pageRequest);
	}

	public Category fromDTO(CategoryDTO categoryDTO) {
		return new Category(categoryDTO.getId(), categoryDTO.getName());
	}
}