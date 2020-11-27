package com.ibm.application.spb.domain;

import java.time.Instant;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ibm.application.spb.domain.enums.StatusPayment;

@Entity
@JsonTypeName("paymentWithInvoice")
public class PaymentWithInvoice extends Payment {
	private static final long serialVersionUID = 1L;

	private Instant dueDate;
	private Instant perDay;

	public PaymentWithInvoice() {
	}

	public PaymentWithInvoice(Long id, StatusPayment status, Order order, Instant dueDate, Instant perDay) {
		super(id, status, order);
		this.dueDate = dueDate;
		this.perDay = perDay;
	}

	public Instant getDueDate() {
		return dueDate;
	}

	public void setDueDate(Instant dueDate) {
		this.dueDate = dueDate;
	}

	public Instant getPerDay() {
		return perDay;
	}

	public void setPerDay(Instant perDay) {
		this.perDay = perDay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PaymentWithInvoice [dueDate=" + dueDate + ", perDay=" + perDay + "]";
	}
}
