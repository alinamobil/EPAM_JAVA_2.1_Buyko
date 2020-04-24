package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ParallelTaskDoer {
    private List<Aircraft> aircraftList;

    public ParallelTaskDoer(ArrayList<Aircraft> aircraftList) {
        this.aircraftList = aircraftList;
    }

    public ArrayList<Aircraft> getAircraftsWithMultipleOwners() {
        Stream<Aircraft> aircraftStream = this.aircraftList.stream();
        return this.aircraftList.stream().parallel()
                .filter(aircraft -> aircraft.getStores().size() > 1)
                .peek(aircraft -> System.out.println("peeked (parallel stream) " + aircraft.getStores().size() + " owners"))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
