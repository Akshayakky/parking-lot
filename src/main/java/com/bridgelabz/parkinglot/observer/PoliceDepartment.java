package com.bridgelabz.parkinglot.observer;

import com.bridgelabz.parkinglot.main.ParkingLotSystem;
import com.bridgelabz.parkinglot.spot.ParkingSpot;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PoliceDepartment {
    ParkingLotSystem parkingLotSystem;
    Map<String, ParkingSpot> vehicles = new HashMap<>();

    public PoliceDepartment(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public Map<String, ParkingSpot> getAllVehicles(Object... attributes) {
        vehicles = parkingLotSystem.carsInLot;
        Arrays.stream(attributes).forEach(attribute -> getFilteredMap(attribute));
        return vehicles;
    }

    void getFilteredMap(Object object) {
        vehicles = vehicles.entrySet().stream().filter(getPredicate(object))
                .collect(Collectors.toConcurrentMap(p -> p.getKey(), p -> p.getValue()));
    }

    Predicate<? super Map.Entry<String, ParkingSpot>> getPredicate(Object object) {
        switch (object.getClass().getName()) {
            case "com.bridgelabz.parkinglot.spot.Vehicle$Color":
                return e -> object.equals(e.getValue().vehicle.color);
            case "com.bridgelabz.parkinglot.spot.Vehicle$Brand":
                return e -> object.equals(e.getValue().vehicle.brand);
            case "java.util.Date":
                return e -> (e.getValue().dateParking).after((Date) object);
            case "com.bridgelabz.parkinglot.spot.Vehicle$Size":
                return e -> object.equals(e.getValue().vehicle.size);
            case "com.bridgelabz.parkinglot.spot.Driver$IsHandicap":
                return e -> object.equals(e.getValue().driver.isHandicap);
            case "java.lang.Character":
                return e -> object.equals(parkingLotSystem.parkingAttendant.getParkingRow(e.getValue().vehicle));
            default:
                return e -> object.equals(e.getValue());
        }
    }
}
