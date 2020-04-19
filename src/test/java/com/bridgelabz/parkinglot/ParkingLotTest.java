package com.bridgelabz.parkinglot;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;

public class ParkingLotTest {

    int PARKING_LOT_SIZE;
    int NO_OF_LOTS;

    int carsInPark;

    ParkingLotSystem parkingLotSystem;
    Vehicle vehicle;
    Date date;
    Driver driver;
    Vehicle.Color color;
    Vehicle.Size size;

    @Before
    public void setup() {
        PARKING_LOT_SIZE = 100;
        NO_OF_LOTS = 1;
        carsInPark = 0;
        color = Vehicle.Color.WHITE;
        size = Vehicle.Size.SMALL;
        date = new Date();
        driver = new Driver(parkingLotSystem, Driver.IsHandicap.NO);
        vehicle = new Vehicle(date, driver, size, color);
        parkingLotSystem = new ParkingLotSystem(NO_OF_LOTS, PARKING_LOT_SIZE);
    }

    @Test
    public void givenParkingLot_WhenParked_ThenReturnTrue() {
        try {
            boolean isParked = parkingLotSystem.park(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenUnparked_ThenReturnTrue() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isUnparked = parkingLotSystem.unPark(vehicle);
            Assert.assertTrue(isUnparked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenAlreadyParked_ThenThrowException() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isParked = parkingLotSystem.park(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.IS_ALREADY_PARKED, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenAlreadyUnparked_ThenThrowException() {
        try {
            parkingLotSystem.park(vehicle);
            parkingLotSystem.unPark(vehicle);
            boolean isUnparked = parkingLotSystem.unPark(vehicle);
            Assert.assertTrue(isUnparked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.IS_ALREADY_UNPARKED, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenPositionOccupied_ThenThrowException() {
        try {
            parkingLotSystem.park(vehicle, "P1 1");
            boolean isParked = parkingLotSystem.park(new Vehicle(), "P1 1");
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.ALREADY_OCCUPIED, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenUnparked_ThenThrowException() {
        try {
            boolean isUnparked = parkingLotSystem.unPark(vehicle);
            Assert.assertTrue(isUnparked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.IS_ALREADY_UNPARKED, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenLotEmpty_ThenReturnTrue() {
        boolean isEmpty = parkingLotSystem.isEmpty();
        Assert.assertTrue(isEmpty);
    }

    @Test
    public void givenParkingLot_WhenLotNotEmpty_ThenReturnFalse() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isEmpty = parkingLotSystem.isEmpty();
            Assert.assertFalse(isEmpty);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenLotNotFull_ThenReturnFalse() {
        try {
            parkingLotSystem.park(vehicle);
            boolean isFull = parkingLotSystem.isFull();
            Assert.assertFalse(isFull);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenLotFull_ThenReturnTrue() {
        try {
            while (carsInPark++ < PARKING_LOT_SIZE)
                parkingLotSystem.park(new Vehicle(date, driver, size, color));
            boolean isFull = parkingLotSystem.isFull();
            Assert.assertTrue(isFull);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenLotFull_ThenThrowException() {
        try {
            while (carsInPark++ < NO_OF_LOTS * PARKING_LOT_SIZE)
                parkingLotSystem.park(new Vehicle(date, driver, size, color));
            boolean isParked = parkingLotSystem.park(new Vehicle(date, driver, size, color));
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.LOTS_FULL, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenLotFull_ThenRedirectSecurity() {
        try {
            while (carsInPark++ < PARKING_LOT_SIZE)
                parkingLotSystem.park(new Vehicle(date, driver, size, color));
            boolean redirectSecurity = parkingLotSystem.airportSecurity.isFull;
            Assert.assertTrue(redirectSecurity);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenLotNotFull_ThenDontRedirectSecurity() {
        try {
            while (carsInPark++ < PARKING_LOT_SIZE - 1)
                parkingLotSystem.park(new Vehicle(date, driver, size, color));
            boolean redirectSecurity = parkingLotSystem.airportSecurity.isFull;
            Assert.assertFalse(redirectSecurity);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenLotNotFull_ThenTakeOffFullSign() {
        try {
            while (carsInPark++ < PARKING_LOT_SIZE - 1)
                parkingLotSystem.park(new Vehicle(date, driver, size, color));
            Assert.assertFalse(parkingLotSystem.isFull());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenPositionGiven_ThenParkAtGivenPosition() {
        try {
            boolean isParked = parkingLotSystem.park(vehicle);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenDriverEntersCar_ThenReturnPosition() {
        try {
            parkingLotSystem.park(vehicle, "P1 1");
            vehicle.driver = new Driver(parkingLotSystem);
            String position = vehicle.driver.getCarPosition(vehicle);
            Assert.assertEquals("P1 1", position);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingAttendant_ShouldDistributeTrafficEvenly() {
        try {
            parkingLotSystem = new ParkingLotSystem(4, PARKING_LOT_SIZE);
            while (carsInPark++ < 120)
                parkingLotSystem.park(new Vehicle(date, driver, size, color));
            int carsInLot1 = parkingLotSystem.getNumberOfCars(1);
            Assert.assertEquals(30, carsInLot1);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingAttendant_WhenDriverHandicap_ThenParkNearestFreeSpace() {
        try {
            parkingLotSystem = new ParkingLotSystem(4, PARKING_LOT_SIZE);
            while (carsInPark++ < 120)
                parkingLotSystem.park(new Vehicle(date, new Driver(parkingLotSystem, Driver.IsHandicap.YES), size, color));
            Assert.assertEquals(100, parkingLotSystem.getNumberOfCars(1));
            Assert.assertEquals(20, parkingLotSystem.getNumberOfCars(2));
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingAttendant_WhenCarIsLarge_ThenParkInLotWithMaximumFreeSpace() {
        try {
            parkingLotSystem = new ParkingLotSystem(4, PARKING_LOT_SIZE);
            parkingLotSystem.park(new Vehicle(date, driver, size, color), "P1 1");
            parkingLotSystem.park(new Vehicle(date, driver, size, color), "P3 1");
            parkingLotSystem.park(new Vehicle(date, driver, size, color), "P4 1");
            Vehicle largeVehicle = new Vehicle(date, driver, Vehicle.Size.LARGE, color);
            parkingLotSystem.park(largeVehicle);
            String largeVehicleLot = parkingLotSystem.carsInLot.keySet().stream().filter(key -> largeVehicle
                    .equals(parkingLotSystem.carsInLot.get(key))).findFirst().get().split(" ")[0];
            Assert.assertEquals("P2", largeVehicleLot);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotSystem_WhenCarColorGiven_ThenReturnCar() {
        try {
            parkingLotSystem = new ParkingLotSystem(4, PARKING_LOT_SIZE);
            PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
            parkingLotSystem.park(new Vehicle(date, driver, size, color), "P1 1");
            parkingLotSystem.park(new Vehicle(date, driver, size, color), "P2 1");
            parkingLotSystem.park(new Vehicle(date, driver, size, color), "P4 1");
            HashMap<String, Vehicle> vehicles = policeDepartment.getAllVehicles(Vehicle.Color.WHITE);
            Assert.assertEquals(3, vehicles.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
