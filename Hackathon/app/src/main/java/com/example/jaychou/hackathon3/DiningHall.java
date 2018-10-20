package com.example.jaychou.hackathon3;

/**
 * Created by jaychou on 4/21/17.
 */

public class DiningHall implements BuildingI {

    private final String buildingName;
    private Location location;
    private static String buildingType = "DiningHall";
    private static String description;

    public DiningHall(String buildingName, Location location, String description){
        this.buildingName = buildingName;
        this.location = location;
        this.description = description;
    }

    public String getBuildingName(){
        return buildingName;
    }

    public String getType(){
        return buildingType;
    }
    public Location getLocation() {return location;}

    public String getDescription(){return description;}


}
