package com.bridgelabz.parkinglot;

public class AirportSecurity {

    ParkingLotSystem parkingLotSystem;

    public AirportSecurity(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public boolean redirectSecurity(int lotNumber) {
        if (parkingLotSystem.isFull(lotNumber))
            return true;
        return false;
    }
}
