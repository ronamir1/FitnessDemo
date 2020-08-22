package com.example.fitnessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LegsActivity extends AppCompatActivity {

    ArrayList<String> exerciseArrList;
    ListView exerciseListView;

    public void backToMain(View view){
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    public void setExerciseList(){
        exerciseArrList = new ArrayList<String>();
        exerciseArrList.add("Squats");
        exerciseArrList.add("Deadlift");
        exerciseArrList.add("Lunges");
        exerciseArrList.add("Bulgarian split squat");
        exerciseArrList.add("Calf raise");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legs2);

        setExerciseList();
        exerciseListView = (ListView) findViewById(R.id.exerciseListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exerciseArrList);
        exerciseListView.setAdapter(arrayAdapter);

        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mainIntent = new Intent(getApplicationContext(), SquatActivity.class);
                startActivity(mainIntent);
            }
        });
    }
}