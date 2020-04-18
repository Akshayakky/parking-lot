package com.bridgelabz.parkinglot;

public class Driver {

    private ParkingLotSystem parkingLotSystem;
    public IsHandicap isHandicap;

    public Driver(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public Driver(ParkingLotSystem parkingLotSystem, IsHandicap isHandicap) {
        this.parkingLotSystem = parkingLotSystem;
        this.isHandicap = isHandicap;
    }

    enum IsHandicap {
        NO, YES;
    }

    public String getCarPosition(Vehicle vehicle) {
        return parkingLotSystem.getCarPosition(vehicle);
    }
}
