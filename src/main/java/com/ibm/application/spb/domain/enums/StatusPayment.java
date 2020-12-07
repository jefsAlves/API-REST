package com.ibm.application.spb.domain.enums;

public enum StatusPayment {

	PEDING(1, "PENDING"), 
	CANCELED(2, "CANCELED"),
	PAID(3, "PAID");

	private Integer code;
	private String description;

	private StatusPayment(Integer code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public static StatusPayment toEnum(Integer cod) {

		if (cod == null) {
			return null;
		}

		for (StatusPayment x : StatusPayment.values()) {
			if (cod.equals(x.getCode())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Code Invalid! " + cod);
	}
}
