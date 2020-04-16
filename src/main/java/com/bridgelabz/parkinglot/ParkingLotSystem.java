package com.bridgelabz.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotSystem {
    public Map<String, Vehicle> carsInLot = new HashMap<>();
    private Owner owner;
    private ParkingAttendant parkingAttendant;

    public ParkingLotSystem(int NO_OF_LOTS, int PARKING_LOT_SIZE) {
        this.owner = new Owner(this.carsInLot, PARKING_LOT_SIZE);
        this.parkingAttendant = new ParkingAttendant(this.carsInLot, NO_OF_LOTS);
    }

    public boolean park(Vehicle vehicle) throws ParkingLotException {
        if (!this.isParked(vehicle)) {
            parkingAttendant.park(vehicle);
            return true;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.IS_ALREADY_PARKED, "Is Already Parked");
    }

    public boolean unPark(Vehicle vehicle) throws ParkingLotException {
        if (this.isParked(vehicle)) {
            parkingAttendant.unPark(vehicle);
            isFull(1);
            return true;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.IS_ALREADY_UNPARKED, "Is Already UnParked");
    }

    public boolean isParked(Vehicle vehicle) {
        if (carsInLot.containsValue(vehicle))
            return true;
        return false;
    }

    public boolean isFull(int lotNumber) {
        return owner.isFull(lotNumber);

    }

    public boolean isEmpty(int lotNumber) {
        return owner.isEmpty(lotNumber);
    }

    public String getCarPosition(Vehicle vehicle) {
        return new Driver().getCarPosition(carsInLot, vehicle);
    }

    public int getNumberOfCars(String parkingLot) {
        return parkingAttendant.getNumberOfCars(parkingLot);
    }
}
