package com.bridgelabz.parkinglot;

import java.util.Arrays;

public class Owner implements IParkingLotObserver {

    private int PARKING_LOT_SIZE = 100;

    @Override
    public boolean isFull(Vehicle[] carsInLot) {
        if (Arrays.stream(carsInLot).filter(s -> s != null).count() == PARKING_LOT_SIZE)
            return true;
        return false;
    }

    @Override
    public boolean isEmpty(Vehicle[] carsInLot) {
        if (Arrays.stream(carsInLot).filter(s -> s != null).count() == 0)
            return true;
        return false;
    }
}
