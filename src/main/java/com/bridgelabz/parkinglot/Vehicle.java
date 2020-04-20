package com.bridgelabz.parkinglot;

import java.util.Date;

public class Vehicle {
    public Date dateParking;
    public Driver driver;
    public Size size;
    Color color;
    String plateNumber;
    Brand brand;
    String attendantName;

    public Vehicle(String attendantName, Date dateParking, Driver driver, Size size, Color color,String plateNumber, Brand brand) {
        this.attendantName = attendantName;
        this.dateParking = dateParking;
        this.driver = driver;
        this.size = size;
        this.color = color;
        this.plateNumber = plateNumber;
        this.brand = brand;
    }

    public Vehicle() {
    }

    enum Brand {
        TOYOTA, BMW;
    }

    enum Color {
        WHITE, BLACK, RED, BLUE, YELLOW;
    }

    enum Size {
        SMALL, LARGE;
    }
}
