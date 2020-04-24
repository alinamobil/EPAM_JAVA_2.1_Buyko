package com.company;

import java.util.ArrayList;
import java.util.List;

public class Owner {
    private String name;
    private List<Aircraft> aircraftList;
    private String location;

    public Owner(String name, List<Aircraft> aircraftList, String location) {
        this.name = name;
        this.aircraftList = aircraftList;
        this.location = location;
    }

    public Owner(String name, String location) {
        this(name, new ArrayList<Aircraft>(), location);
    }

    public Owner() {
        this("", new ArrayList<Aircraft>(), "");
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<Aircraft> getAircraftList() {
        return aircraftList;
    }

    public void addAircraft(Aircraft aircraft) {
        this.aircraftList.add(aircraft);
    }

    @Override
    public String toString() {
        return "Name: " + this.name + ", location: " + this.location + ", number of aircrafts: " + this.aircraftList.size();
    }
}
