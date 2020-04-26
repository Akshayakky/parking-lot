package com.bridgelabz.parkinglot.main;

import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.spot.Driver;
import com.bridgelabz.parkinglot.spot.ParkingSpot;
import com.bridgelabz.parkinglot.spot.Vehicle;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ParkingAttendant {

    ParkingLotSystem parkingLotSystem;
    String attendantName;
    private Map<Integer, Integer> noOfCars = new HashMap<>();

    public ParkingAttendant(ParkingLotSystem parkingLotSystem, String attendantName) {
        this.parkingLotSystem = parkingLotSystem;
        this.attendantName = attendantName;
    }

    public void park(ParkingSpot parkingSpot, String... positionArray) throws ParkingLotException {
        String position;
        position = (positionArray.length == 0) ? getParkingPosition(parkingSpot) : positionArray[0];
        if (Integer.parseInt(position.split(" ")[1]) > parkingLotSystem.PARKING_LOT_SIZE || Integer.parseInt(position.split(" ")[1]) < 1)
            throw new ParkingLotException(ParkingLotException.ExceptionType.INVALID_SPOT, "Enter Proper Spot");
        if (parkingLotSystem.carsInLot.containsKey(position))
            throw new ParkingLotException(ParkingLotException.ExceptionType.ALREADY_OCCUPIED, "Position Already Occupied");
        parkingLotSystem.carsInLot.put(position, parkingSpot);
        parkingLotSystem.updateObservers();
        updateLots();
    }

    public String getParkingPosition(ParkingSpot parkingSpot) throws ParkingLotException {
        int lot;
        lot = (parkingSpot.driver.isHandicap == Driver.IsHandicap.YES) ? getNearestFreeSpaceLot() : getLotWithMinimumCars();
        if (parkingLotSystem.isFull())
            throw new ParkingLotException(ParkingLotException.ExceptionType.LOTS_FULL, "All Lots Full");
        return "P" + lot + " " + (getParkingSpot(lot));
    }

    private int getParkingSpot(int lot) {
        int spot = 1;
        while (parkingLotSystem.carsInLot.containsKey("P" + lot + " " + spot))
            spot++;
        return spot;
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

    public void unPark(ParkingSpot parkingSpot) {
        parkingLotSystem.carsInLot.entrySet().removeIf(entry -> parkingSpot.equals(entry.getValue()));
        parkingLotSystem.updateObservers();
        updateLots();
    }

    public char getParkingRow(Vehicle vehicle) {
        String position = parkingLotSystem.carsInLot.keySet().stream().filter(key -> parkingLotSystem.carsInLot.get(key).vehicle.equals(vehicle)).findFirst().get();
        int spotNumber = Integer.parseInt(position.split(" ")[1]);
        return (spotNumber % 10 == 0) ? (char) (spotNumber / 10 + 65 - 1) : (char) (spotNumber / 10 + 65);
    }

    public boolean isParked(Vehicle vehicle) {
        return parkingLotSystem.carsInLot.values().stream().anyMatch(value -> value.vehicle.equals(vehicle));
    }
}
