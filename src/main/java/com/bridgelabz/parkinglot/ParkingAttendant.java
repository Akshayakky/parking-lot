package com.bridgelabz.parkinglot;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParkingAttendant {

    ParkingLotSystem parkingLotSystem;
    private Map<Integer, Integer> noOfCars = new HashMap<>();
    String attendantName;

    public ParkingAttendant(ParkingLotSystem parkingLotSystem, String attendantName) {
        this.parkingLotSystem = parkingLotSystem;
        this.attendantName = attendantName;
    }

    public void park(Vehicle vehicle, String... positionArray) throws ParkingLotException {
        String position;
        position = (positionArray.length == 0) ? getParkingPosition(vehicle) : positionArray[0];
        if (parkingLotSystem.carsInLot.containsKey(position))
            throw new ParkingLotException(ParkingLotException.ExceptionType.ALREADY_OCCUPIED, "Position Already Occupied");
        parkingLotSystem.carsInLot.put(position, vehicle);
        parkingLotSystem.updateObservers();
        updateLots();
    }

    public String getParkingPosition(Vehicle vehicle) throws ParkingLotException {
        int lot;
        lot = (vehicle.driver.isHandicap == Driver.IsHandicap.YES) ? getNearestFreeSpaceLot() : getLotWithMinimumCars();
        if (parkingLotSystem.isFull())
            throw new ParkingLotException(ParkingLotException.ExceptionType.LOTS_FULL, "All Lots Full");
        return "P" + lot + " " + (noOfCars.get(lot) + 1);
    }

    private int getNearestFreeSpaceLot() {
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
        parkingLotSystem.carsInLot.entrySet().removeIf(entry -> vehicle.equals(entry.getValue()));
        parkingLotSystem.updateObservers();
        updateLots();
    }

    public boolean isParked(Vehicle vehicle) {
        return (parkingLotSystem.carsInLot.containsValue(vehicle)) ? true : false;
    }
}
