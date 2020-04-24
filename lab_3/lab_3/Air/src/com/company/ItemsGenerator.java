package com.company;

import java.util.ArrayList;
import java.util.List;

public class ItemsGenerator {
    private List<Aircraft> aircraftList = new ArrayList<Aircraft>();
    private List<Owner> ownerList = new ArrayList<Owner>();

    public ItemsGenerator() {
        Aircraft white_aircraft = new Aircraft("Boeing", 4000, 170);
        Aircraft red_aircraft = new Aircraft("Qatar", 2000, 280);
        Aircraft green_aircraft = new Aircraft("Boeing", 1000, 220);
        Aircraft blue_aircraft = new Aircraft("Belavia", 3000, 270);
        Aircraft black_aircraft = new Aircraft("Qatar", 3700, 200);

        Owner donald = new Owner("Donald Trump", "11D");
        Owner bill = new Owner("Bill Gates", "6F");
        Owner ivan = new Owner("Ivan Ivanov", "22E");
        Owner anna = new Owner("Anna Sidorova", "1A");
        Owner petya = new Owner("Petya Smirnov", "17B");
        Owner nick = new Owner("Nickolai Grachev", "10C");
        Owner tom = new Owner("Tom Cat", "29A");
        Owner jerry = new Owner("Jerry Mouse", "29B");
        Owner clp4tp = new Owner("Clap Trap", "6D");
        Owner micky = new Owner("Micky Mouse", "25D");
        Owner steve = new Owner("Steve FromMinecraft", "11E");

        white_aircraft.addOwner(donald);
        white_aircraft.addOwner(bill);
        white_aircraft.addOwner(ivan);

        red_aircraft.addOwner(anna);
        red_aircraft.addOwner(petya);
        red_aircraft.addOwner(nick);

        green_aircraft.addOwner(tom);
        green_aircraft.addOwner(jerry);

        blue_aircraft.addOwner(clp4tp);

        black_aircraft.addOwner(micky);
        black_aircraft.addOwner(steve);


        Aircraft[] aircrafts = {white_aircraft, red_aircraft, green_aircraft, blue_aircraft, black_aircraft};
        Owner[] owners = {donald, bill, ivan, anna, petya, nick, tom, jerry, clp4tp, micky, steve};

        for(Aircraft aircraft : aircrafts) {
            this.aircraftList.add(aircraft);
        }

        for(Owner owner: owners) {
            this.ownerList.add(owner);
        }
    }

    public List<Aircraft> getAircraftList() {
        return aircraftList;
    }

    public List<Owner> getOwnerList() {
        return ownerList;
    }
}

