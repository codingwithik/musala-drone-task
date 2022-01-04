package com.musala.dronetask.enums;

public enum DroneState {

	IDLE("IDLE"),
	LOADING("LOADING"),
	LOADED("LOADED"),
	DELIVERING("DELIVERING"), 
	DELIVERED("DELIVERED"), 
	RETURNING("RETURNING");

	final String value;

	private DroneState(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static DroneState fromValue(String value) {
		for (DroneState e : DroneState.values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException(value);
	}
}
