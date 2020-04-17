package com.bridgelabz.parkinglot;

public class ParkingLotOwner implements IParkingLotObserver {

    public boolean isFull;
    private ParkingLotSystem parkingLotSystem;

    public ParkingLotOwner(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    @Override
    public void isFull(int lotNumber) {
        this.isFull = parkingLotSystem.isFull(lotNumber);
    }
}
