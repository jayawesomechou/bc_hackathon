package com.example.jaychou.hackathon3;

/**
 * Created by jaychou on 4/21/17.
 */
public class AcademicBuilding implements BuildingI {

    private String buildingName;
    private Location location;
    private static String buildingType = "Academic";

    public AcademicBuilding (String buildingName, Location location){
        this.buildingName = buildingName;
        this.location = location;
    }
    public String getBuildingName(){
        return buildingName;
    }

    public Location getLocation() {return location;}


    public String getType(){
        return buildingType;
    }

    public String getDescription(){
        return "Academic Building";
    };

}