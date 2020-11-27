package com.ibm.application.spb.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.ibm.application.spb.domain.enums.StatusPayment;

@Entity
@JsonTypeName("paymentWithCard")
public class PaymentWithCard extends Payment {
	private static final long serialVersionUID = 1L;

	private Integer installmentsNumbers;

	public PaymentWithCard() {
	}

	public PaymentWithCard(Long id, StatusPayment status, Order order, Integer installmentsNumbers) {
		super(id, status, order);
		this.installmentsNumbers = installmentsNumbers;
	}

	public Integer getInstallmentsNumbers() {
		return installmentsNumbers;
	}

	public void setInstallmentsNumbers(Integer installmentsNumbers) {
		this.installmentsNumbers = installmentsNumbers;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "PaymentWithCard [installmentsNumbers=" + installmentsNumbers + "]";
	}
}
