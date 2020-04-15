package com.bridgelabz.parkinglot;

import java.util.Map;

public class Driver {

    public String getCarPosition(Map<String, Vehicle> carsInLot, Vehicle vehicle) {
        return carsInLot.keySet().stream().filter(key -> vehicle.equals(carsInLot.get(key))).findFirst().get();
    }
}
