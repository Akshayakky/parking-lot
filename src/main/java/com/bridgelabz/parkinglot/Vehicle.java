package com.bridgelabz.parkinglot;

public class Vehicle {
    public Size size;
    Color color;
    String plateNumber;
    Brand brand;

    public Vehicle(Size size, Color color,String plateNumber, Brand brand) {
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
