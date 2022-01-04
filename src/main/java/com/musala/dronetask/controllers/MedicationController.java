package com.musala.dronetask.controllers;

import com.musala.dronetask.dto.response.GenericResponse;
import com.musala.dronetask.services.MedicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(name = "api")
@RequiredArgsConstructor
public class MedicationController {

    private final MedicationService medicationService;

    @GetMapping(value = "/v1/medication/checkLoadedItemsForDrone", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllLoadedItems(String name) {

        GenericResponse<?> response = medicationService.checkingLoadedMedicationItemsForDrone(name);

        return new ResponseEntity<GenericResponse<?>>(response, HttpStatus.OK);
    }
}
