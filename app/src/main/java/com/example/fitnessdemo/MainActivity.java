package com.example.fitnessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void legsMain(View view){
        Intent legsIntent = new Intent(getApplicationContext(), LegsActivity.class);
        startActivity(legsIntent);
    }

    public void absMain(View view){
        Intent legsIntent = new Intent(getApplicationContext(), absActivity.class);
        startActivity(legsIntent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}