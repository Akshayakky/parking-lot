package com.bridgelabz.parkinglot;

import java.util.Arrays;

public class ParkingAttendant {

    public void park(Object[] carsInLot, Object vehicle, int position) throws ParkingLotException {
        if (carsInLot[position] != null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.ALREADY_OCCUPIED, "Position Already Occupied");
        carsInLot[position] = vehicle;
    }

    public void unPark(Vehicle[] carsInLot, Object vehicle) {
        carsInLot[Arrays.asList(carsInLot).indexOf(vehicle)] = null;
    }
}
