package com.bridgelabz.parkinglot;

public class ParkingLotOwner implements IParkingLotObserver {

    public boolean isFull;
    private ParkingLotSystem parkingLotSystem;

    public ParkingLotOwner(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    @Override
    public void isFull(String position) {
        this.isFull = parkingLotSystem.isFull(Integer.parseInt(position.split(" ")[0].substring(1)));
    }
}
