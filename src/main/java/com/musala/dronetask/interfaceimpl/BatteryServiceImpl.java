package com.musala.dronetask.interfaceimpl;

import com.musala.dronetask.dto.request.DroneStateRequest;
import com.musala.dronetask.dto.response.GenericResponse;
import com.musala.dronetask.entities.Drone;
import com.musala.dronetask.entities.Medication;
import com.musala.dronetask.enums.DroneState;
import com.musala.dronetask.enums.ResponseCode;
import com.musala.dronetask.enums.ResponseStatus;
import com.musala.dronetask.exceptions.CustomException;
import com.musala.dronetask.interfaces.BatteryService;
import com.musala.dronetask.services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BatteryServiceImpl implements BatteryService {

    @Autowired
    private DroneService droneService;

    @Override
    public int checkBatteryLevel(String serialNumber) {
        Drone droneInDb = droneService.findBySerialNumber(serialNumber).orElseThrow(() ->
                new CustomException("Drone serial number not found"));

        List<Integer> itemWeights = droneInDb.getMedications().stream()
                .map(Medication::getWeight)
                .collect(Collectors.toList());

        int medicationSum = droneInDb.getMedications().size() == 0 ?
                0 : itemWeights.stream().mapToInt(Integer::intValue).sum();

        return (droneInDb.getWeightLimit() - medicationSum) / 5;
    }

}
