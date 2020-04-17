package com.bridgelabz.parkinglot;

public class Driver {

    private ParkingLotSystem parkingLotSystem;

    public Driver(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public String getCarPosition(Vehicle vehicle) {
        return parkingLotSystem.getCarPosition(vehicle);
    }
}
