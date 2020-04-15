package com.bridgelabz.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotSystem {
    public Map<String, Vehicle> carsInLot = new HashMap<>(100);
    public boolean isFull = false;
    public boolean isEmpty = true;
    private Owner owner;
    private ParkingAttendant parkingAttendant;

    public ParkingLotSystem(Owner owner, ParkingAttendant parkingAttendant) {
        this.owner = owner;
        this.parkingAttendant = parkingAttendant;
    }

    public boolean park(Vehicle vehicle, int position) throws ParkingLotException {
        if (isFull())
            throw new ParkingLotException(ParkingLotException.ExceptionType.LOT_FULL, "Lot Limit Reached");
        if (!this.isParked(vehicle)) {
            parkingAttendant.park(carsInLot, vehicle, Integer.toString(position));
            return true;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.IS_ALREADY_PARKED, "Is Already Parked");
    }

    public boolean unPark(Vehicle vehicle) throws ParkingLotException {
        if (isEmpty())
            throw new ParkingLotException(ParkingLotException.ExceptionType.LOT_EMPTY, "Parking Lot Empty");
        if (this.isParked(vehicle)) {
            parkingAttendant.unPark(carsInLot, vehicle);
            isFull();
            return true;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.IS_ALREADY_UNPARKED, "Is Already UnParked");
    }

    public boolean isParked(Vehicle vehicle) {
        if (carsInLot.containsValue(vehicle))
            return true;
        return false;
    }

    public boolean isFull() {
        isFull = owner.isFull(carsInLot);
        return isFull;
    }

    public boolean isEmpty() {
        isEmpty = owner.isEmpty(carsInLot);
        return isEmpty;
    }

    public String getCarPosition(Vehicle vehicle) {
        return new Driver().getCarPosition(carsInLot, vehicle);
    }
}
