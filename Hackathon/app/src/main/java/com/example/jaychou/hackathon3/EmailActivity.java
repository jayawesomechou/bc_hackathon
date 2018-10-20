package com.example.jaychou.hackathon3;

/**
 * Created by jaychou on 4/22/17.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.*;
import android.widget.*;
import android.content.*;
import android.net.*;
import android.view.*;

import org.w3c.dom.Text;

public class EmailActivity extends AppCompatActivity{
    private EditText et1,et2;
    private TextView tv0,tv1,tv2;
    private String course,professor,address,subject,body;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);


        final Intent i = getIntent();

        course=i.getStringExtra("course");

        et1= (EditText) findViewById(R.id.et1);
        et2= (EditText) findViewById(R.id.et2);



        et1.setText(professor);
        et2.setText(address);



        Toast.makeText(getApplicationContext(), course, Toast.LENGTH_LONG).show();

        professor=generateprofessor();
        address=generateaddress();
        subject=generatetitle();
        body=generatebody();
    }

    public String generatetitle(){

        return "Apologize for unable to attend to class";
    }

    public String generatebody(){

        return "Dear Professor"+" "+professor+","+ "\n" +"\n"+
                "   I am afraid that I might not be able to attend to your "+course+
                " class today due to fact that I got a severe stomache last night. "+
                "If possible, please give me the details of the assignment and homework to be assigned this week, "+
                "so I can finish my homework on time.\n" +
                "\n"+" Thank you very much for understanding.\n" +
                "\n" +
                "\n" +
                "Sincerely,\n" +
                "Jay";
    }


    public void send(View view){
        Toast.makeText(this, "send", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Intent.ACTION_SENDTO)
                .setData(new Uri.Builder().scheme("mailto").build())
                .putExtra(Intent.EXTRA_EMAIL, new String[]{ address })
                .putExtra(Intent.EXTRA_SUBJECT, subject)
                .putExtra(Intent.EXTRA_TEXT, body)
                ;

        ComponentName emailApp = intent.resolveActivity(getPackageManager());
        ComponentName unsupportedAction = ComponentName.unflattenFromString("com.android.fallback/.Fallback");
        if (emailApp != null && !emailApp.equals(unsupportedAction))
            try {
                // Needed to customise the chooser dialog title since it might default to "Share with"
                // Note that the chooser will still be skipped if only one app is matched
                Intent chooser = Intent.createChooser(intent, "Send email with");
                startActivity(chooser);
                return;
            }
            catch (ActivityNotFoundException ignored) {
            }

        Toast
                .makeText(this, "Couldn't find an email app and account", Toast.LENGTH_LONG)
                .show();



    }



    public void back(View view){
        Intent back=new Intent(EmailActivity.this, MainActivity.class);
        startActivity(back);

    }


    public String generateprofessor(){
        if(course.equals("Mobil App")){
            return "Signorile";
        }
        else if(course.equals("Cryptography")){
            return "Straubin";
        }

        else if(course.equals("Computer Orgnization")){
            return "Signorile";
        }

        else if(course.equals("Programming Languages")){
            return "Muller";
        }

        else if(course.equals("Artificial Intelligence")){
            return "Alvarez";
        }
        else if(course.equals("Laotulv Luguan")){
            return "Zhanglang";
        }
        else {
            return "_enter_professor_name_";
        }




    }

    public String generateaddress(){
        if(course.equals("Mobil App")){
            return "Signorile <signoril@bc.edu>";
        }
        else if(course.equals("Cryptography")){
            return "Straubin <straubin@bc.edu>";
        }

        else if(course.equals("Computer Orgnization")){
            return "Signorile <signoril@bc.edu>";
        }

        else if(course.equals("Programming Languages")){
            return "Muller <muller@bc.edu>";
        }

        else if(course.equals("Artificial Intelligence")){
            return "Alvarez <alvarez@bc.edu>";
        }
        else if(course.equals("Laotulv Luguan")){
            return "Andre_Roach <weizb@bc.edu>";
        }
        else {
            return "_enter_mail_address_  ";
        }


    }





}
