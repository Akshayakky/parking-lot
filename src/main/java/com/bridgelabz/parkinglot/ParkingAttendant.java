package com.bridgelabz.parkinglot;

import java.util.Map;

public class ParkingAttendant {

    public void park(Map<String, Vehicle> carsInLot, Vehicle vehicle, String position) throws ParkingLotException {
        if (carsInLot.containsKey("1 " + position))
            throw new ParkingLotException(ParkingLotException.ExceptionType.ALREADY_OCCUPIED, "Position Already Occupied");
        carsInLot.put("1 " + position, vehicle);
    }

    public void unPark(Map<String, Vehicle> carsInLot, Vehicle vehicle) {
        carsInLot.entrySet().removeIf(entry -> vehicle.equals(entry.getValue()));

    }
}
