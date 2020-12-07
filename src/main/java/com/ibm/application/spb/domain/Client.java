package com.ibm.application.spb.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ibm.application.spb.domain.enums.CustomerType;
import com.ibm.application.spb.domain.enums.Profiles;

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

	@JsonIgnore
	private String password;

	private String sourceSecurity;

	private Integer customerType;

	@OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
	List<Adress> adresses = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "TB_PHONE")
	Set<String> phones = new HashSet<>();

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "TB_PROFILES")
	private Set<Integer> profiles = new HashSet<>();

	@JsonIgnore
	@OneToMany(mappedBy = "client")
	List<Order> orders = new ArrayList<>();

	public Client() {
		addProfile(Profiles.CLIENT);
	}

	public Client(Long id, String name, String email, String password, String sourceSecurity,
			CustomerType customerType) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.sourceSecurity = sourceSecurity;
		this.customerType = (customerType == null) ? null : customerType.getCode();
		addProfile(Profiles.CLIENT);
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Set<Profiles> getProfiles() {
		return profiles.stream().map(x -> Profiles.toEnum(x)).collect(Collectors.toSet());
	}

	public void addProfile(Profiles profile) {
		profiles.add(profile.getCode());
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
