package com.musala.dronetask.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.musala.dronetask.entities.Drone;
import com.musala.dronetask.enums.DroneModel;
import com.musala.dronetask.enums.DroneState;

@DataJpaTest
public class DroneRepositoryTest {
	
	@Autowired
	private DroneRepository droneRepository;
	
	@Test
	void given_a_valid_serial_number_return_object() {
		
		// given
		String serial = UUID.randomUUID().toString();
		
		Drone drone = Drone.builder()
				.id(1L)
				.serialNumber(serial)
				.weightLimit(500)
				.batteryCapacity(60)
				.model(DroneModel.HEAVYWEIGHT)
				.state(DroneState.IDLE)
				.build();
		
		Drone actual = droneRepository.save(drone);
		// when
		Optional<Drone> expected = droneRepository.findBySerialNumber(serial);
		
		// then
		assertThat(actual).isEqualTo(expected.get());
		//assertEquals(expected.get(), drone);
	}
	
	
	@Test
	void given_a_non_valid_serial_number_return_empty() {
		
		// given
		String serial = UUID.randomUUID().toString();
		
		// when
		Optional<Drone> expected = droneRepository.findBySerialNumber(serial);
		
		// then
		assertThat(expected).isEmpty();
	}

	@Test
	void given_a_valid_drone_state_return_object() {

		// given
		String serial = UUID.randomUUID().toString();

		Drone drone = Drone.builder()
				.id(1L)
				.serialNumber(serial)
				.weightLimit(500)
				.batteryCapacity(60)
				.model(DroneModel.HEAVYWEIGHT)
				.state(DroneState.IDLE)
				.build();

		Drone drone2 = Drone.builder()
				.id(2L)
				.serialNumber(UUID.randomUUID().toString())
				.weightLimit(300)
				.batteryCapacity(70)
				.model(DroneModel.HEAVYWEIGHT)
				.state(DroneState.IDLE)
				.build();

		droneRepository.save(drone);
		droneRepository.save(drone2);
		// when
		List<Drone> expected = droneRepository.findByState(DroneState.IDLE);

		// then
		assertEquals(2, expected.size());

	}
}
