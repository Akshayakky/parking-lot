package com.bridgelabz.parkinglot;

import java.util.Arrays;

public class ParkingLotSystem {
    public int PARKING_LOT_SIZE = 100;
    //    public ArrayList<Object> carsInLot = new ArrayList<>();
    public Object[] carsInLot = new Object[100];
    public int isFull = 0;

    public boolean park(Object vehicle, int position) throws ParkingLotException {
        if (isFull())
            throw new ParkingLotException(ParkingLotException.ExceptionType.LOT_FULL, "Lot Limit Reached");
        if (this.isParked(vehicle) == false) {
            new ParkingAttendant().park(carsInLot, vehicle, position);
            isFull();
            return true;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.IS_ALREADY_PARKED, "Is Already Parked");
    }

    public boolean unPark(Object vehicle) throws ParkingLotException {
        if (Arrays.stream(carsInLot).filter(s -> s != null).count() == 0)
            throw new ParkingLotException(ParkingLotException.ExceptionType.LOT_EMPTY, "Parking Lot Empty");
        if (this.isParked(vehicle) == true) {
            carsInLot[Arrays.asList(carsInLot).indexOf(vehicle)] = null;
            isFull();
            return true;
        }
        throw new ParkingLotException(ParkingLotException.ExceptionType.IS_ALREADY_UNPARKED, "Is Already UnParked");
    }

    public boolean isParked(Object vehicle) {
        if (Arrays.asList(carsInLot).contains(vehicle))
            return true;
        return false;
    }

    public boolean isFull() {
        if (Arrays.stream(carsInLot).filter(s -> s != null).count() == PARKING_LOT_SIZE) {
            isFull = 1;
            return true;
        } else isFull = 0;
        return false;
    }

    public int getCarPosition(Object vehicle) {
        return new Driver().getCarPosition(carsInLot, vehicle);
    }
}
