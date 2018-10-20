package com.example.jaychou.hackathon3;

/**
 * Created by jaychou on 4/22/17.
 */

/* class Day: a list of Events */

/* class Day: a list of Events */


import java.util.*;

public class Day{
    public Event firstEvent;

    public Day(Event firstEvent){
        this.firstEvent = firstEvent;
    }

    public static Day getDay(List<Course> cs, String day){
        List<Event> l = new ArrayList<Event>();

        if (day.equals("M")){
            for (int i = 0; i < cs.size(); i++){
                Course course = cs.get(i);
                if (course.schedule.monday != null){
                    Event event = new Event();
                    event.startMinSince12 = course.schedule.monday.beginminsince12;
                    event.endMinSince12 = course.schedule.monday.endminsince12;
                    event.course = course;
                    event.building = course.building;
                    event.name = course.name;
                    l.add(event);
                }

            }
        } else if (day.equals("T")){
            for (int i = 0; i < cs.size(); i++){
                Course course = cs.get(i);
                if (course.schedule.tuesday != null){
                    Event event = new Event();
                    event.startMinSince12 = course.schedule.tuesday.beginminsince12;
                    event.endMinSince12 = course.schedule.tuesday.endminsince12;
                    event.course = course;
                    event.building = course.building;
                    event.name = course.name;
                    l.add(event);
                }
            }
        } else if (day.equals("W")){
            for (int i = 0; i < cs.size(); i++){
                Course course = cs.get(i);
                if (course.schedule.wednesday != null){
                    Event event = new Event();
                    event.startMinSince12 = course.schedule.wednesday.beginminsince12;
                    event.endMinSince12 = course.schedule.wednesday.endminsince12;
                    event.course = course;
                    event.building = course.building;
                    event.name = course.name;
                    l.add(event);
                }
            }
        } else if (day.equals("TH")){
            for (int i = 0; i < cs.size(); i++){
                Course course = cs.get(i);
                if (course.schedule.thursday != null){
                    Event event = new Event();
                    event.startMinSince12 = course.schedule.thursday.beginminsince12;
                    event.endMinSince12 = course.schedule.thursday.endminsince12;
                    event.course = course;
                    event.building = course.building;
                    event.name = course.name;
                    l.add(event);
                }
            }
        } else if (day.equals("F")){
            for (int i = 0; i < cs.size(); i++){
                Course course = cs.get(i);
                if (course.schedule.friday != null){
                    Event event = new Event();
                    event.startMinSince12 = course.schedule.friday.beginminsince12;
                    event.endMinSince12 = course.schedule.friday.endminsince12;
                    event.course = course;
                    event.building = course.building;
                    event.name = course.name;
                    l.add(event);
                }
            }
        }

        Collections.sort(l);


        Event firstEvent = null;
        if (l.size() > 1) {
            firstEvent = l.get(0);
            l.get(0).nextEvent = l.get(1);
            l.get(l.size() - 1).previousEvent = l.get(l.size() - 2);
            for (int i = 1; i <= l.size() - 2; i++){
                l.get(i).nextEvent = l.get(i+1);
                l.get(i+1).previousEvent = l.get(i);
            }
        } else if (l.size() == 1){
            firstEvent = l.get(0);
        }

        //get lunch
        firstEvent = Calculations.lunchSuggestion(firstEvent);

        return new Day(firstEvent);

    }

    public String toString(){
        Event current = this.firstEvent;
        String s = "";
        while(current != null){
            s += (current.toString() + "\n");
            current = current.nextEvent;
        }
        return s;
    }
}
