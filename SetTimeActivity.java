package com.example.timothy.sleeptracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SetTimeActivity extends AppCompatActivity {
    TimePicker timePicker;
    public static String time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_time);

        Button setTimeBtn = findViewById(R.id.setTimeBtn);

        setTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePicker timePicker = findViewById(R.id.timePicker);
                int Hour;
                int Minutes;
                String  AM_PM;

                if (timePicker.getHour() == 0) {
                    Hour = 12;
                    AM_PM = "AM";
                }
                else if (timePicker.getHour() < 12){
                    Hour = timePicker.getHour();
                    AM_PM = "AM";
                }
                else if (timePicker.getHour() == 12){
                    Hour = timePicker.getHour();
                    AM_PM = "PM";
                }
                else{
                    Hour = timePicker.getHour() - 12;
                    AM_PM = "PM";
                }

                Minutes = timePicker.getMinute();

                time = String.format("%02d",Hour) + ":" + String.format("%02d",Minutes) + " " + AM_PM;

                Intent returnToHome = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(returnToHome);
            }
        });

    }
}
