package com.bridgelabz.parkinglot.spot;

import java.util.Date;

public class ParkingSpot {
    public Vehicle vehicle;
    public Driver driver;
    public Date dateParking;
    String attendantName;

    public ParkingSpot(Vehicle vehicle, Driver driver, String attendantName) {
        this.vehicle = vehicle;
        this.driver = driver;
        this.dateParking = new Date();
        this.attendantName = attendantName;
    }
}
