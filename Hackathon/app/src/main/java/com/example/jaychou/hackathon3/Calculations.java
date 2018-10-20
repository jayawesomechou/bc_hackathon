package com.example.jaychou.hackathon3;

/**
 * Created by jaychou on 4/22/17.
 */

import java.util.*;

public class Calculations {

    private static final double earthRadius;
    private static final int lunchTime; //preferred lunch start time, in minutes since
    private static final int lunchPeriod; //minutes
    private static final double walkingSpeed; //in meters per minute

    static {
        earthRadius = 6.371e6;
        lunchTime = 720;
        lunchPeriod = 30;
        walkingSpeed = 84.0 / Math.sqrt(2.0);
    }

    private static double sine (double degrees) {return Math.sin (Math.toRadians (degrees));}
    private static double cosine (double degrees) {return Math.cos (Math.toRadians (degrees));}

    public static double distanceBetween(Location pt1, Location pt2) {
        double latDiff = pt2.lat - pt1.lat;
        double lngDiff = pt2.lng - pt1.lng;
        double haversine = Math.pow(sine(latDiff/2), 2.0) + cosine(pt1.lat) * cosine(pt2.lat) * Math.pow(sine(lngDiff/2), 2);
        double unitDistance = 2 * Math.atan2(Math.sqrt(haversine), Math.sqrt(1 - haversine));
        assert unitDistance > 0;
        return earthRadius * unitDistance;
    }

    private static double travelTime(Location from, Location to) {
        return distanceBetween(from, to) / walkingSpeed;
    }

    public static Event lunchSuggestion(Event startEvent) {
        if (startEvent == null) return null; //no classes, no lunch suggestion
        Event currentEvent = startEvent;
        int lunchStartDiff = 1440;
        Event bestLunchEvent = null;
        Event bestAfterEvent = null;
        while (currentEvent.nextEvent != null) {
            Location from = currentEvent.building.getLocation();
            Location to = currentEvent.nextEvent.building.getLocation();
            DiningHall closestDiningHall = BuildingUpLocations.getNearestDiningHall(from, to);
            Location meal = closestDiningHall.getLocation();
            double toTime = travelTime(from, meal);
            double awayTime = travelTime(to, meal);
            double totalTime = toTime + awayTime + lunchPeriod;
            int gapEnd = currentEvent.nextEvent.startMinSince12;
            int gapStart = currentEvent.endMinSince12;
            int lunchStart;
            if (totalTime <= gapEnd - gapStart) {
                if (gapEnd < lunchTime) lunchStart = (int) (gapEnd - awayTime - lunchPeriod);
                else if (gapStart > lunchTime) lunchStart = (int) (gapStart  + toTime);
                else if (lunchTime + lunchPeriod + awayTime > gapEnd) lunchStart = (int) (gapEnd - awayTime - lunchPeriod);
                else if (lunchTime - toTime < gapStart) lunchStart = (int) (gapStart + toTime);
                else lunchStart = lunchTime;
                if (Math.abs(lunchTime - lunchStart) < lunchStartDiff) {
                    bestLunchEvent = new Event(lunchStart, lunchStart + lunchPeriod, closestDiningHall, "lunch", currentEvent, currentEvent.nextEvent);
                    bestAfterEvent = currentEvent;
                }
                if (lunchStartDiff == 0) break;
            }
            currentEvent = currentEvent.nextEvent;
        }

        if (bestLunchEvent != null) {
            bestAfterEvent.nextEvent.previousEvent = bestLunchEvent;
            bestAfterEvent.nextEvent = bestLunchEvent;
        }

        return startEvent;

    }

    public static void main(String[] args) {
        AcademicBuilding gasson = BuildingUpLocations.getAcademicBuilding("Gasson");
        System.out.println(gasson);
        AcademicBuilding maloney = BuildingUpLocations.getAcademicBuilding("Maloney");
        AcademicBuilding merkert = BuildingUpLocations.getAcademicBuilding("Merkert");
        AcademicBuilding fulton = BuildingUpLocations.getAcademicBuilding("Fulton");
        AcademicBuilding lyons = BuildingUpLocations.getAcademicBuilding("Lyons");

        List<Course> cs = new ArrayList<Course>();
        cs.add(new Course("MATH110101", "M W F 9", gasson));
        cs.add(new Course("PHYS120001", "M W F 4 30*", maloney));
        cs.add(new Course("CSCI120001", "M W F 12 30", merkert));
        cs.add(new Course("CSCI120001", "T TH 10 30", gasson));
        cs.add(new Course("CSCI120001", "T TH 12 30", fulton));
        Week week = new Week(cs);
        System.out.println(week.monday.firstEvent.building);
        //week.monday.firstEvent = lunchSuggestion(week.monday.firstEvent);
        System.out.println(week.toString());
    }

}