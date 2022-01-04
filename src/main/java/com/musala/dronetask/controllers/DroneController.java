package com.musala.dronetask.controllers;

import com.musala.dronetask.dto.request.DroneRegistrationRequest;
import com.musala.dronetask.dto.request.DroneStateRequest;
import com.musala.dronetask.dto.request.LoadDroneRequest;
import com.musala.dronetask.dto.response.GenericResponse;
import com.musala.dronetask.entities.Drone;
import com.musala.dronetask.enums.DroneState;
import com.musala.dronetask.enums.ResponseCode;
import com.musala.dronetask.enums.ResponseStatus;
import com.musala.dronetask.exceptions.CustomException;
import com.musala.dronetask.interfaces.BatteryService;
import com.musala.dronetask.services.DroneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(name = "api")
public class DroneController {

    private final DroneService droneService;
    private final BatteryService batteryService;

    @PostMapping(value = "/v1/drone/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerDrone(@Valid @RequestBody DroneRegistrationRequest request) {

        GenericResponse<?> response = droneService.droneRegistration(request);

        if(!response.getResponseCode().equals(ResponseCode.SUCCESS.getCode()))
            return new ResponseEntity<GenericResponse<?>>(response, HttpStatus.PRECONDITION_FAILED);

        return new ResponseEntity<GenericResponse<?>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/drone/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllRegisteredDrone() {

        GenericResponse<?> response = droneService.getAllDrones();

        return new ResponseEntity<GenericResponse<?>>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/v1/drone/loadDroneWithItems", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loadDroneWithMedicationItems(@Valid @RequestBody LoadDroneRequest request) {

        GenericResponse<?> response = droneService.loadDroneWithMedication(request);

        if(!response.getResponseCode().equals(ResponseCode.SUCCESS.getCode()))
            return new ResponseEntity<GenericResponse<?>>(response, HttpStatus.PRECONDITION_FAILED);

        return new ResponseEntity<GenericResponse<?>>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/v1/drone/checkForAvailableDrones", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllAvailableDrones() {

        GenericResponse<?> response = droneService.checkForAvailableDrones();

        return new ResponseEntity<GenericResponse<?>>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/v1/drone/updateDroneState", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loadDroneWithMedicationItems(@Valid @RequestBody DroneStateRequest request) {

        Drone droneInDb = droneService.findBySerialNumber(request.getSerialNumber()).orElseThrow(() ->
                new CustomException("Drone serial number not found"));

        if(request.getDroneState().equals(DroneState.LOADING) &&
                batteryService.checkBatteryLevel(droneInDb.getSerialNumber()) < 25)
            throw new CustomException("Drone battery level too low");

        droneInDb.setState(request.getDroneState());
        Drone updatedDrone = droneService.save(droneInDb);

        return new ResponseEntity<GenericResponse<?>>(new GenericResponse<>(ResponseCode.SUCCESS.getCode(), ResponseStatus.SUCCESS,
                "Request Successful", updatedDrone), HttpStatus.OK);
    }
}
