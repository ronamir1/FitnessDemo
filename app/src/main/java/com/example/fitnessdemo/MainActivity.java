package com.example.fitnessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public void legsMain(View view){
        String[] legsDrills = {"1. Squats", "2. Deadlift", "3. Lunges", "4. Bulgarian split squat", "5. Calf raise"};
        ArrayList <String> exerciseArrList = new ArrayList<String>(Arrays.asList(legsDrills));
        Intent legsIntent = new Intent(getApplicationContext(), absActivity.class);
        legsIntent.putExtra("Drills", exerciseArrList);
        legsIntent.putExtra("description", "Leg Day");
        startActivity(legsIntent);
    }

    public void absMain(View view){
        String[] absDrills = {"1. Static upper", "2. Legs raise", "3. Marine leg raise", "4. Bicycle, 5. Side crunches"};
        ArrayList <String> exerciseArrList = new ArrayList<String>(Arrays.asList(absDrills));
        Intent absIntent = new Intent(getApplicationContext(), absActivity.class);
        absIntent.putExtra("Drills", exerciseArrList);
        absIntent.putExtra("description", "Abs & Core_day");
        startActivity(absIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}