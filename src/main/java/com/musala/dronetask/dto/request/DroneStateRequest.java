package com.musala.dronetask.dto.request;

import com.musala.dronetask.enums.DroneState;
import lombok.Data;

@Data
public class DroneStateRequest {

    private String serialNumber;
    private DroneState droneState;
}
