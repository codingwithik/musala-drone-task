package com.musala.dronetask.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

public @Data class LoadDroneRequest {

    @NotNull(message = "Drone id is required")
    private long droneId;
    private List<MedicationRequest> items;
}
