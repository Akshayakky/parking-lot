package com.bridgelabz.parkinglot;

import java.util.Map;

public class Owner implements IParkingLotObserver {

    private int PARKING_LOT_SIZE;
    private Map<String, Vehicle> carsInLot;

    public Owner(Map<String, Vehicle> carsInLot, int PARKING_LOT_SIZE) {
        this.PARKING_LOT_SIZE = PARKING_LOT_SIZE;
        this.carsInLot = carsInLot;
    }

    @Override
    public boolean isFull(int lotNumber) {
        if (carsInLot.keySet().stream().filter(k -> k.contains("P" + lotNumber)).count() == PARKING_LOT_SIZE)
            return true;
        return false;
    }

    @Override
    public boolean isEmpty(int lotNumber) {
        if (carsInLot.keySet().stream().filter(k -> k.contains("P" + lotNumber)).count() == 0)
            return true;
        return false;
    }
}
