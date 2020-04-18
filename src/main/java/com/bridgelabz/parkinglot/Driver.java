package com.bridgelabz.parkinglot;

public class Driver {

    private ParkingLotSystem parkingLotSystem;
    public IsHandicapped isHandicapped;

    public Driver(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public Driver(ParkingLotSystem parkingLotSystem, IsHandicapped isHandicapped) {
        this.parkingLotSystem = parkingLotSystem;
        this.isHandicapped = isHandicapped;
    }

    enum IsHandicapped {
        NO, YES;
    }

    public String getCarPosition(Vehicle vehicle) {
        return parkingLotSystem.getCarPosition(vehicle);
    }
}
