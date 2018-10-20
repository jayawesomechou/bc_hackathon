package com.example.jaychou.hackathon3;

/**
 * Created by jaychou on 4/22/17.
 */



/* The Event Class */

/* The Event Class */

/* The Event Class */


public class Event implements Comparable<Event>{
    public int startMinSince12;
    public int endMinSince12;
    public Course course;
    public BuildingI building;
    public String name;
    public Event previousEvent;
    public Event nextEvent;

    public Event(){
        this.startMinSince12 = 0;
        this.endMinSince12 = 0;
        this.course = null;
        this.building = null;
        this.previousEvent = null;
        this.nextEvent = null;
    }

    public Event(int s, int e, Course c, Event pe, Event ne){
        this.startMinSince12 = s;
        this.endMinSince12 = e;
        this.course = c;
        this.building = c.building;
        this.name = c.name;
        this.previousEvent = pe;
        this.nextEvent = ne;
    }

    public Event(int s, int e, DiningHall building, String name, Event pe, Event ne){
        this.startMinSince12 = s;
        this.endMinSince12 = e;
        this.building = building;
        this.name = name;
        this.previousEvent = pe;
        this.nextEvent = ne;
    }

    public int compareTo(Event other){
        return Integer.compare(this.startMinSince12, other.startMinSince12);
    }

    public String toString(){
        return name + " " + building.getBuildingName();
    }public String getEventName(){
        return name;
    }

    public String getTime(){
        startMinSince12 = (startMinSince12 > 720) ? startMinSince12 - 720 : startMinSince12;
        endMinSince12 = (endMinSince12 > 720) ? endMinSince12 - 720 : endMinSince12;

        return (startMinSince12 / 60) + ":" + (startMinSince12 % 60) + "-" + (endMinSince12 / 60) + ":" + (endMinSince12 % 60);
    }

    public String getBuildingName(){
        return building.getBuildingName();
    }

    public double getEventLat(){
        return building.getLocation().lat;
    }

    public double getEventLng(){
        return building.getLocation().lng;
    }
}