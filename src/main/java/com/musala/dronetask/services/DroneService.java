package com.musala.dronetask.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.musala.dronetask.dto.request.DroneRegistrationRequest;
import com.musala.dronetask.dto.request.LoadDroneRequest;
import com.musala.dronetask.dto.request.MedicationRequest;
import com.musala.dronetask.dto.response.GenericResponse;
import com.musala.dronetask.entities.Medication;
import com.musala.dronetask.enums.ResponseCode;
import com.musala.dronetask.enums.ResponseStatus;
import com.musala.dronetask.exceptions.CustomException;
import com.musala.dronetask.utils.Util;
import org.springframework.stereotype.Service;

import com.musala.dronetask.entities.Drone;
import com.musala.dronetask.repositories.DroneRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DroneService {
	
	private final DroneRepository droneRepository;

	public Optional<Drone> findById(Long id) {
		return droneRepository.findById(id);
	}

	public Optional<Drone> findBySerialNumber(String number) {
		return droneRepository.findBySerialNumber(number);
	}

	public List<Drone> findAll() {
		return (List<Drone>) droneRepository.findAll();
	}

	public void deleteById(Long id) {
		droneRepository.deleteById(id);
	}

	public void delete(Drone drone) {
		droneRepository.delete(drone);
	}

	public Drone save(Drone drone) {
		return droneRepository.save(drone);
	}

	public void saveAll(List<Drone> drones) {
		droneRepository.saveAll(drones);
	}

	public boolean existsById(Long id) {
		return droneRepository.existsById(id);
	}

	public long count() {
		return droneRepository.count();
	}

	public void deleteAll(List<Drone> drones) {
		droneRepository.deleteAll(drones);
	}

	public void deleteAll() {
		droneRepository.deleteAll();
	}

	public GenericResponse<?> droneRegistration(DroneRegistrationRequest drone) {

		if(!Util.validateDroneBatteryCapacity(drone.getBatteryCapacity()))
			throw new CustomException("invalid drone battery capacity");

		if(!Util.validateDroneWeightLimit(drone.getWeight()))
			throw new CustomException("invalid drone weight limit");

		if(findBySerialNumber(drone.getSerialNumber()).isPresent())
			throw new CustomException("Serial number already exists");

		Drone newDrone = Drone.builder()
				.serialNumber(drone.getSerialNumber())
				.batteryCapacity(drone.getBatteryCapacity())
				.model(drone.getDroneModel())
				.state(drone.getDroneState())
				.build();

		Drone droneInDb = save(newDrone);

		return new GenericResponse<>(ResponseCode.SUCCESS.getCode(), ResponseStatus.SUCCESS,
				"Request Successful", droneInDb);

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
						.map(m -> new Medication(m.getName(), m.getWeight(), m.getCode(), m.getImage()))
				        .collect(Collectors.toList());

		droneIndb.setMedications(medications);

		Drone droneInDb = save(droneIndb);

		return new GenericResponse<>(ResponseCode.SUCCESS.getCode(), ResponseStatus.SUCCESS,
				"Request Successful", droneInDb);

	}

}
