package com.musala.dronetask.controllers;

import com.musala.dronetask.dto.request.DroneRegistrationRequest;
import com.musala.dronetask.dto.request.DroneStateRequest;
import com.musala.dronetask.dto.request.LoadDroneRequest;
import com.musala.dronetask.dto.response.GenericResponse;
import com.musala.dronetask.enums.ResponseCode;
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

        GenericResponse<?> response = droneService.updateDroneState(request);

        if(!response.getResponseCode().equals(ResponseCode.SUCCESS.getCode()))
            return new ResponseEntity<GenericResponse<?>>(response, HttpStatus.PRECONDITION_FAILED);

        return new ResponseEntity<GenericResponse<?>>(response, HttpStatus.OK);
    }
}
