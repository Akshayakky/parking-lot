package com.bridgelabz.parkinglot.observer;

import com.bridgelabz.parkinglot.main.ParkingLotSystem;

public class AirportSecurity implements IParkingLotObserver {

    public boolean isFull;
    private ParkingLotSystem parkingLotSystem;

    public AirportSecurity(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    @Override
    public void isFull() {
        this.isFull = parkingLotSystem.isFull();
    }
}
