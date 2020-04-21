package com.bridgelabz.parkinglot;

import java.util.Date;

public class ParkingSlot {
    public Vehicle vehicle;
    public Driver driver;
    public Date dateParking;
    String attendantName;

    public ParkingSlot(Vehicle vehicle, Driver driver, Date date, String attendantName) {
        this.vehicle = vehicle;
        this.driver = driver;
        this.dateParking = date;
        this.attendantName = attendantName;
    }
}
