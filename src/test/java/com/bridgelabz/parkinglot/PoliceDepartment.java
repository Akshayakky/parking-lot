package com.bridgelabz.parkinglot;

import java.util.HashMap;

public class PoliceDepartment {
    ParkingLotSystem parkingLotSystem;
    HashMap<String, Vehicle> vehicles = new HashMap<>();

    public PoliceDepartment(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public HashMap<String, Vehicle> getAllVehicles(Vehicle.Color color) {
        parkingLotSystem.carsInLot.entrySet().stream().filter(Entry -> color.equals(Entry.getValue().color))
                .forEach(Entry -> vehicles.put(Entry.getKey(), Entry.getValue()));
        return vehicles;
    }
}
