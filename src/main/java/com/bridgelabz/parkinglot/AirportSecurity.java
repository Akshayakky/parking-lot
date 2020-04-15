package com.bridgelabz.parkinglot;

public class AirportSecurity {

    ParkingLotSystem parkingLotSystem;

    public AirportSecurity(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public boolean redirectSecurity() {
        if (parkingLotSystem.isFull())
            return true;
        return false;
    }
}
