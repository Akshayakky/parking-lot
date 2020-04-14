package com.bridgelabz.parkinglot;

public class ParkingLotException extends Throwable {

    enum ExceptionType {
        IS_ALREADY_PARKED, IS_ALREADY_UNPARKED;
    }

    ExceptionType type;

    public ParkingLotException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
