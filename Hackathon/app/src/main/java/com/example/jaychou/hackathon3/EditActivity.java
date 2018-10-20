package com.example.jaychou.hackathon3;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import android.widget.*;
import android.content.Intent;




/**
 * Created by jaychou on 4/21/17.
 */

public class EditActivity extends AppCompatActivity {

    private EditText et1,et2;
    private TextView tv1,tv2,tv3;
    private String s1,s2,s3;
    private String ns1,ns2,ns3;
    private int position;

    private ArrayList<String> namelist;
    private ArrayList<String> schedulelist;
    private ArrayList<String> locationlist;

    String [] locations = {"Gasson", "Maloney", "O'Neil","St. Mary","Devlin",
            "Lyons","Fulton","Higgins", "Stokes North","Stokes South", "Carney", "McGuinn",
            "Campion", "Cushing","Merkert", "Robsham" };

    ArrayList<String> locationlst = new  ArrayList<String>(Arrays.asList(locations));

    Spinner abspinner;

    ArrayAdapter<String> dataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        namelist=new ArrayList<String>();
        schedulelist=new ArrayList<String>();
        locationlist=new ArrayList<String>();

        et1= (EditText) findViewById(R.id.et1);
        et2= (EditText) findViewById(R.id.et2);



        final Intent i = getIntent();

        s1=i.getStringExtra("s1");
        s2=i.getStringExtra("s2");
        s3=i.getStringExtra("s3");


        et1.setText(s1);
        et2.setText(s2);

       position=Integer.parseInt(i.getStringExtra("pos"));


        readFromFile("courseNameTemp2.txt",namelist);
        readFromFile("courseScheduleTemp2.txt",schedulelist);
        readFromFile("courseLocationTemp2.txt",locationlist);



        abspinner = (Spinner) findViewById(R.id.abspinner);

        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                locationlst);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        abspinner.setAdapter(dataAdapter);


    }

    public void update(View view) {
        // do stuff here
        //get the data from the edittext fields

        //remove
        removeFromFile("courseNameTemp2.txt",namelist,position);
        removeFromFile("courseScheduleTemp2.txt",schedulelist,position);
        removeFromFile("courseLocationTemp2.txt",locationlist,position);


        //update
        ns1 = et1.getText().toString();
        ns2 = et2.getText().toString();
        ns3 = abspinner.getSelectedItem().toString();
        saveStringToFile("courseNameTemp2.txt",ns1+"\n");
        saveStringToFile("courseScheduleTemp2.txt",ns2+"\n");
        saveStringToFile("courseLocationTemp2.txt",ns3+"\n");
        Toast.makeText(this, ns1+" "+ns2+" "+ns3, Toast.LENGTH_SHORT).show();

        /*AcademicBuilding ab = BuildingUpLocations.getAcademicBuilding(s3);
        Course c = new Course(s1, s2, ab);
        List<Course> l = new ArrayList<Course>();*/



    }

    public void clear(View view) {
        // do stuff here
        //get the data from the edittext fields

        et1.setText("");
        et2.setText("");
    }

    public void back(View view) {
        // do stuff here
        //get the data from the edittext fields
        Intent back=new Intent(EditActivity.this, MainActivity.class);
        startActivity(back);

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


    private void removeFromFile(String taskname,List<String> lst, int pos){
        String s = lst.get(pos).toString();
        lst.remove(s);
        try {
            PrintStream file = new PrintStream(openFileOutput(taskname, MODE_PRIVATE));
            for (int i = 0; i < lst.size(); i++) {
                file.println(lst.get(i));

            }


            file.close();
        } catch (Exception e) {

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


}
