package com.ibm.application.spb.domain.enums;

public enum CustomerType {

	LEGAL_PERSON(1),
	PHYSICAL_PERSON(2);

	private Integer code;

	private CustomerType() {
	}

	private CustomerType(Integer code) {
		this.code = code;
	}

	public Integer getCode() {
		return code;
	}

	public static CustomerType toEnum(Integer code) {

		if (code == null) {
			return null;
		}

		for (CustomerType x : CustomerType.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Code not found :" + code);
	}
}
