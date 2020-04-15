package com.bridgelabz.parkinglot;

import java.util.Map;

public class Owner implements IParkingLotObserver {

    private int PARKING_LOT_SIZE = 100;

    @Override
    public boolean isFull(Map<String, Vehicle> carsInLot) {
        if (carsInLot.keySet().stream().filter(k -> k.contains("1")).count() == PARKING_LOT_SIZE)
            return true;
        return false;
    }

    @Override
    public boolean isEmpty(Map<String, Vehicle> carsInLot) {
        if (carsInLot.keySet().stream().filter(k -> k.contains("1")).count() == 0)
            return true;
        return false;
    }
}
