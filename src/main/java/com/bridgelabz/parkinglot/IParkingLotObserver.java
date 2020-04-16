package com.bridgelabz.parkinglot;

public interface IParkingLotObserver {
    boolean isFull(int lotNumber);

    boolean isEmpty(int lotNumber);
}
