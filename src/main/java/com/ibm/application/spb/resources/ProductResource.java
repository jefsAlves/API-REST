package com.ibm.application.spb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.application.spb.domain.Product;
import com.ibm.application.spb.dto.ProductDTO;
import com.ibm.application.spb.resources.utils.URL;
import com.ibm.application.spb.services.ProductService;

@RestController
@RequestMapping(value = "/api/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product prod = service.find(id);
		return ResponseEntity.ok().body(prod);
	}

	@GetMapping(value = "/page")
	public ResponseEntity<Page<ProductDTO>> findProduct(@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "categories", defaultValue = "") String categories,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "name") String orderBy) {

		String decoder = URL.decodeParam(name);
		List<Long> category = URL.toList(categories);
		Page<Product> product = service.find(decoder, category, page, linesPerPage, direction, orderBy);
		Page<ProductDTO> productDTO = product.map(obj -> new ProductDTO(obj));

		return ResponseEntity.ok().body(productDTO);
	}

}
