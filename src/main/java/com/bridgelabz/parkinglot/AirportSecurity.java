package com.bridgelabz.parkinglot;

public class AirportSecurity implements IParkingLotObserver {

    public boolean isFull;
    private ParkingLotSystem parkingLotSystem;

    public AirportSecurity(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    @Override
    public void isFull(String position) {
        this.isFull = parkingLotSystem.isFull(Integer.parseInt(position.split(" ")[0].substring(1)));
    }
}
