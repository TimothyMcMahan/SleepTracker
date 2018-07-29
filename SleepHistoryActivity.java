package com.example.timothy.sleeptracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.timothy.sleeptracker.files.Utils;

import java.util.ArrayList;
import java.util.List;

public class SleepHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_sleep_history);
        ListView sleepHistoryListView = findViewById(R.id.sleepHistoryListView);

        TextView displayDateTextView = findViewById(R.id.displayDateTextView);
        String selectedDate = getIntent().getStringExtra("SleepTracker.SelectedDate");
        displayDateTextView.setText(selectedDate);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,R.layout.sleep_amount, CalendarActivity.SleepTimes);
        sleepHistoryListView.setAdapter(arrayAdapter);



    }
}
