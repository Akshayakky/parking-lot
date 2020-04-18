package com.bridgelabz.parkinglot;

import java.util.Date;

public class Vehicle {
    public Date dateParking;
    public Driver driver;
    public Size size;

    public Vehicle(Date dateParking, Driver driver, Size size) {
        this.dateParking = dateParking;
        this.driver = driver;
        this.size = size;
    }

    enum Size {
        SMALL, LARGE;
    }
}
