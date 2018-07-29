package com.example.timothy.sleeptracker.Classes;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.timothy.sleeptracker.R;

import java.util.ArrayList;

public class SkillsAdapter extends BaseAdapter {
    LayoutInflater mInflator;
    ArrayList<Drawable> stairwayArray;
    ArrayList<Drawable> room_1_Array;
    ArrayList<Drawable> room_2_Array;

    public SkillsAdapter(Context c, ArrayList<Drawable> array_1,ArrayList<Drawable> array_2, ArrayList<Drawable> array_3){
        stairwayArray = array_1;
        room_1_Array = array_2;
        room_2_Array = array_3;
        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return stairwayArray.size();
    }

    @Override
    public Object getItem(int i) {
        return stairwayArray.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = mInflator.inflate(R.layout.skills_listview,null);

        Drawable stair;
        Drawable room_1;

        if (stairwayArray.size() >= i){
            ImageView stairImageView = v.findViewById(R.id.stairImageView);
            stair = stairwayArray.get(i);
            stairImageView.setImageDrawable(stair);
            stairImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        if (room_1_Array.size() >= i){
            ImageView room_1ImageView = v.findViewById(R.id.room_1ImageView);
            room_1 = room_1_Array.get(i);
            room_1ImageView.setImageDrawable(room_1);
            room_1ImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        if (room_2_Array.size() >= i) {
            ImageView room_2ImageView = v.findViewById(R.id.room_2ImageView);
            Drawable room_2;
            room_2 = room_2_Array.get(i);
            room_2ImageView.setImageDrawable(room_2);
            room_2ImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        }
        else{
            ImageView room_2ImageView = v.findViewById(R.id.room_2ImageView);
            room_2ImageView.setImageResource(R.drawable.ic_launcher_background);
        }


        return v;
    }
}
