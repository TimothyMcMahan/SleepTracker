package com.example.timothy.sleeptracker;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.timothy.sleeptracker.Classes.SkillsAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {
    ArrayList<Drawable> stairwayArray = new ArrayList<>();
    ArrayList<Drawable> room_1_Array = new ArrayList<>();
    ArrayList<Drawable> room_2_Array = new ArrayList<>();
    ListView skills_ListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        skills_ListView = findViewById(R.id.skills_ListView);

        stairwayArray.add(getDrawable(R.drawable.stairway_image));
        stairwayArray.add(getDrawable(R.drawable.stairway_2_image));
        room_1_Array.add(getDrawable(R.drawable.bedroom_image));
        room_1_Array.add(getDrawable(R.drawable.kitchen_image));
        room_2_Array.add(getDrawable(R.drawable.bathroom_image));
        room_2_Array.add(null);

        SkillsAdapter skillsAdapter = new SkillsAdapter(this,stairwayArray,room_1_Array,room_2_Array);
        skills_ListView.setAdapter(skillsAdapter);


    }
}
