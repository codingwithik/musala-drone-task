package com.musala.dronetask.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.musala.dronetask.entities.Medication;

public interface MedicationRepository extends CrudRepository<Medication, Long>{
	Optional<Medication> findByCode(String code);
	List<Medication> findByName(String name);
}
