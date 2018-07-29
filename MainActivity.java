package com.example.timothy.sleeptracker;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.timothy.sleeptracker.files.Utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity{

    public static Date startTime;
    public static Date endTime;
    public static String startDate;
    public static String timeAsleep;
    public static Boolean push=false;
    public static File sleepAmountFile;
    public static File sleepDateFile;
    FloatingActionButton asleepStartTimeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);

        //create 2 files that will store the sleep amounts and dates.
        sleepAmountFile = new File(getFilesDir(), "sleepAmountFile");
        sleepDateFile = new File(getFilesDir(), "sleepDateFile");
        try {
            sleepAmountFile.createNewFile();
            sleepDateFile.createNewFile();

        }
        catch (IOException e){
            e.printStackTrace();
        }

        //set the timeAsleepTextView to equal the time asleep
        if (timeAsleep != null) {
            TextView timeAsleepTextView = findViewById(R.id.timeAsleepTextView);
            timeAsleepTextView.setText(timeAsleep);
        }

        //Initialize the TextClock with the correct format
        TextClock currentTimeTextClock = findViewById(R.id.currentTimeTextClock);
        currentTimeTextClock.setFormat12Hour("hh:mm aa");

        //Create onClickListener to move to the SetTimeActivity upon click of Set button
        Button setBtn = findViewById(R.id.setBtn);
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent SetTimeIntent = new Intent(getApplicationContext(), SetTimeActivity.class);
                startActivity(SetTimeIntent);
            }
        });
        // if the time has been set via SetTimeActivity then set the TextView to the time.
        if (SetTimeActivity.time != null) {
            TextView setTimeTextView = findViewById(R.id.setTimeTextView);
            setTimeTextView.setText(SetTimeActivity.time);
        }

        //===========================================SLEEP TRACKER=====================================
        //create onClickListener for the Zz button to get the date and time at the time of the button press
        asleepStartTimeBtn = findViewById(R.id.asleepStartTimeBtn);
        asleepStartTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!push) {
                    push = true;

                    SimpleDateFormat dateformatter = new SimpleDateFormat("MM/dd/yyyy");
                    Date date = new Date();
                    startTime = new Date();

                    startDate = (dateformatter.format(date));
                    Utils.changeToTheme(MainActivity.this, Utils.THEME_DARK);
                    timeAsleep = "";
                }
                else {
                    asleepStartTimeBtn.setImageTintList(getColorStateList(R.color.sleepBlue));
                    push = false;

                    startTime = null;
                    startDate = null;
                    Utils.changeToTheme(MainActivity.this, Utils.THEME_DEFAULT);

                }

            }
        });

        //create onClickListener for sun button to get the time at the time of click
        //call the timeAsleep method created below to calculate the amount of time asleep based on start and end time
        ImageButton asleepEndTimeBtn = findViewById(R.id.asleepEndTimeBtn);
        asleepEndTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (push) {            //ensures that the Zz button was pressed first
                    endTime = new Date();

                    timeAsleep = (findTimeAsleep(startTime, endTime));

                    //write the timeAsleep and the startDate to their own respective files.
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(sleepAmountFile, true));
                        writer.newLine();
                        writer.write(timeAsleep);
                        writer.close();

                        writer = new BufferedWriter(new FileWriter(sleepDateFile, true));
                        writer.newLine();
                        writer.write(startDate);
                        writer.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), "File Not Appended ", Toast.LENGTH_LONG).show();
                    }

                    push = false;
                    Utils.changeToTheme(MainActivity.this, Utils.THEME_DEFAULT);

                }

            }
        });

        Button goToCalendarBtn = findViewById(R.id.calendarBtn);
        goToCalendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoToCalendar = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(GoToCalendar);
            }
        });

        Button goToHomePageBtn = findViewById(R.id.homeBtn);
        goToHomePageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToHomePage = new Intent(getApplicationContext(),HomePageActivity.class);
                startActivity(goToHomePage);
            }
        });

    }
    //method that calculates the time asleep based on the start and end time asleep from button presses
    //returns a string of the formatted difference.
    public static String findTimeAsleep(Date startTime,Date endTime){
        long difference = 0;
        int hours=0;
        int minutes=0;
        SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm aa");


        difference = endTime.getTime() - startTime.getTime();
        int timeInSeconds = (int)difference / 1000;
        hours = timeInSeconds / 3600;
        timeInSeconds = timeInSeconds - (hours * 3600);
        minutes = timeInSeconds / 60;

        String actualTime = (timeFormatter.format(startTime) + "-" + timeFormatter.format(endTime));
        String differenceInTime = ((hours) + " h & " + (minutes) + " min");//HH:mm;
        String sleepAmount = (actualTime + " : " + differenceInTime);

        return  sleepAmount;
    }

}
