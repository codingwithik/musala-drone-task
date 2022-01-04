package com.musala.dronetask.repositories;

import java.util.List;
import java.util.Optional;

import com.musala.dronetask.enums.DroneState;
import org.springframework.data.repository.CrudRepository;

import com.musala.dronetask.entities.Drone;

public interface DroneRepository extends CrudRepository<Drone, Long> {
	Optional<Drone> findBySerialNumber(String number);
	List<Drone> findByState(DroneState state);
}
