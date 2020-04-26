package com.bridgelabz.parkinglot.spot;

public class Vehicle {
    public Size size;
    public Color color;
    public String plateNumber;
    public Brand brand;

    public Vehicle(Size size, Color color, String plateNumber, Brand brand) {
        this.size = size;
        this.color = color;
        this.plateNumber = plateNumber;
        this.brand = brand;
    }

    public Vehicle() {
    }

    public enum Brand {
        TOYOTA, BMW;
    }

    public enum Color {
        WHITE, BLACK, RED, BLUE, YELLOW;
    }

    public enum Size {
        SMALL, LARGE;
    }
}
