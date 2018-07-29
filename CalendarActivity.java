package com.example.timothy.sleeptracker;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.timothy.sleeptracker.files.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CalendarActivity extends AppCompatActivity {
    CalendarView calendar;
    public static List<String> sleepAmount;
    public static List<String> sleepDate;
    public static List<String> SleepTimes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_calendar);



        try{
            sleepAmount = Files.readAllLines(Paths.get(MainActivity.sleepAmountFile.getAbsolutePath()),Charset.defaultCharset());
            sleepDate = Files.readAllLines(Paths.get(MainActivity.sleepDateFile.getAbsolutePath()),Charset.defaultCharset());

        }
        catch (IOException e){
            e.printStackTrace();
        }


        calendar = findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                month = month+1;
                String selectedDate = ((month <10 ? "0" + month : month) + "/" + (dayOfMonth < 10 ? "0" + dayOfMonth : dayOfMonth) + "/" + year);
                SleepTimes = findByDate(getApplicationContext(),selectedDate,sleepAmount,sleepDate);

                Intent OpenHistory = new Intent(getApplicationContext(),SleepHistoryActivity.class);
                OpenHistory.putExtra("SleepTracker.SelectedDate",selectedDate);
                startActivity(OpenHistory);
            }
        });
    }
   public static ArrayList<String> findByDate(Context context, String date, List<String> sleepTimes, List<String> sleepDates) {
        ArrayList<String> times = new ArrayList<>();
        Integer i = 0;
        Iterator it = sleepDates.iterator();
        while (it.hasNext()) {
            if ( it.next().equals(date)){
                times.add(sleepTimes.get(i)+"");
            }
            i++;
        }
        return times;
    }
}
