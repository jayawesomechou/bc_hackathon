package com.example.jaychou.hackathon3;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.pm.PackageManager;
import android.Manifest;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.BaseAdapter;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    public ArrayAdapter<String> adapter;
    private ArrayList<String> namelist;
    private ArrayList<String> schedulelist;
    private ArrayList<String> locationlist;
    private String  bding;


    ArrayList<String> generallist;



    private static final String[] INITIAL_PERMS={
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.READ_CONTACTS
    };

    private static final int INITIAL_REQUEST=1337;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        final String generalfile = "generalTemp2.txt";
        final String courseNamefile ="courseNameTemp2.txt";
        final String courseSchedulefile="courseScheduleTemp2.txt";
        final String courseLocationfile="courseLocationTemp2.txt";

        namelist=new ArrayList<String>();
        schedulelist=new ArrayList<String>();
        locationlist=new ArrayList<String>();
        generallist=new ArrayList<String>();


        readFromFile("courseNameTemp2.txt",namelist);
        readFromFile("courseScheduleTemp2.txt",schedulelist);
        readFromFile("courseLocationTemp2.txt",locationlist);

        for(int i=0;i<namelist.size();i++){
            String s="";
            String s1=namelist.get(i);
            String s2=schedulelist.get(i);
            String s3=locationlist.get(i);
            s=s.concat(s1+" ").concat(" "+s2+" ").concat(" "+s3);
            generallist.add(s);
        }

//        List<Course> l = new ArrayList<Course>();
//        for (int i = 0; i < namelist.size(); i++){
//            AcademicBuilding ab = BuildingUpLocations.getAcademicBuilding(locationlist.get(i));
//            Course c = new Course(namelist.get(i), schedulelist.get(i), ab);
//            l.add(c);
//        }
//        Week week = new Week(l);
//
//        // Test one event
//        Event event = week.monday.firstEvent;
//        String name = event.name;
//        bding = event.building.getBuildingName();


        lv = (ListView) findViewById(R.id.lv);


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, generallist);
        lv.setAdapter(adapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                removeFromFile("courseNameTemp2.txt",namelist,position);
                removeFromFile("courseScheduleTemp2.txt",schedulelist,position);
                removeFromFile("courseLocationTemp2.txt",locationlist,position);
                generallist.remove(position);


                return true;
            }
        });


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent edit = new Intent(MainActivity.this, EditActivity.class);
                String s1=namelist.get(position);
                String s2=schedulelist.get(position);
                String s3=locationlist.get(position);

                edit.putExtra("s1",s1);
                edit.putExtra("s2",s2);
                edit.putExtra("s3",s3);
                edit.putExtra("pos",Integer.toString(position));


                startActivity(edit);
            }
        });

        //test position
        //String s=locationlist.get(0);

        //Toast.makeText(this, s, Toast.LENGTH_SHORT).show();





        if (!canAccessLocation() || !canAccessContacts()) {
            requestPermissions(INITIAL_PERMS, INITIAL_REQUEST);
        }
    }

    private boolean canAccessLocation() {
        return(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION));
    }

    private boolean hasPermission(String perm) {
        return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(perm));
    }

    private boolean canAccessContacts() {
        return(hasPermission(Manifest.permission.READ_CONTACTS));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_show:
                // do stuff here
//                Intent addIntent = new Intent(MainActivity.this, AddActivity.class);
//                startActivityForResult(addIntent, 101);
                Toast.makeText(this, "Show Selected", Toast.LENGTH_SHORT).show();
                Intent map = new Intent(getApplicationContext(), MapActivity.class);
                startActivity(map);
                return true;
            case R.id.menu_add:
                // do stuff here
                Intent add = new Intent(MainActivity.this, AddActivity.class);
                startActivityForResult(add, 102);
                Toast.makeText(this, "add Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_delete:
//                // do stuff here

                Toast.makeText(this, "Delete Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_zhanglang:
                // do stuff here
//                Intent listIntent = new Intent(MainActivity.this, ListActivity.class);
//                startActivityForResult(listIntent, 104);
                Toast.makeText(this, "Zhanglang Selected", Toast.LENGTH_SHORT).show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void saveStringToFile(String fileName, String stringToBeSaved) {
        FileOutputStream fos;
        try {
            fos = openFileOutput(fileName, Context.MODE_PRIVATE | Context.MODE_APPEND);
            fos.write(stringToBeSaved.getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private void readFromFile(String taskname,List<String> list) { //reads from a file and uses StringBuilder to create a large string


        try {


            Scanner scan = new Scanner(openFileInput(taskname));

            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                list.add(line);
//                adapter.notifyDataSetChanged();

            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void removeFromFile(String taskname,List<String> lst, int pos){
        String s = lst.get(pos).toString();
        lst.remove(s);
        adapter.notifyDataSetChanged();
        try {
            PrintStream file = new PrintStream(openFileOutput(taskname, MODE_PRIVATE));
            for (int i = 0; i < lst.size(); i++) {
                file.println(lst.get(i));

            }


            file.close();
        } catch (Exception e) {

        }

    }



}
