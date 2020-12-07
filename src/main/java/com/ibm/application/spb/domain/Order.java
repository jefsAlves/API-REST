package com.ibm.application.spb.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TB_ORDER")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date moment;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
	private Payment payment;

	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;

	@ManyToOne
	@JoinColumn(name = "develiry_address")
	private Adress adress;

	@OneToMany(mappedBy = "id.order")
	Set<OrderItem> orderItems = new HashSet<>();

	public Order() {
	}

	public Order(Long id, Date moment, Client client, Adress adress) {
		this.id = id;
		this.moment = moment;
		this.client = client;
		this.adress = adress;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Adress getAdress() {
		return adress;
	}

	public void setAdress(Adress adress) {
		this.adress = adress;
	}

	public Set<OrderItem> getOrderItems() {
		return orderItems;
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
		Order other = (Order) obj;
		return Objects.equals(id, other.id);
	}

	public Double getTotal() {
		double sum = 0.0;
		for (OrderItem order : orderItems) {
			sum += order.getSubTotal();
		}

		return sum;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		builder.append("Order Number: ");
		builder.append(id);
		builder.append("Date: ");
		builder.append(moment);
		builder.append("Name: ");
		builder.append(client);
		builder.append(getClient().getName());
		builder.append("Order Status: ");
		builder.append(getPayment().getStatus().getDescription());
		builder.append("/nDetails");
		
		for(OrderItem oi : getOrderItems()){
			builder.append(oi.toString());
		}
		
		builder.append("Total Payment: ");
		builder.append(getTotal());
		
		return builder.toString();
	}
}
