package com.ibm.application.spb.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.ibm.application.spb.domain.PaymentWithInvoice;

@Service
public class InvoiceService {

	public void invoice(PaymentWithInvoice invoice, Date moment) {
		Calendar call = Calendar.getInstance();
		call.setTime(moment);
		call.add(Calendar.DAY_OF_MONTH, 7);
		invoice.setDueDate(call.getTime());
	}

}
