package com.bridgelabz.parkinglot;

public class Driver {

    public IsHandicap isHandicap;
    private ParkingLotSystem parkingLotSystem;

    public Driver(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public Driver(ParkingLotSystem parkingLotSystem, IsHandicap isHandicap) {
        this.parkingLotSystem = parkingLotSystem;
        this.isHandicap = isHandicap;
    }

    public String getCarPosition(Vehicle vehicle) {
        return parkingLotSystem.getCarPosition(vehicle);
    }

    public enum IsHandicap {
        NO, YES;
    }
}
