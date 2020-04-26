package com.bridgelabz.parkinglot;

public class ParkingLotException extends Throwable {

    public ExceptionType type;

    public ParkingLotException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }

    public enum ExceptionType {
        IS_ALREADY_PARKED, IS_ALREADY_UNPARKED, LOTS_FULL, ALREADY_OCCUPIED, INVALID_SLOT;
    }
}
