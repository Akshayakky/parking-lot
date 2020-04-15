package com.bridgelabz.parkinglot;

import java.util.Arrays;

public class Driver {

    public int getCarPosition(Object[] carsInLot, Object vehicle) {
        return Arrays.asList(carsInLot).indexOf(vehicle);
    }
}
