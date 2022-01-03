package com.musala.dronetask.repositories;

import com.musala.dronetask.entities.Medication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class MedicationRepositoryTest {

    @Autowired
    private MedicationRepository medicationRepository;

    @Test
    void given_a_valid_medication_code_return_object() {

        // given
        String code = UUID.randomUUID().toString();

        Medication medication = Medication.builder()
                .id(1L)
                .code(code)
                .weight(500)
                .name("MS-001")
                .build();

        Medication actual = medicationRepository.save(medication);
        // when
        Optional<Medication> expected = medicationRepository.findByCode(code);

        // then
        assertThat(actual).isEqualTo(expected.get());
        //assertEquals(expected.get(), medication);
    }


    @Test
    void given_a_non_valid_serial_number_return_empty() {

        // given
        String code = UUID.randomUUID().toString();

        // when
        Optional<Medication> expected = medicationRepository.findByCode(code);

        // then
        assertThat(expected).isEmpty();
    }

}
