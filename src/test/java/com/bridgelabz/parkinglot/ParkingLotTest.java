package com.bridgelabz.parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ParkingLotTest {

    Vehicle vehicle = null;
    ParkingLotSystem parkingLotSystem = null;
    int PARKING_LOT_SIZE = 100;
    int count = 0;

    @Before
    public void setup() {
        vehicle = new Vehicle(new Date());
        parkingLotSystem = new ParkingLotSystem(new Owner(), new ParkingAttendant());
        count = PARKING_LOT_SIZE;
    }

    @Test
    public void givenParkingLot_WhenParked_ThenReturnTrue() {
        try {
            boolean isParked = parkingLotSystem.park(vehicle, 1);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenUnparked_ThenReturnTrue() {
        try {
            parkingLotSystem.park(vehicle, 1);
            boolean isUnparked = parkingLotSystem.unPark(vehicle);
            Assert.assertTrue(isUnparked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenAlreadyParked_ThenThrowException() {
        try {
            parkingLotSystem.park(vehicle, 1);
            boolean isParked = parkingLotSystem.park(vehicle, 2);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.IS_ALREADY_PARKED, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenAlreadyUnparked_ThenThrowException() {
        try {
            parkingLotSystem.park(vehicle, 1);
            parkingLotSystem.park(new Vehicle(new Date()), 2);
            parkingLotSystem.unPark(vehicle);
            boolean isUnparked = parkingLotSystem.unPark(vehicle);
            Assert.assertTrue(isUnparked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.IS_ALREADY_UNPARKED, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenLotEmpty_ThenThrowException() {
        try {
            boolean isUnparked = parkingLotSystem.unPark(vehicle);
            Assert.assertTrue(isUnparked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.LOT_EMPTY, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenLotNotFull_ThenReturnFalse() {
        try {
            parkingLotSystem.park(vehicle, 1);
            boolean isFull = parkingLotSystem.isFull();
            Assert.assertFalse(isFull);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenLotFull_ThenReturnTrue() {
        try {
            while (count-- > 0)
                parkingLotSystem.park(new Vehicle(new Date()), count);
            boolean isFull = parkingLotSystem.isFull();
            Assert.assertTrue(isFull);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenLotFull_ThenThrowException() {
        try {
            while (count-- > 0)
                parkingLotSystem.park(new Vehicle(new Date()), count);
            boolean isParked = parkingLotSystem.park(new Vehicle(new Date()), 99);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.LOT_FULL, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenLotFull_ThenRedirectSecurity() {
        try {
            while (count-- > 0)
                parkingLotSystem.park(new Vehicle(new Date()), count);
            AirportSecurity airportSecurity = new AirportSecurity(parkingLotSystem);
            boolean redirectSecurity = airportSecurity.redirectSecurity();
            Assert.assertTrue(redirectSecurity);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenLotNotFull_ThenDontRedirectSecurity() {
        try {
            while (count-- > 1)
                parkingLotSystem.park(new Vehicle(new Date()), count);
            AirportSecurity airportSecurity = new AirportSecurity(parkingLotSystem);
            boolean redirectSecurity = airportSecurity.redirectSecurity();
            Assert.assertFalse(redirectSecurity);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenLotNotFull_ThenTakeOffFullSign() {
        try {
            while (count-- > 1)
                parkingLotSystem.park(new Vehicle(new Date()), count);
            parkingLotSystem.park(vehicle, 0);
            parkingLotSystem.unPark(vehicle);
            Assert.assertFalse(parkingLotSystem.isFull);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenPositionGiven_ThenParkAtGivenPosition() {
        try {
            boolean isParked = parkingLotSystem.park(vehicle, 45);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenPositionOccupied_ThenThrowException() {
        try {
            parkingLotSystem.park(vehicle, 45);
            boolean isParked = parkingLotSystem.park(new Vehicle(new Date()), 45);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.ALREADY_OCCUPIED, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenDriverEntersCar_ThenReturnPosition() {
        try {
            parkingLotSystem.park(vehicle, 45);
            String position = parkingLotSystem.getCarPosition(vehicle);
            Assert.assertEquals("1 45", position);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
