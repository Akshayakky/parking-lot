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
        updateLots();
        int lot = 0;
        if (vehicle.driver.isHandicapped == Driver.IsHandicapped.YES)
            lot = getNearestEmptyLot();
        else
            lot = getLotWithMinimumCars();
        if (parkingLotSystem.isFull(lot))
            throw new ParkingLotException(ParkingLotException.ExceptionType.LOTS_FULL, "All Lots Full");
        String key = "P" + lot + " " + (noOfCars.get(lot) + 1);
        parkingLotSystem.carsInLot.put(key, vehicle);
        parkingLotSystem.updateObservers(lot);
        updateLots();
    }

    private int getNearestEmptyLot() {
        return noOfCars.keySet().stream().filter(key -> noOfCars.get(key) < parkingLotSystem.PARKING_LOT_SIZE)
                .findFirst().get();
    }

    public void updateLots() {
        int currentLot = 0;
        while (currentLot++ < parkingLotSystem.NO_OF_LOTS) {
            int finalI = currentLot;
            noOfCars.put(currentLot, (int) parkingLotSystem.carsInLot.keySet().stream().filter(k -> k
                    .contains("P" + finalI)).count());
        }
    }

    private int getLotWithMinimumCars() {
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
}
