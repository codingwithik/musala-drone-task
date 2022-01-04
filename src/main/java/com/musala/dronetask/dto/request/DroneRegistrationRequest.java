package com.musala.dronetask.dto.request;

import com.musala.dronetask.enums.DroneModel;
import com.musala.dronetask.enums.DroneState;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public @Data class DroneRegistrationRequest {

    @Size(min = 5, max = 100, message="Serial Number must be between 10 and 100")
    @NotNull(message = "Serial Number is required")
    private String serialNumber;
    @NotNull(message = "Weight is required")
    private Integer weight;
    @NotNull(message = "Battery Capacity is required")
    private Integer batteryCapacity;
    @NotNull(message = "Drone Model is required")
    private DroneModel droneModel;
    @NotNull(message = "Drone State is required")
    private DroneState droneState;

}
