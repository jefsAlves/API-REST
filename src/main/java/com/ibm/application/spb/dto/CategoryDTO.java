package com.ibm.application.spb.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ibm.application.spb.domain.Category;

public class CategoryDTO {

	private Long id;
	@NotEmpty(message = "Mandatory Filling!")
	@Length(min = 5, max = 80, message = "This Field Be Contains Between 5 and 80 Characters!")
	private String name;

	public CategoryDTO() {
	}

	public CategoryDTO(Category category) {
		id = category.getId();
		name = category.getName();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
