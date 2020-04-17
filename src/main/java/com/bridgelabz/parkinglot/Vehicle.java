package com.bridgelabz.parkinglot;

import java.util.Date;

public class Vehicle {
    public Date dateParking;
    public Driver driver;

    public Vehicle(Date dateParking) {
        this.dateParking = dateParking;
    }
}
