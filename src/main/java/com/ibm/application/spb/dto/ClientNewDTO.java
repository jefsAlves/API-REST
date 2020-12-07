package com.ibm.application.spb.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.ibm.application.spb.services.validation.ClientInsert;

@ClientInsert
public class ClientNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Mandatory Filling")
	@Length(min = 5, max = 100, message = "This Field Be Contains Between 5 and 80 Characters!")
	private String name;

	@NotEmpty(message = "Mandatory Filling")
	@Email(message = "Invalid E-Mail!")
	private String email;

	@NotEmpty(message = "Mandatory Filling")
	private String password;

	@NotEmpty(message = "Mandatory Filling")
	private String sourceSecurity;

	private Integer customerType;

	@NotEmpty(message = "Mandatory Filling")
	private String phoneOne;

	private String phoneTwo;

	@NotEmpty(message = "Mandatory Filling")
	private String publicPlace;

	@NotEmpty(message = "Mandatory Filling")
	private Integer number;

	private String complement;

	private String neighborhood;

	@NotEmpty(message = "Mandatory Filling")
	private Integer zipCode;

	private Long cityId;

	public ClientNewDTO() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSourceSecurity() {
		return sourceSecurity;
	}

	public void setSourceSecurity(String sourceSecurity) {
		this.sourceSecurity = sourceSecurity;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public String getPhoneOne() {
		return phoneOne;
	}

	public void setPhoneOne(String phoneOne) {
		this.phoneOne = phoneOne;
	}

	public String getPhoneTwo() {
		return phoneTwo;
	}

	public void setPhoneTwo(String phoneTwo) {
		this.phoneTwo = phoneTwo;
	}

	public String getPublicPlace() {
		return publicPlace;
	}

	public void setPublicPlace(String publicPlace) {
		this.publicPlace = publicPlace;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public Integer getZipCode() {
		return zipCode;
	}

	public void setZipCode(Integer zipCode) {
		this.zipCode = zipCode;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}
}
