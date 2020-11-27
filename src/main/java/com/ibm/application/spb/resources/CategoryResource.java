package com.ibm.application.spb.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ibm.application.spb.domain.Category;
import com.ibm.application.spb.dto.CategoryDTO;
import com.ibm.application.spb.services.CategoryService;

@RestController
@RequestMapping(value = "/api/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category find = service.findById(id);
		return ResponseEntity.ok().body(find);
	}

	@PostMapping
	public ResponseEntity<Category> insertCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		Category category = service.fromDTO(categoryDTO);
		category = service.insertCategory(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId())
				.toUri();
		return ResponseEntity.created(uri).body(category);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO) {
		Category category = service.fromDTO(categoryDTO);
		category = service.updateCategory(id, category);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
		service.deleteCategory(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<CategoryDTO>> findAll(Category category) {
		List<Category> list = service.findAll(category);
		List<CategoryDTO> listDTO = list.stream().map(e -> new CategoryDTO(e)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<CategoryDTO>> findByCategory(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {

		Page<Category> list = service.findByCategory(page, linesPerPage, direction, orderBy);
		Page<CategoryDTO> listDTO = list.map(e -> new CategoryDTO(e));
		return ResponseEntity.ok().body(listDTO);
	}
}
