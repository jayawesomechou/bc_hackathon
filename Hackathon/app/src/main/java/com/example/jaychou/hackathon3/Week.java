package com.example.jaychou.hackathon3;

/**
 * Created by jaychou on 4/22/17.
 */
/* The Week Class */

/* The Week Class */

import java.util.*;

public class Week{
    public Day monday;
    public Day tuesday;
    public Day wednesday;
    public Day thursday;
    public Day friday;

    public Week(List<Course> cs){
        this.monday = Day.getDay(cs, "M");
        this.tuesday = Day.getDay(cs, "T");
        this.wednesday = Day.getDay(cs, "W");
        this.thursday = Day.getDay(cs, "TH");
        this.friday = Day.getDay(cs, "F");
    }

    public String toString(){
        String s = "";
        s += "Monday\n" + monday.toString() + "\n";
        s += "Tuesday\n" + tuesday.toString() + "\n";
        s += "Wednesday\n" + wednesday.toString() + "\n";
        s += "Thursday\n" + thursday.toString() + "\n";
        s += "Friday\n" + friday.toString() + "\n";
        return s;
    }

    public List<List<Event>> listView(){
        ArrayList<List<Event>> l = new ArrayList<List<Event>>();
        ArrayList<Event> m = new ArrayList<Event>();
        if (monday.firstEvent == null){
            l.add(m);
        } else {
            Event e = monday.firstEvent;
            while (e != null){
                m.add(e);
                e = e.nextEvent;
            }
            l.add(m);
        }
        ArrayList<Event> t = new ArrayList<Event>();
        if (tuesday.firstEvent == null){
            l.add(t);
        } else {
            Event e = tuesday.firstEvent;
            while (e != null){
                t.add(e);
                e = e.nextEvent;
            }
            l.add(t);
        }
        ArrayList<Event> w = new ArrayList<Event>();
        if (wednesday.firstEvent == null){
            l.add(w);
        } else {
            Event e = wednesday.firstEvent;
            while (e != null){
                w.add(e);
                e = e.nextEvent;
            }
            l.add(w);
        }
        ArrayList<Event> th = new ArrayList<Event>();
        if (thursday.firstEvent == null){
            l.add(th);
        } else {
            Event e = thursday.firstEvent;
            while (e != null){
                th.add(e);
                e = e.nextEvent;
            }
            l.add(th);
        }
        ArrayList<Event> f = new ArrayList<Event>();
        if (friday.firstEvent == null){
            l.add(f);
        } else {
            Event e = friday.firstEvent;
            while (e != null){
                f.add(e);
                e = e.nextEvent;
            }
            l.add(f);
        }
        return l;
    }

    public static void main(String[] args){
        List<Course> cs = new ArrayList<Course>();
        cs.add(new Course("MATH110101", "M W F 9", null));
        cs.add(new Course("PHYS120001", "M W F 4 30*", null));
        cs.add(new Course("CSCI120001", "M W F 12 30", null));
        cs.add(new Course("CSCI300001", "M W F 3", null));
        cs.add(new Course("MATH880801", "M W F 6", null));
        Week week = new Week(cs);
        System.out.println(week.toString());

    }


}

