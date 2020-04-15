package com.bridgelabz.parkinglot;

import java.util.Map;

public interface IParkingLotObserver {
    boolean isFull(Map<String, Vehicle> carsInLot);

    boolean isEmpty(Map<String, Vehicle> carsInLot);
}
