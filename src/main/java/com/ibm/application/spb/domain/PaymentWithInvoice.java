package com.ibm.application.spb.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ibm.application.spb.domain.enums.StatusPayment;

@Entity
@JsonTypeName("paymentWithInvoice")
public class PaymentWithInvoice extends Payment {
	private static final long serialVersionUID = 1L;

	private Date dueDate;
	private Date perDay;

	public PaymentWithInvoice() {
	}

	public PaymentWithInvoice(Long id, StatusPayment status, Order order, Date dueDate, Date perDay) {
		super(id, status, order);
		this.dueDate = dueDate;
		this.perDay = perDay;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPerDay() {
		return perDay;
	}

	public void setPerDay(Date perDay) {
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
