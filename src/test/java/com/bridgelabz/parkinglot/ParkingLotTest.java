package com.bridgelabz.parkinglot;

import com.bridgelabz.parkinglot.exception.ParkingLotException;
import com.bridgelabz.parkinglot.main.ParkingAttendant;
import com.bridgelabz.parkinglot.main.ParkingLotSystem;
import com.bridgelabz.parkinglot.observer.PoliceDepartment;
import com.bridgelabz.parkinglot.spot.Driver;
import com.bridgelabz.parkinglot.spot.ParkingSpot;
import com.bridgelabz.parkinglot.spot.Vehicle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Map;

public class ParkingLotTest {

    int PARKING_LOT_SIZE;
    int NO_OF_LOTS;

    int carsInPark;

    ParkingLotSystem parkingLotSystem;
    Vehicle vehicle;
    Driver driver;
    ParkingSpot parkingSpot;
    Vehicle.Color color;
    Vehicle.Size size;
    String plateNumber;
    Vehicle.Brand brand;
    ParkingAttendant parkingAttendant;
    String attendantName;

    @Before
    public void setup() {
        PARKING_LOT_SIZE = 100;
        NO_OF_LOTS = 1;
        carsInPark = 0;
        plateNumber = "CL01 01";
        color = Vehicle.Color.WHITE;
        size = Vehicle.Size.SMALL;
        brand = Vehicle.Brand.TOYOTA;
        driver = new Driver(parkingLotSystem, Driver.IsHandicap.NO);
        attendantName = "Attendant Name";
        vehicle = new Vehicle(size, color, plateNumber, brand);
        parkingSpot = new ParkingSpot(vehicle, driver, attendantName);
        parkingLotSystem = new ParkingLotSystem(attendantName, NO_OF_LOTS, PARKING_LOT_SIZE);
    }

