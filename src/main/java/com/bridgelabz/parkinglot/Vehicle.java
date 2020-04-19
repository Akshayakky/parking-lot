package com.bridgelabz.parkinglot;

import java.util.Date;

public class Vehicle {
    public Date dateParking;
    public Driver driver;
    public Size size;
    Color color;

    public Vehicle(Date dateParking, Driver driver, Size size, Color color) {
        this.dateParking = dateParking;
        this.driver = driver;
        this.size = size;
        this.color = color;
    }

    public Vehicle() {
    }

    enum Color {
        WHITE, BLACK, RED, BLUE, YELLOW;
    }

    enum Size {
        SMALL, LARGE;
    }
}
