package com.ibm.application.spb.domain.enums;

public enum StatusPayment {

	PEDING(1), 
	CANCELED(2),
	PAID(3);

	private Integer code;

	private StatusPayment(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
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
