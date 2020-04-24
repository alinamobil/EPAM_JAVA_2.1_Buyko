package com.company;

import java.util.*;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import java.util.stream.Stream;

public class Main {
    private static Logger logger = LogManager.getLogger(Main.class.getName());

    public static void main(String[] args) {
        ItemsGenerator itemsGenerator = new ItemsGenerator();
        List<Aircraft> aircrafts = itemsGenerator.getAircraftList();
        TasksDoer tasksDoer = new TasksDoer(new ArrayList<Aircraft>(aircrafts));
        ParallelTaskDoer parallelTaskDoer = new ParallelTaskDoer(new ArrayList<Aircraft>(aircrafts));

        int capacityThreshold = 200;
        try {
            Aircraft firstAircraftWithHigherCapacity = tasksDoer.getFirstAircraftWithCapacityOver(capacityThreshold);
            System.out.println("Aircraft with a capacity over " + capacityThreshold + ": " +
                    firstAircraftWithHigherCapacity.getName() + " aircraft, capacity = " +
                    firstAircraftWithHigherCapacity.getCapacity());
        } catch (Exception e) {
            logger.info(e.getMessage());
        }

        Aircraft minCapacityAircraft = tasksDoer.getAircraftWithMinCapacity();
        Aircraft maxCapacityAircraft = tasksDoer.getAircraftWithMaxCapacity();
        System.out.println("Min capacity aircraft: " + minCapacityAircraft.toString());
        System.out.println("Max capacity aircraft: " + maxCapacityAircraft.toString());


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


        ArrayList<Aircraft> sortedAircraftsByPrice = tasksDoer.getSortedAircraftsByPrice();
        System.out.println("Aircrafts sorted by price:");
        for(Aircraft aircraft : sortedAircraftsByPrice) {
            System.out.println("\t" + aircraft.toString());
        }


        ArrayList<Aircraft> sortedAircraftsByCapacity = tasksDoer.getSortedAircraftsByCapacity();
        System.out.println("Aircrafts sorted by capacity:");
        for(Aircraft aircraft : sortedAircraftsByCapacity) {
            System.out.println("\t" + aircraft.toString());
        }


        System.out.println("All aircrafts' names: " + tasksDoer.getAircraftNames());


        System.out.println("testing aircrafts output method:");
        tasksDoer.printAircrafts();


        System.out.println("All aircrafts' names without duplicates: " + tasksDoer.getAircraftNamesSet());
    }
}
