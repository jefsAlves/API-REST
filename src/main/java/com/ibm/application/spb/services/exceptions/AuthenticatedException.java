package com.ibm.application.spb.services.exceptions;

import java.io.Serializable;

public class AuthenticatedException extends RuntimeException implements Serializable {
	private static final long serialVersionUID = 1L;

	public AuthenticatedException(String msg) {
		super(msg);
	}
}
