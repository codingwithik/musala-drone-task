package com.musala.dronetask.enums;

public enum DroneModel {

	LIGHTWEIGHT("IDLE"),
	MIDDLEWEIGHT("LOADING"), 
	CRUISERWEIGHT("DELIVERING"), 
	HEAVYWEIGHT("DELIVERED");

	final String value;

	private DroneModel(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static DroneModel fromValue(String value) {
		for (DroneModel e : DroneModel.values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException(value);
	}
}
