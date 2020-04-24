package com.company;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TasksDoer {
    private List<Aircraft> aircraftList;

    public TasksDoer(ArrayList<Aircraft> aircraftList) {
        this.aircraftList = aircraftList;
    }

    public Aircraft getFirstAircraftWithCapacityOver(int capacity) throws Exception {
        return this.aircraftList.stream()
                .filter(aircraft -> aircraft.getCapacity() > capacity)
                .findAny()
                .orElseThrow(() -> new Exception("No aircrafts with capactiy over " + capacity + " are found"));
    }

    public Aircraft getAircraftWithMinCapacity() {
        Optional<Aircraft> optionalAircraft = this.aircraftList.stream()
                .min(Comparator.comparingInt(Aircraft::getCapacity));
        return optionalAircraft.orElseGet(Aircraft::new);
    }

    public Aircraft getAircraftWithMaxCapacity() {
        Optional<Aircraft> optionalAircraft = this.aircraftList.stream()
                .max(Comparator.comparingInt(Aircraft::getCapacity));
        return optionalAircraft.isPresent() ? optionalAircraft.get() : new Aircraft();
    }

    public ArrayList<Aircraft> getAircraftsWithMultipleOwners() {
        return this.aircraftList.stream()
                .filter(aircraft -> aircraft.getStores().size() > 1)
                .peek(aircraft -> System.out.println("peeked (consecutive stream) " + aircraft.getStores().size() + " owners"))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Aircraft> getSortedAircraftsByPrice() {
        return this.aircraftList.stream()
                .sorted(Comparator.comparingInt(Aircraft::getPrice))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<Aircraft> getSortedAircraftsByCapacity() {
        return this.aircraftList.stream()
                .sorted(Comparator.comparingInt(Aircraft::getCapacity))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public ArrayList<String> getAircraftNames() {
        return this.aircraftList.stream()
                .map(Aircraft::getName)
                .peek(name -> System.out.println("peeked name " + name))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public void printAircrafts() {
        this.aircraftList.stream()
                .forEach(aircraft -> System.out.println(aircraft.getName()));
    }

    public ArrayList<String> getAircraftNamesSet() {
        return this.aircraftList.stream()
                .map(Aircraft::getName)
                .distinct()
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
