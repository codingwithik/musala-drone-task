package com.musala.dronetask.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.musala.dronetask.enums.ResponseStatus;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public @Data class GenericResponse<T> {
	
	private String responseDescription;
	private String responseCode;
	private ResponseStatus responseStatus;
	private T responsePayload;

	public GenericResponse() {}
	
	public GenericResponse(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public GenericResponse(String responseCode, ResponseStatus responseStatus, 
			String responseDescription, T responsePayload) {
		this.responseDescription = responseDescription;
		this.responseStatus = responseStatus;
		this.responseCode = responseCode;
		this.responsePayload = responsePayload;
	}

}
