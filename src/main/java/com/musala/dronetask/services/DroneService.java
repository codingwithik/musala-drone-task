package com.musala.dronetask.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.musala.dronetask.dto.request.DroneRegistrationRequest;
import com.musala.dronetask.dto.request.DroneStateRequest;
import com.musala.dronetask.dto.request.LoadDroneRequest;
import com.musala.dronetask.dto.request.MedicationRequest;
import com.musala.dronetask.dto.response.GenericResponse;
import com.musala.dronetask.entities.Medication;
import com.musala.dronetask.enums.DroneState;
import com.musala.dronetask.enums.ResponseCode;
import com.musala.dronetask.enums.ResponseStatus;
import com.musala.dronetask.exceptions.CustomException;
import com.musala.dronetask.interfaces.BatteryService;
import com.musala.dronetask.utils.Util;
import org.springframework.stereotype.Service;

import com.musala.dronetask.entities.Drone;
import com.musala.dronetask.repositories.DroneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DroneService {
	
	private final DroneRepository droneRepository;
	private final BatteryService batteryService;

	public Optional<Drone> findById(Long id) {
		return droneRepository.findById(id);
	}

	public Optional<Drone> findBySerialNumber(String number) {
		return droneRepository.findBySerialNumber(number);
	}

	public List<Drone> findByState(DroneState state) {
		return droneRepository.findByState(state);
	}

	public List<Drone> findAll() {
		return (List<Drone>) droneRepository.findAll();
	}

	public Drone save(Drone drone) {
		return droneRepository.save(drone);
	}

	public GenericResponse<?> getAllDrones() {
		return new GenericResponse<>(ResponseCode.SUCCESS.getCode(), ResponseStatus.SUCCESS,
				"Request Successful", findAll());
	}

	public GenericResponse<?> droneRegistration(DroneRegistrationRequest request) {

		if(!Util.validateDroneBatteryCapacity(request.getBatteryCapacity()))
			throw new CustomException("invalid drone battery capacity");

		if(!Util.validateDroneWeightLimit(request.getWeight()))
			throw new CustomException("invalid drone weight limit");

		Drone droneInDb = findBySerialNumber(request.getSerialNumber()).orElseThrow(() ->
				new CustomException("Drone serial number not found"));

		if(!droneInDb.getState().equals(DroneState.LOADING))
			throw new CustomException("Update drone state to Loading before loading any items");

		Drone newDrone = Drone.builder()
				.serialNumber(request.getSerialNumber())
				.batteryCapacity(request.getBatteryCapacity())
				.model(request.getDroneModel())
				.state(request.getDroneState())
				.build();

		Drone drone = save(newDrone);

		return new GenericResponse<>(ResponseCode.SUCCESS.getCode(), ResponseStatus.SUCCESS,
				"Request Successful", drone);

	}

	public GenericResponse<?> loadDroneWithMedication(LoadDroneRequest drone) {

		Drone droneIndb = findById(drone.getDroneId()).orElseThrow(() ->
				new CustomException("Drone with id "+drone.getDroneId()+" not found"));

		List<Integer> itemWeights = drone.getItems().stream()
				.map(MedicationRequest::getWeight)
				.collect(Collectors.toList());

		int allItemsWeightSum = itemWeights.stream().mapToInt(Integer::intValue).sum();

		if(allItemsWeightSum > droneIndb.getWeightLimit())
			throw new CustomException("Medication weight exceeds drone limit");

		List<Medication> medications = drone.getItems().stream()
						.map(m -> new Medication(droneIndb.getId(), m.getName(), m.getWeight(), m.getCode(), m.getImage()))
				        .collect(Collectors.toList());

		if(allItemsWeightSum == droneIndb.getWeightLimit()) droneIndb.setState(DroneState.LOADED);

		droneIndb.setMedications(medications);
		Drone droneInDb = save(droneIndb);
		return new GenericResponse<>(ResponseCode.SUCCESS.getCode(), ResponseStatus.SUCCESS,
				"Request Successful", droneInDb);

	}

	public GenericResponse<?> checkForAvailableDrones() {

		List<Drone> droneInDb = findByState(DroneState.IDLE);

		return new GenericResponse<>(ResponseCode.SUCCESS.getCode(), ResponseStatus.SUCCESS,
				"Request Successful", droneInDb);

	}

	public GenericResponse<?> updateDroneState(DroneStateRequest request) {

		Drone droneInDb = findBySerialNumber(request.getSerialNumber()).orElseThrow(() ->
				new CustomException("Drone serial number not found"));

		if(request.getDroneState().equals(DroneState.LOADING) &&
				batteryService.checkBatteryLevel(droneInDb.getSerialNumber()) < 25)
			throw new CustomException("Drone battery level too low");

		droneInDb.setState(request.getDroneState());
		Drone updatedDrone = save(droneInDb);
		return new GenericResponse<>(ResponseCode.SUCCESS.getCode(), ResponseStatus.SUCCESS,
				"Request Successful", updatedDrone);

	}

}
