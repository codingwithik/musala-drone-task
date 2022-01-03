package com.musala.dronetask.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

public @Data class MedicationRequest {

    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "Weight is required")
    private Integer weight;
    private String image;
    @NotNull(message = "Code is required")
    private String code;
}
