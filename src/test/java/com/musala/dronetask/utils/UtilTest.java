package com.musala.dronetask.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UtilTest {

    @Test
    void return_false_if_drone_weight_is_greater_than_500(){
        assertFalse(Util.validateDroneWeightLimit(900));
    }

    @Test
    void return_false_if_drone_weight_is_less_than_zero(){
        assertFalse(Util.validateDroneWeightLimit(-1));
    }
    @Test
    void return_true_if_drone_weight_is_greater_or_equal_500(){
        assertTrue(Util.validateDroneWeightLimit(500));
    }

    @Test
    void return_false_if_drone_battery_capacity_is_greater_than_100(){
        assertFalse(Util.validateDroneBatteryCapacity(150));
    }

    @Test
    void return_true_if_drone_battery_capacity_is_equal_100(){
        assertTrue(Util.validateDroneBatteryCapacity(100));
    }

    @Test
    void return_false_if_drone_battery_capacity_is_less_than_zero(){
        assertFalse(Util.validateDroneBatteryCapacity(-20));
    }

    @Test
    void return_true_if_medication_name_is_valid(){
        // allowed only letters, numbers, ‘-‘, ‘_’
        assertTrue(Util.validateMedicationName("ms_001-AZ"));
    }

    @Test
    void return_false_if_medication_name_is_not_valid(){
        // allowed only letters, numbers, ‘-‘, ‘_’
        assertFalse(Util.validateMedicationName("ms_?>001-AZ"));
    }

    @Test
    void return_true_if_medication_code_is_valid(){
        // allowed only upper case letters, underscore and numbers
        assertTrue(Util.validateMedicationCode("001_AZ"));
    }

    @Test
    void return_false_if_medication_code_is_not_valid(){
        // allowed only upper case letters, underscore and numbers
        assertFalse(Util.validateMedicationCode("ms_001-AZ"));
    }
}
