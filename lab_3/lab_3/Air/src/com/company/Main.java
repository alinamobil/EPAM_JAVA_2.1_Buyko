package com.company;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Stream;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        ItemsGenerator itemsGenerator = new ItemsGenerator();
        List<Aircraft> aircrafts = itemsGenerator.getAircraftList();
        TasksDoer tasksDoer = new TasksDoer(new ArrayList<Aircraft>(aircrafts));
        ParallelTaskDoer parallelTaskDoer = new ParallelTaskDoer(new ArrayList<Aircraft>(aircrafts));

        System.out.println("==============");
        int capacityThreshold = 200;
        try {
            Aircraft firstAircraftWithHigherCapacity = tasksDoer.getFirstAircraftWithCapacityOver(capacityThreshold);
            System.out.println("first found Aircraft with capacity over " + capacityThreshold + ": " +
                    firstAircraftWithHigherCapacity.getName() + " aircraft, capacity = " +
                    firstAircraftWithHigherCapacity.getCapacity());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }


        System.out.println("==============");
        Aircraft minCapacityAircraft = tasksDoer.getAircraftWithMinCapacity();
        Aircraft maxCapacityAircraft = tasksDoer.getAircraftWithMaxCapacity();
        System.out.println("min capacity aircraft: " + minCapacityAircraft.toString());
        System.out.println("max capacity aircraft: " + maxCapacityAircraft.toString());


        System.out.println("==============");
        System.out.println("==============");

        Date startForConsecutiveOperation = new Date();
        //start of consecutive operation
        ArrayList<Aircraft> aircraftsWithMultipleOwners = tasksDoer.getAircraftsWithMultipleOwners();
        //end of consecutive operation
        Date endOfConsecutiveOperation = new Date();
        long consExecTime = endOfConsecutiveOperation.getTime() - startForConsecutiveOperation.getTime();

        Date startForParallelOperation = new Date();
        //start of parallel operation
        ArrayList<Aircraft> parallelAircraftsWithMultipleUsers = parallelTaskDoer.getAircraftsWithMultipleOwners();
        //end of parallel operation
        Date endOfParallelOperation = new Date();
        long paraExecTime = endOfParallelOperation.getTime() - startForParallelOperation.getTime();

        System.out.println("Aircrafts with multiple owners:");
        for(Aircraft aircraft : aircraftsWithMultipleOwners) {
            System.out.println("\t" + aircraft.toString());
        }

        System.out.println("Consecutive stream: " + consExecTime + "ms");
        System.out.println("Parallel stream: " + paraExecTime + "ms");
        if(paraExecTime < consExecTime) {
            System.out.println("Parallel stream wins");
        }
        else {
            System.out.println("Consecutive stream wins");
        }


        System.out.println("==============");
        System.out.println("==============");
        ArrayList<Aircraft> sortedAircraftsByPrice = tasksDoer.getSortedAircraftsByPrice();
        System.out.println("Aircrafts sorted by price:");
        for(Aircraft aircraft : sortedAircraftsByPrice) {
            System.out.println("\t" + aircraft.toString());
        }


        System.out.println("==============");
        ArrayList<Aircraft> sortedAircraftsByCapacity = tasksDoer.getSortedAircraftsByCapacity();
        System.out.println("Aircrafts sorted by capacity:");
        for(Aircraft aircraft : sortedAircraftsByCapacity) {
            System.out.println("\t" + aircraft.toString());
        }


        System.out.println("==============");
        System.out.println("All aircrafts' names: " + tasksDoer.getAircraftNames());


        System.out.println("==============");
        System.out.println("testing aircrafts output method:");
        tasksDoer.printAircrafts();


        System.out.println("==============");
        System.out.println("All aircrafts' names without duplicates: " + tasksDoer.getAircraftNamesSet());
        //logger.info("hi");
    }

}
