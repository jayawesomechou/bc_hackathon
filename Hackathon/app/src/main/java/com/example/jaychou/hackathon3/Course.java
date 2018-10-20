package com.example.jaychou.hackathon3;

/**
 * Created by jaychou on 4/22/17.
 */

/* The Course Class

*//* The Course Class

*/
/* The Course Class

*/

public class Course{
    public String name;
    public Schedule schedule;
    public AcademicBuilding building;

    public class Schedule{
        public Time monday;
        public Time tuesday;
        public Time wednesday;
        public Time thursday;
        public Time friday;

        public Schedule(){
            this.monday = null;
            this.tuesday = null;
            this.wednesday = null;
            this.thursday = null;
            this.friday = null;
        }
    }

    public class Time{
        public int beginminsince12;
        public int endminsince12;

        public Time(int bm, int em){
            beginminsince12 = bm;
            endminsince12 = em;
        }
    }

    public Course(String name, String schedule, AcademicBuilding building){

        this.name = name;

        int length = 0;
        if (schedule.charAt(schedule.length() - 1) == '*'){
            length = 1;
            schedule = schedule.substring(0, schedule.length() - 1);
        }
        else if (schedule.contains("-")){
            length = 2;
        }
        String[] sche = schedule.split(" ");

        int i = 0;
        while (!isNumeric(sche[i]))	i++;
        int bh = Integer.parseInt(sche[i]);
        int bm = 0;
        if ((i < sche.length - 1) && (isNumeric(sche[i + 1]))) bm = Integer.parseInt(sche[i + 1]);
        int begintime = bh * 60 + bm;
        if (bh < 8) begintime = begintime + 720;
        int endtime = begintime + 50;
        if (length == 1){
            endtime = begintime + 75;
        } else if (length == 2){
            endtime = begintime + 140;
        }

        for (int j = 0; j < 3; j++){
            if (!isNumeric(sche[j])){
                if (sche[j].equals("M")){
                    Time monday = new Time(begintime, endtime);
                    if (this.schedule == null){
                        Schedule sc = new Schedule();
                        sc.monday = monday;
                        this.schedule = sc;
                    } else {
                        this.schedule.monday = monday;
                    }
                } else if (sche[j].equals("T")){
                    Time tuesday = new Time(begintime, endtime);
                    if (this.schedule == null){
                        Schedule sc = new Schedule();
                        sc.tuesday = tuesday;
                        this.schedule = sc;
                    } else {
                        this.schedule.tuesday = tuesday;
                    }
                } else if (sche[j].equals("W")){
                    Time wednesday = new Time(begintime, endtime);
                    if (this.schedule == null){
                        Schedule sc = new Schedule();
                        sc.wednesday = wednesday;
                        this.schedule = sc;
                    } else {
                        this.schedule.wednesday = wednesday;
                    }
                } else if (sche[j].equals("TH")){
                    Time thursday = new Time(begintime, endtime);
                    if (this.schedule == null){
                        Schedule sc = new Schedule();
                        sc.thursday = thursday;
                        this.schedule = sc;
                    } else {
                        this.schedule.thursday = thursday;
                    }
                } else if (sche[j].equals("F")){
                    Time friday = new Time(begintime, endtime);
                    if (this.schedule == null){
                        Schedule sc = new Schedule();
                        sc.friday = friday;
                        this.schedule = sc;
                    } else {
                        this.schedule.friday = friday;
                    }
                }
            }
        }

        this.building = building;


    }

    private static boolean isNumeric(String s){
        return ((s.charAt(0) <= '9') && (s.charAt(0) >= '0'));
    }

    public String toString(){
        return this.name;
    }


}




