package com.musala.dronetask.enums;

public enum ResponseCode {

	SUCCESS("000","SUCCESS"),
	FAILURE("900","FAILURE"),
	TIMEOUT("800","TIMEOUT");

	private final String code;
	private final String description;

	ResponseCode(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static ResponseCode getItem(String code) {
		for (ResponseCode item : ResponseCode.values()) {
			if (item.getCode().equals(code)) {
				return item;
			}
		}
		return null;
	}
	
}
