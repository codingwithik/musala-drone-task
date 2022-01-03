package com.musala.dronetask.utils;

public class Util {

    public static boolean validateDroneWeightLimit(int weight){
        return (weight > 0) && (weight <= 500);
    }

    public static boolean validateDroneBatteryCapacity(int capacity){
        return (capacity >= 0) && (capacity <= 100);
    }

    public static boolean validateMedicationName(String name){
        // allowed only letters, numbers, ‘-‘, ‘_’
        for(int i = 0; i < name.length(); i++){
            if(!(name.charAt(i) >= 'a' && name.charAt(i) <= 'z')&&
                    !(name.charAt(i) >= 'A' && name.charAt(i) <= 'Z') &&
                    !(name.charAt(i) >= '0' && name.charAt(i) <= '9') &&
                    name.charAt(i) != '-' && name.charAt(i) != '_'){
                return false;
            }
        }
        return true;
    }

    public static boolean validateMedicationCode(String code){
        // allowed only upper case letters, underscore and numbers
        for(int i = 0; i < code.length(); i++){
            if(!(code.charAt(i) >= 'A' && code.charAt(i) <= 'Z') &&
                    !(code.charAt(i) >= '0' && code.charAt(i) <= '9') &&
                    code.charAt(i) != '_'){
                return false;
            }
        }
        return true;
    }

}
