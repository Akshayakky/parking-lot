package com.bridgelabz.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLotSystem {
    public final int NO_OF_LOTS;
    public Map<String, ParkingSlot> carsInLot = new HashMap<>();
    public ParkingAttendant parkingAttendant;
    public AirportSecurity airportSecurity;
    public ParkingLotOwner parkingLotOwner;
    public int PARKING_LOT_SIZE;

    public ParkingLotSystem(String attendantName, int NO_OF_LOTS, int PARKING_LOT_SIZE) {
        this.parkingAttendant = new ParkingAttendant(this, attendantName);
        this.parkingLotOwner = new ParkingLotOwner(this);
        this.airportSecurity = new AirportSecurity(this);
        this.PARKING_LOT_SIZE = PARKING_LOT_SIZE;
        this.NO_OF_LOTS = NO_OF_LOTS;
    }

    public boolean park(ParkingSlot parkingSlot, String... position) throws ParkingLotException {
        parkingAttendant.updateLots();
        if (parkingAttendant.isParked(parkingSlot.vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.IS_ALREADY_PARKED, "Is Already Parked");
        parkingAttendant.park(parkingSlot, position);
        return true;
    }

    public boolean unPark(ParkingSlot parkingSlot) throws ParkingLotException {
        if (!parkingAttendant.isParked(parkingSlot.vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.IS_ALREADY_UNPARKED, "Is Already UnParked");
        parkingAttendant.unPark(parkingSlot);
        return true;
    }

    public boolean isFull() {
        return (NO_OF_LOTS * PARKING_LOT_SIZE == carsInLot.size()) ? true : false;
    }

    public boolean isEmpty() {
        return (carsInLot.size() == 0) ? true : false;
    }

    public String getCarPosition(Vehicle vehicle) {
        return carsInLot.keySet().stream().filter(key -> vehicle.equals(carsInLot.get(key).vehicle)).findFirst().get();
    }

    public int getNumberOfCars(int lotNumber) {
        return (int) carsInLot.keySet().stream().filter(k -> k.contains("P" + lotNumber)).count();
    }

    public void updateObservers() {
        parkingLotOwner.isFull();
        airportSecurity.isFull();
    }
}