    @Test
    public void givenParkingLot_WhenParked_ThenReturnTrue() {
        try {
            boolean isParked = parkingLotSystem.park(parkingSpot);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenUnparked_ThenReturnTrue() {
        try {
            parkingLotSystem.park(parkingSpot);
            boolean isUnparked = parkingLotSystem.unPark(parkingSpot);
            Assert.assertTrue(isUnparked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenAlreadyParked_ThenThrowException() {
        try {
            parkingLotSystem.park(parkingSpot);
            boolean isParked = parkingLotSystem.park(parkingSpot);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.IS_ALREADY_PARKED, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenAlreadyUnparked_ThenThrowException() {
        try {
            parkingLotSystem.park(parkingSpot);
            parkingLotSystem.unPark(parkingSpot);
            boolean isUnparked = parkingLotSystem.unPark(parkingSpot);
            Assert.assertTrue(isUnparked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.IS_ALREADY_UNPARKED, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenPositionOccupied_ThenThrowException() {
        try {
            parkingLotSystem.park(parkingSpot, "P1 1");
            boolean isParked = parkingLotSystem.park(new ParkingSpot(new Vehicle(), driver, attendantName), "P1 1");
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.ALREADY_OCCUPIED, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenUnparked_ThenThrowException() {
        try {
            boolean isUnparked = parkingLotSystem.unPark(parkingSpot);
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
            parkingLotSystem.park(parkingSpot);
            boolean isEmpty = parkingLotSystem.isEmpty();
            Assert.assertFalse(isEmpty);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenLotNotFull_ThenReturnFalse() {
        try {
            parkingLotSystem.park(parkingSpot);
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
                parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName));
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
                parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName));
            boolean isParked = parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName));
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.LOTS_FULL, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenLotFull_ThenRedirectSecurity() {
        try {
            parkingLotSystem.register(parkingLotSystem.airportSecurity);
            while (carsInPark++ < PARKING_LOT_SIZE)
                parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName));
            boolean redirectSecurity = parkingLotSystem.airportSecurity.isFull;
            Assert.assertTrue(redirectSecurity);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenLotFull_ThenUpdateOwner() {
        try {
            parkingLotSystem.register(parkingLotSystem.parkingLotOwner);
            while (carsInPark++ < PARKING_LOT_SIZE)
                parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName));
            boolean redirectSecurity = parkingLotSystem.parkingLotOwner.isFull;
            Assert.assertTrue(redirectSecurity);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenSpotInvalid_ThenThrowException() {
        try {
            boolean isParked = parkingLotSystem.park(new ParkingSpot(vehicle, driver, attendantName), "P1 -1");
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            Assert.assertEquals(ParkingLotException.ExceptionType.INVALID_SPOT, e.type);
        }
    }

    @Test
    public void givenParkingLot_WhenLotNotFull_ThenDontRedirectSecurity() {
        try {
            while (carsInPark++ < PARKING_LOT_SIZE - 1)
                parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName));
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
                parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName));
            Assert.assertFalse(parkingLotSystem.isFull());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenPositionGiven_ThenParkAtGivenPosition() {
        try {
            boolean isParked = parkingLotSystem.park(parkingSpot);
            Assert.assertTrue(isParked);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLot_WhenDriverEntersCar_ThenReturnPosition() {
        try {
            parkingLotSystem.park(parkingSpot, "P1 1");
            parkingSpot.driver = new Driver(parkingLotSystem);
            String position = parkingSpot.driver.getCarPosition(vehicle);
            Assert.assertEquals("P1 1", position);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingAttendant_ShouldDistributeTrafficEvenly() {
        try {
            parkingLotSystem = new ParkingLotSystem(attendantName, 4, PARKING_LOT_SIZE);
            while (carsInPark++ < 120)
                parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName));
            int carsInLot1 = parkingLotSystem.getNumberOfCars(1);
            Assert.assertEquals(30, carsInLot1);
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingAttendant_WhenDriverHandicap_ThenParkNearestFreeSpace() {
        try {
            parkingLotSystem = new ParkingLotSystem(attendantName, 4, PARKING_LOT_SIZE);
            while (carsInPark++ < 120)
                parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand)
                        , new Driver(parkingLotSystem, Driver.IsHandicap.YES), attendantName));
            Assert.assertEquals(100, parkingLotSystem.getNumberOfCars(1));
            Assert.assertEquals(20, parkingLotSystem.getNumberOfCars(2));
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingAttendant_WhenCarIsLarge_ThenParkInLotWithMaximumFreeSpace() {
        try {
            parkingLotSystem = new ParkingLotSystem(attendantName, 4, PARKING_LOT_SIZE);
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName), "P1 1");
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName), "P3 1");
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName), "P4 1");
            ParkingSpot largeVehicle = new ParkingSpot(new Vehicle(Vehicle.Size.LARGE, color, plateNumber, brand), driver, attendantName);
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
            parkingLotSystem = new ParkingLotSystem(attendantName, 4, PARKING_LOT_SIZE);
            PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, Vehicle.Color.WHITE, plateNumber, brand), driver, attendantName));
            Map<String, ParkingSpot> vehicles = policeDepartment.getAllVehicles(Vehicle.Color.WHITE);
            Assert.assertEquals(1, vehicles.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotSystem_WhenCarColorGivenTest2_ThenReturnCar() {
        try {
            parkingLotSystem = new ParkingLotSystem(attendantName, 4, PARKING_LOT_SIZE);
            PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, Vehicle.Color.BLUE, plateNumber, brand), driver, attendantName));
            Map<String, ParkingSpot> vehicles = policeDepartment.getAllVehicles(Vehicle.Color.WHITE);
            Assert.assertEquals(0, vehicles.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotSystem_WhenCarColorAndBrandGiven_ThenReturnCars() {
        try {
            parkingLotSystem = new ParkingLotSystem(attendantName, 4, PARKING_LOT_SIZE);
            PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, Vehicle.Color.BLUE, plateNumber, Vehicle.Brand.TOYOTA), driver, attendantName));
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, Vehicle.Color.WHITE, plateNumber, Vehicle.Brand.TOYOTA), driver, attendantName));
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, Vehicle.Color.BLUE, plateNumber, Vehicle.Brand.BMW), driver, attendantName));
            Map<String, ParkingSpot> vehicles = policeDepartment.getAllVehicles(Vehicle.Color.BLUE, Vehicle.Brand.TOYOTA);
            Assert.assertEquals(1, vehicles.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotSystem_WhenCarBrandGiven_ThenReturnCars() {
        try {
            parkingLotSystem = new ParkingLotSystem(attendantName, 4, PARKING_LOT_SIZE);
            PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, Vehicle.Brand.BMW), driver, attendantName));
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, Vehicle.Brand.TOYOTA), driver, attendantName));
            Map<String, ParkingSpot> vehicles = policeDepartment.getAllVehicles(Vehicle.Brand.BMW);
            Assert.assertEquals(1, vehicles.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotSystem_ReturnCarsParkedInLast30Minutes() {
        try {
            parkingLotSystem = new ParkingLotSystem(attendantName, 4, PARKING_LOT_SIZE);
            PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName));
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName));
            Map<String, ParkingSpot> vehicles = policeDepartment.getAllVehicles(new Date(System.currentTimeMillis() - 30 * 60 * 1000));
            Assert.assertEquals(2, vehicles.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotSystem_When1CarParkedInLast30Minutes_ThenReturn1Car() {
        try {
            parkingLotSystem = new ParkingLotSystem(attendantName, 4, PARKING_LOT_SIZE);
            PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
            ParkingSpot parkingSpot = new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName);
            parkingSpot.dateParking = new Date(System.currentTimeMillis() - 30 * 60 * 1000);
            parkingLotSystem.park(parkingSpot);
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName));
            Map<String, ParkingSpot> vehicles = policeDepartment.getAllVehicles(new Date(System.currentTimeMillis() - 30 * 60 * 1000));
            Assert.assertEquals(1, vehicles.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotSystem_ReturnCarsParkedInRowsBorD() {
        try {
            parkingLotSystem = new ParkingLotSystem(attendantName, 4, PARKING_LOT_SIZE);
            PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), new Driver(parkingLotSystem, Driver.IsHandicap.YES), attendantName), "P1 40");
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), new Driver(parkingLotSystem, Driver.IsHandicap.YES), attendantName), "P1 19");
            Map<String, ParkingSpot> vehicles = policeDepartment.getAllVehicles(Vehicle.Size.SMALL, Driver.IsHandicap.YES, 'B');
            vehicles.putAll(policeDepartment.getAllVehicles(Vehicle.Size.SMALL, Driver.IsHandicap.YES, 'D'));
            Assert.assertEquals(2, vehicles.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void givenParkingLotSystem_GetAllVehiclesForPoliceInvestigation() {
        try {
            parkingLotSystem = new ParkingLotSystem(attendantName, 4, PARKING_LOT_SIZE);
            PoliceDepartment policeDepartment = new PoliceDepartment(parkingLotSystem);
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName));
            parkingLotSystem.park(new ParkingSpot(new Vehicle(size, color, plateNumber, brand), driver, attendantName));
            Map<String, ParkingSpot> vehicles = policeDepartment.getAllVehicles();
            Assert.assertEquals(2, vehicles.size());
        } catch (ParkingLotException e) {
            e.printStackTrace();
        }
    }
}
