package com.bridgelabz.parkinglot;

public class ParkingLotSystem {
    public Object vehicle = null;

    public boolean park(Object vehicle) throws ParkingLotException {
        if (this.isParked() == false) {
            this.vehicle = vehicle;
            return true;
        }
        return false;
    }

    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (this.isUnparked() == false) {
            this.vehicle = null;
            return true;
        }
        return false;
    }

    public boolean isParked() throws ParkingLotException {
        if (this.vehicle != null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.IS_ALREADY_PARKED, "Is Already Parked");
        return false;
    }

    public boolean isUnparked() throws ParkingLotException {
        if (this.vehicle == null)
            throw new ParkingLotException(ParkingLotException.ExceptionType.IS_ALREADY_UNPARKED, "Is Already UnParked");
        return false;
    }
}
