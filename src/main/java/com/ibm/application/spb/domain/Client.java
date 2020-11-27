package com.ibm.application.spb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.application.spb.domain.enums.CustomerType;

@Entity
@Table(name = "TB_CLIENT")
public class Client implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private String sourceSecurity;

	private Integer customerType;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	List<Adress> adresses = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "phone")
	Set<String> phones = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "client")
	List<Order> orders = new ArrayList<>();

	public Client() {
	}

	public Client(Long id, String name, String email, String sourceSecurity, CustomerType customerType) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.sourceSecurity = sourceSecurity;
		this.customerType = (customerType == null) ? null : customerType.getCode();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSourceSecurity() {
		return sourceSecurity;
	}

	public void setSourceSecurity(String sourchSecurity) {
		this.sourceSecurity = sourchSecurity;
	}

	public CustomerType getCustomerType() {
		return CustomerType.toEnum(customerType);
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType.getCode();
	}

	public List<Adress> getAdresses() {
		return adresses;
	}

	public Set<String> getPhones() {
		return phones;
	}

	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", email=" + email + ", SourchSecurity=" + sourceSecurity
				+ ", customerType=" + customerType + "]";
	}
}
