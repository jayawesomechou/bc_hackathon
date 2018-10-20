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
import java.util.*;
import android.widget.*;
import android.content.Intent;


/**
 * Created by jaychou on 4/21/17.
 */

public class AddActivity extends AppCompatActivity {

    private EditText name,schedule;
    private TextView tv1,tv2,tv3;
    private String s1,s2,s3;
    String [] locations = {"Gasson", "Maloney", "O'Neil","St. Mary","Devlin",
            "Lyons","Fulton","Higgins", "Stokes North","Stokes South", "Carney", "McGuinn",
            "Campion", "Cushing","Merkert", "Robsham" };

    ArrayList<String> locationlist = new  ArrayList<String>(Arrays.asList(locations));

    Spinner abspinner;

    ArrayAdapter<String> dataAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        abspinner = (Spinner) findViewById(R.id.abspinner);

        dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item,
                locationlist);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        abspinner.setAdapter(dataAdapter);

        name = (EditText) findViewById(R.id.et1);
        schedule = (EditText) findViewById(R.id.et2);


    }

    public void add(View view) {
        // do stuff here
        //get the data from the edittext fields
        s1 = name.getText().toString();
        s2 = schedule.getText().toString();
        s3 = abspinner.getSelectedItem().toString();
        saveStringToFile("courseNameTemp2.txt",s1+"\n");
        saveStringToFile("courseScheduleTemp2.txt",s2+"\n");
        saveStringToFile("courseLocationTemp2.txt",s3+"\n");
        Toast.makeText(this, s1+" "+s2+" "+s3, Toast.LENGTH_SHORT).show();

        /*AcademicBuilding ab = BuildingUpLocations.getAcademicBuilding(s3);
        Course c = new Course(s1, s2, ab);
        List<Course> l = new ArrayList<Course>();*/



    }

    public void clear(View view) {
        // do stuff here
        //get the data from the edittext fields

        name.setText("");
        schedule.setText("");
    }

    public void back(View view) {
        // do stuff here
        //get the data from the edittext fields
       Intent back=new Intent(AddActivity.this, MainActivity.class);
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


}
