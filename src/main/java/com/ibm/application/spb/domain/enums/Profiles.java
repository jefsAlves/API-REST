package com.ibm.application.spb.domain.enums;

public enum Profiles {

	CLIENT(1, "ROLE_CLIENT"),
	ADM(2, "ROLE_ADM");

	private Integer code;
	private String description;

	private Profiles() {
	}
	
	private Profiles(Integer code, String description) {
		this.code = code;
		this.description = description;
	}
	
	public Integer getCode() {
		return code;
	}
	
	public String getDescription() {
		return description;
	}

	public static Profiles toEnum(Integer code) {

		if (code == null) {
			return null;
		}

		for (Profiles x : Profiles.values()) {
			if (code.equals(x.getCode())) {
				return x;
			}
		}

		throw new IllegalArgumentException("Code not found :" + code);
	}
}
