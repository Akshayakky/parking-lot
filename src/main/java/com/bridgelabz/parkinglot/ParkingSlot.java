package com.bridgelabz.parkinglot;

import java.util.Date;

public class ParkingSlot {
    public Vehicle vehicle;
    public Driver driver;
    public Date dateParking;
    String attendantName;

    public ParkingSlot(Vehicle vehicle, Driver driver, String attendantName) {
        this.vehicle = vehicle;
        this.driver = driver;
        this.dateParking = new Date();
        this.attendantName = attendantName;
    }
}
