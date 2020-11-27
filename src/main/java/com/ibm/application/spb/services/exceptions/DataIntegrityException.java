package com.ibm.application.spb.services.exceptions;

import java.io.Serializable;

public class DataIntegrityException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public DataIntegrityException(String msg) {
		super(msg);
	}

}