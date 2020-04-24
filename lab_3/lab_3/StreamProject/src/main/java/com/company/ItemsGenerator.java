package com.company;

import java.util.ArrayList;
import java.util.List;

public class ItemsGenerator {
    private List<Aircraft> aircraftList = new ArrayList<Aircraft>();
    private List<Owner> ownerList = new ArrayList<Owner>();

    public ItemsGenerator() {
        Aircraft huge_aircraft = new Aircraft("Boeing", 3500, 200);
        Aircraft big_aircraft = new Aircraft("Qatar", 2150, 365);
        Aircraft medium_aircraft = new Aircraft("Boeing", 3000, 400);
        Aircraft little_aircraft = new Aircraft("Belavia", 6700, 250);

        Owner alina = new Owner("Alina Sidorova", "10A");
        Owner ben = new Owner("Ben Li", "15B");
        Owner vlad = new Owner("Vlag Pavlov", "28D");
        Owner anna = new Owner("Anna Karenina", "30B");
        Owner zak = new Owner("Zak Markul", "15C");
        Owner kill = new Owner("Kill Cat", "11B");
        Owner sid = new Owner("Sid Garry", "1E");
        Owner tom = new Owner("Tom Dan", "17A");
        Owner stas = new Owner("Stas Vorobey", "8B");
        Owner dasha = new Owner("Dasha Grichenko", "26E");
        Owner nick = new Owner("Nick Berhard", "28A");

        huge_aircraft.addOwner(alina);
        huge_aircraft.addOwner(ben);
        huge_aircraft.addOwner(vlad);

        big_aircraft.addOwner(anna);
        big_aircraft.addOwner(zak);

        medium_aircraft.addOwner(kill);
        medium_aircraft.addOwner(sid);
        medium_aircraft.addOwner(tom);

        little_aircraft.addOwner(stas);
        little_aircraft.addOwner(dasha);
        little_aircraft.addOwner(nick);


        Aircraft[] aircrafts = {huge_aircraft, big_aircraft, medium_aircraft, little_aircraft};
        Owner[] owners = {alina, ben, vlad, anna, zak, kill, sid, tom, stas, dasha, nick};

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
