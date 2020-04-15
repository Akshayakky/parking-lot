package com.bridgelabz.parkinglot;

import java.util.ArrayList;

public class ParkingLotSystem {
    public int PARKING_LOT_SIZE = 100;
    public ArrayList<Object> carsInLot = new ArrayList<>();

    public boolean park(Object vehicle) throws ParkingLotException {
        if (carsInLot.size() == 100)
            throw new ParkingLotException(ParkingLotException.ExceptionType.LOT_FULL, "Lot Limit Reached");
        if (this.isParked(vehicle) == false) {
            carsInLot.add(vehicle);
            return true;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.IS_ALREADY_PARKED, "Is Already Parked");
    }

    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (carsInLot.size() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.LOT_EMPTY, "Parking Lot Empty");
        if (this.isParked(vehicle) == true) {
            carsInLot.remove(vehicle);
            return true;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.IS_ALREADY_UNPARKED, "Is Already UnParked");
    }

    public boolean isParked(Object vehicle) {
        if (carsInLot.contains(vehicle))
            return true;
        return false;
    }

    public boolean isFull() {
        if (carsInLot.size() == PARKING_LOT_SIZE)
            return true;
        return false;
    }
}
