package com.bridgelabz.parkinglot;

public interface IParkingLotObserver {
    boolean isFull(Vehicle[] carsInLot);
    boolean isEmpty(Vehicle[] carsInLot);
}
