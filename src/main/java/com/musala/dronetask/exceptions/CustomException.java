package com.musala.dronetask.exceptions;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
	
	private static final long serialVersionUID = 1670051583873052761L;
	private final String message;

    public CustomException(String message) {
        this.message = message;
    }
}
