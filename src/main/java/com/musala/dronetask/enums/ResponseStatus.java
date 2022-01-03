package com.musala.dronetask.enums;


public enum ResponseStatus {
	
	SUCCESS("SUCCESSFUL"),
	FAILURE("FAILED"), 
	UNKNOWN("UNKNOWN"), 
	TIMEOUT("TIMEOUT");

	final String value;

	private ResponseStatus(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static ResponseStatus fromValue(String value) {
		for (ResponseStatus e : ResponseStatus.values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException(value);
	}
}
