package com.bridgelabz.parkinglot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParkingAttendant {

    ParkingLotSystem parkingLotSystem;
    private Map<Integer, Integer> noOfCars = new HashMap<>();

    public ParkingAttendant(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public void park(Vehicle vehicle) throws ParkingLotException {
        int lot = getLotWithLeastCars();
        if (parkingLotSystem.isFull(lot))
            throw new ParkingLotException(ParkingLotException.ExceptionType.LOTS_FULL, "All Lots Full");
        String key = "P" + lot + " " + (noOfCars.get(lot) + 1);
        parkingLotSystem.carsInLot.put(key, vehicle);
        parkingLotSystem.updateObservers(lot);
    }

    private int getLotWithLeastCars() {
        int currentLot = 0;
        while (currentLot++ < parkingLotSystem.NO_OF_LOTS) {
            int finalI = currentLot;
            noOfCars.put(currentLot, (int) parkingLotSystem.carsInLot.keySet().stream().filter(k -> k
                    .contains("P" + finalI)).count());
        }
        return noOfCars.keySet().stream().filter(key -> Collections.min(noOfCars.values()).equals(noOfCars.get(key)))
                .findFirst().get();
    }

    public void unPark(Vehicle vehicle) {
        int lotNumber = Integer.parseInt(parkingLotSystem.carsInLot.keySet().stream()
                .filter(key -> vehicle.equals(parkingLotSystem.carsInLot.get(key)))
                .findFirst().get().split(" ")[0].substring(1));
        parkingLotSystem.carsInLot.entrySet().removeIf(entry -> vehicle.equals(entry.getValue()));
        parkingLotSystem.updateObservers(lotNumber);
    }

    public boolean isParked(Vehicle vehicle) {
        if (parkingLotSystem.carsInLot.containsValue(vehicle))
            return true;
        return false;
    }

    public int getNumberOfCars(String parkingLot) {
        return (int) parkingLotSystem.carsInLot.keySet().stream().filter(k -> k.contains("P" + parkingLot)).count();
    }
}
