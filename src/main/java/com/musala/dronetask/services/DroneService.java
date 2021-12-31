package com.musala.dronetask.services;

import java.util.List;
import java.util.Optional;

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

}
