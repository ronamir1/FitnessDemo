package com.example.fitnessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ImageView entry;
    TableLayout tableLayout;

    public void legsMain(View view){
        String[] legsExercises = {"1. Squats", "2. Deadlift", "3. Lunges", "4. Bulgarian split squat", "5. Calf raise"};
        String[] exerciseInfo = {"You should", "You should", "You should", "You should", "You should"};
        ArrayList <String> exerciseArrList = new ArrayList<String>(Arrays.asList(legsExercises));
        ArrayList <String> exerciseArrInfo = new ArrayList<String>(Arrays.asList(exerciseInfo));
        Intent legsIntent = new Intent(getApplicationContext(), absActivity.class);
        legsIntent.putExtra("exercises", exerciseArrList);
        legsIntent.putExtra("info", exerciseArrInfo);
        legsIntent.putExtra("description", "Leg Day");
        startActivity(legsIntent);
    }

    public void absMain(View view){
        String[] absDrills = {"1. Static upper", "2. Legs raise", "3. Marine leg raise", "4. Bicycle, 5. Side crunches"};
        String[] exerciseInfo = {"You should", "You should", "You should", "You should", "You should"};
        ArrayList <String> exerciseArrList = new ArrayList<String>(Arrays.asList(absDrills));
        ArrayList <String> exerciseArrInfo = new ArrayList<String>(Arrays.asList(exerciseInfo));
        Intent absIntent = new Intent(getApplicationContext(), absActivity.class);
        absIntent.putExtra("exercises", exerciseArrList);
        absIntent.putExtra("info", exerciseArrInfo);
        absIntent.putExtra("description", "Abs & Core_day");
        startActivity(absIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entry = findViewById(R.id.entry);
        tableLayout = findViewById(R.id.tableLayout);
        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                entry.animate().alpha(0f).setDuration(2000);
                tableLayout.setVisibility(View.VISIBLE);
                tableLayout.animate().alpha(1f).setDuration(2000);
            }
        }.start();
    }
}