package com.bridgelabz.parkinglot.main;

import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.observer.AirportSecurity;
import com.bridgelabz.parkinglot.observer.IParkingLotObserver;
import com.bridgelabz.parkinglot.observer.ParkingLotOwner;
import com.bridgelabz.parkinglot.spot.ParkingSpot;
import com.bridgelabz.parkinglot.spot.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ParkingLotSystem {
    public final int NO_OF_LOTS;
    public Map<String, ParkingSpot> carsInLot = new HashMap<>();
    public ParkingAttendant parkingAttendant;
    public AirportSecurity airportSecurity;
    public ParkingLotOwner parkingLotOwner;
    public int PARKING_LOT_SIZE;
    ArrayList<IParkingLotObserver> observers = new ArrayList();

    public ParkingLotSystem(String attendantName, int NO_OF_LOTS, int PARKING_LOT_SIZE) {
        this.parkingAttendant = new ParkingAttendant(this, attendantName);
        this.parkingLotOwner = new ParkingLotOwner(this);
        this.airportSecurity = new AirportSecurity(this);
        this.PARKING_LOT_SIZE = PARKING_LOT_SIZE;
        this.NO_OF_LOTS = NO_OF_LOTS;
    }

    public boolean park(ParkingSpot parkingSpot, String... position) throws ParkingLotException {
        parkingAttendant.updateLots();
        if (parkingAttendant.isParked(parkingSpot.vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.IS_ALREADY_PARKED, "Is Already Parked");
        parkingAttendant.park(parkingSpot, position);
        return true;
    }

    public boolean unPark(ParkingSpot parkingSpot) throws ParkingLotException {
        if (!parkingAttendant.isParked(parkingSpot.vehicle))
            throw new ParkingLotException(ParkingLotException.ExceptionType.IS_ALREADY_UNPARKED, "Is Already UnParked");
        parkingAttendant.unPark(parkingSpot);
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
        for (IParkingLotObserver parkingLotObserver : observers) {
            parkingLotObserver.isFull();
        }
    }

    public void register(IParkingLotObserver parkingLotObserver) {
        observers.add(parkingLotObserver);
    }
}
