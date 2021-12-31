package com.musala.dronetask.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.musala.dronetask.entities.Medication;
import com.musala.dronetask.repositories.MedicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicationService {

	private final MedicationRepository medicationRepository;

	public Optional<Medication> findById(Long id) {
		return medicationRepository.findById(id);
	}

	public Optional<Medication> findByCode(String code) {
		return medicationRepository.findByCode(code);
	}

	public List<Medication> findAll() {
		return (List<Medication>) medicationRepository.findAll();
	}

	public void deleteById(Long id) {
		medicationRepository.deleteById(id);
	}

	public void delete(Medication medication) {
		medicationRepository.delete(medication);
	}

	public Medication save(Medication medication) {
		return medicationRepository.save(medication);
	}

	public void saveAll(List<Medication> medications) {
		medicationRepository.saveAll(medications);
	}

	public boolean existsById(Long id) {
		return medicationRepository.existsById(id);
	}

	public long count() {
		return medicationRepository.count();
	}

	public void deleteAll(List<Medication> medications) {
		medicationRepository.deleteAll(medications);
	}

	public void deleteAll() {
		medicationRepository.deleteAll();
	}
}
