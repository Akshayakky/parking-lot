package com.bridgelabz.parkinglot;

import org.junit.Assert;
import org.junit.Test;

public class ParkingLotTest {
    @Test
    public void givenParkingLot_WhenParked_ThenReturnTrue() {
        ParkingLotSystem parkingLotSystem = new ParkingLotSystem();
        boolean isParked = parkingLotSystem.park(new Object());
        Assert.assertTrue(isParked);
    }
}
