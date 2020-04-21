package com.bridgelabz.parkinglot;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class PoliceDepartment {
    ParkingLotSystem parkingLotSystem;
    Map<String, Vehicle> vehicles = new HashMap<>();

    public PoliceDepartment(ParkingLotSystem parkingLotSystem) {
        this.parkingLotSystem = parkingLotSystem;
    }

    public Map<String, Vehicle> getAllVehicles(Object... attributes) {
        vehicles = parkingLotSystem.carsInLot;
        Arrays.stream(attributes).forEach(attribute -> getFilteredMap(attribute));
        return vehicles;
    }

    void getFilteredMap(Object object) {
        vehicles = vehicles.entrySet().stream().filter(getPredicate(object))
                .collect(Collectors.toConcurrentMap(p -> p.getKey(), p -> p.getValue()));
    }

    Predicate<? super Map.Entry<String, Vehicle>> getPredicate(Object object) {
        System.out.println(object.getClass().getName());
        switch (object.getClass().getName()) {
            case "com.bridgelabz.parkinglot.Vehicle$Color":
                return e -> object.equals(e.getValue().color);
            case "com.bridgelabz.parkinglot.Vehicle$Brand":
                return e -> object.equals(e.getValue().brand);
            case "java.util.Date":
                return e -> (e.getValue().dateParking).after((Date) object);
            case "com.bridgelabz.parkinglot.Vehicle$Size":
                return e -> object.equals(e.getValue().size);
            case "com.bridgelabz.parkinglot.Driver$IsHandicap":
                return e -> object.equals(e.getValue().driver.isHandicap);
            case "java.lang.Character":
                return e -> object.equals(parkingLotSystem.parkingAttendant.getParkingRow(e.getValue()));
            default:
                return e -> object.equals(e.getValue());
        }
    }
}
