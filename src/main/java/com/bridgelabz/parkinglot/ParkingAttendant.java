package com.bridgelabz.parkinglot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParkingAttendant {

    private final int NO_OF_LOTS;
    private Map<String, Vehicle> carsInLot;
    private Map<Integer, Integer> noOfCars = new HashMap<>();

    public ParkingAttendant(Map<String, Vehicle> carsInLot, int NO_OF_LOTS) {
        this.carsInLot = carsInLot;
        this.NO_OF_LOTS = NO_OF_LOTS;
    }

    public void park(Vehicle vehicle) throws ParkingLotException {
        int lot = getLotWithLeastCars();
        String key = "P" + lot + " " + (noOfCars.get(lot) + 1);
        if (carsInLot.containsKey(key))
            throw new ParkingLotException(ParkingLotException.ExceptionType.ALREADY_OCCUPIED, "Position Already Occupied");
        carsInLot.put(key, vehicle);
    }

    private int getLotWithLeastCars() {
        int currentLot = 0;
        while (currentLot++ < NO_OF_LOTS) {
            int finalI = currentLot;
            noOfCars.put(currentLot, (int) carsInLot.keySet().stream().filter(k -> k.contains("P" + finalI)).count());
        }
        return noOfCars.keySet().stream().filter(key -> Collections.min(noOfCars.values()).equals(noOfCars.get(key))).findFirst().get();
    }

    public void unPark(Vehicle vehicle) {
        carsInLot.entrySet().removeIf(entry -> vehicle.equals(entry.getValue()));
    }

    public int getNumberOfCars(String parkingLot) {
        return (int) carsInLot.keySet().stream().filter(k -> k.contains("P" + parkingLot)).count();
    }
}
