package com.example.jaychou.hackathon3;

/**
 * Created by jaychou on 4/22/17.
 */
import java.util.ArrayList;
import java.util.Hashtable;

public class BuildingUpLocations {

    private static final ArrayList<AcademicBuilding> academicBuildingsList;
    private static final ArrayList<DiningHall> diningHallsList;
    private static final Hashtable<String,AcademicBuilding> abhashtable;
    private static final Hashtable<String,Location> dhhashtable;

    static {
        academicBuildingsList = new ArrayList<AcademicBuilding>();

        abhashtable = new Hashtable<String,AcademicBuilding>();
        dhhashtable = new Hashtable<String,Location>();

        AcademicBuilding gassonhall =  new AcademicBuilding("Gasson", new Location(42.335573, -71.170486));
        academicBuildingsList.add(gassonhall);
        abhashtable.put("Gasson", gassonhall);

        AcademicBuilding maloneyhall =  new AcademicBuilding("Maloney", new Location(42.336108, -71.168435));
        academicBuildingsList.add(maloneyhall);
        abhashtable.put("Maloney",maloneyhall );

        AcademicBuilding oneilhall =  new AcademicBuilding("O'Neil", new Location(42.336072, -71.169272));
        academicBuildingsList.add(oneilhall);
        abhashtable.put("O'Neil", oneilhall);

        AcademicBuilding stmaryhall =  new AcademicBuilding("St. Mary", new Location(42.336847, -71.170466));
        academicBuildingsList.add(stmaryhall);
        abhashtable.put("St. Mary", stmaryhall);

        AcademicBuilding devlinhall =  new AcademicBuilding("Devlin", new Location(42.336847, -71.170466));
        academicBuildingsList.add(devlinhall);
        abhashtable.put("Devlin",devlinhall );

        AcademicBuilding lyonshall =  new AcademicBuilding("Lyons", new Location(42.334967, -71.171043));
        academicBuildingsList.add(lyonshall);
        abhashtable.put("Lyons", lyonshall);

        AcademicBuilding fultonhall =  new AcademicBuilding("Fulton", new Location(42.334485, -71.169984));
        academicBuildingsList.add(fultonhall);
        abhashtable.put("Fulton", fultonhall);

        AcademicBuilding higginshall =  new AcademicBuilding("Higgins", new Location(42.335022, -71.168812));
        academicBuildingsList.add(higginshall);
        abhashtable.put("Higgins", higginshall );

        AcademicBuilding stokesnorth =  new AcademicBuilding("Stokes North", new Location(42.334594, -71.171186));
        academicBuildingsList.add(stokesnorth);
        abhashtable.put("Stokes North", stokesnorth);

        AcademicBuilding stokessouth =  new AcademicBuilding("Stokes South", new Location(42.334009, -71.171334));
        academicBuildingsList.add(stokessouth);
        abhashtable.put("Stokes South", stokessouth);

        AcademicBuilding carneyhall =  new AcademicBuilding("Carney", new Location(42.333566, -71.170651));
        academicBuildingsList.add(carneyhall);
        abhashtable.put("Carney", carneyhall );


        AcademicBuilding mcguinnhall =  new AcademicBuilding("McGuinn", new Location(42.333602, -71.169727));
        academicBuildingsList.add(mcguinnhall);
        abhashtable.put("McGuinn",mcguinnhall );

        AcademicBuilding campionhall =  new AcademicBuilding("Campion", new Location(42.333808, -71.168663));
        academicBuildingsList.add(campionhall);
        abhashtable.put("Campion", campionhall);


        AcademicBuilding cushinghall =  new AcademicBuilding("Cushing", new Location(42.333808, -71.168663));
        academicBuildingsList.add(cushinghall);
        abhashtable.put("Cushing", cushinghall);

        AcademicBuilding merkerthall =  new AcademicBuilding("Merkert", new Location(42.333887, -71.167247));
        academicBuildingsList.add(merkerthall);
        abhashtable.put("Merkert", merkerthall);

        AcademicBuilding robsham =  new AcademicBuilding("Robsham", new Location(42.337775, -71.168174));
        academicBuildingsList.add(robsham);
        abhashtable.put("Robsham", robsham);


        diningHallsList = new ArrayList<DiningHall>();

        DiningHall mac = new DiningHall("Mac", new Location(42.333339, -71.171893),"This dining hall is next to Carney and Stokes South.");
        diningHallsList.add(mac);
        dhhashtable.put("Mac", new Location(42.333339, -71.171893));


        DiningHall hillside = new DiningHall("Hillside", new Location(42.336313, -71.168556),"This dining hall is located on the first floor of Maloney. Mealplan not accpeted.");
        diningHallsList.add(hillside);
        dhhashtable.put("Hillside", new Location(42.336313, -71.168556));



        DiningHall thebeancounter = new DiningHall("The Bean Counter", new Location(42.334489, -71.170198),"This food shop is located on the first floor of Fulton. Mealplan not accpeted.");
        diningHallsList.add(thebeancounter);
        dhhashtable.put("The Bean Counter", new Location(42.334489, -71.170198));

        DiningHall chocolatebar = new DiningHall("The Chocolate Bar", new Location(42.334030, -71.171324),"This dining hall is located on the first floor of Stokes South. Mealplan not accpeted.");
        diningHallsList.add(chocolatebar);
        dhhashtable.put("The Chocolate Bar", new Location(42.334030, -71.171324));

        DiningHall therats = new DiningHall("The Rats", new Location(42.334954, -71.171013),"This dining hall is located on the basement floor of Lyons.");
        diningHallsList.add(therats);
        dhhashtable.put("The Rats", new Location(42.334954, -71.171013));

        DiningHall lower = new DiningHall("Lower", new Location(42.338035, -71.167676),"This dining hall is located next to Robsham Theatre.");
        diningHallsList.add(lower);
        dhhashtable.put("Lower", new Location(42.338035, -71.167676));


    }

    public static AcademicBuilding getAcademicBuilding(String building){
        return abhashtable.get(building);
    }

    public static Hashtable<String, Location> getDHHashTable(){
        return dhhashtable;
    }

    private static double distance(Location from, Location to, Location meal) {
        return Calculations.distanceBetween(from, meal) + Calculations.distanceBetween(to, meal);

    }
    public static DiningHall getNearestDiningHall(Location from, Location to) {
        double minimum = distance(from, to, diningHallsList.get(0).getLocation());
        DiningHall nearestDiningHall = (DiningHall) diningHallsList.get(0);
        for (DiningHall e : diningHallsList) {
            double newminimum = distance(from, to, e.getLocation());
            if (newminimum<minimum){
                minimum = newminimum;
                nearestDiningHall = e;
            }
        }

        return nearestDiningHall;


    }




}




