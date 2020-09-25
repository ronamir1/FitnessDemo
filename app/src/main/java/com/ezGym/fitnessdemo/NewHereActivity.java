package com.ezGym.fitnessdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import me.relex.circleindicator.CircleIndicator;

public class NewHereActivity extends AppCompatActivity {

    public void backToMain(View view) {
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        mainIntent.putExtra("start screen", 0);
        startActivity(mainIntent);
    }

    ViewPager viewPager2;
    CircleIndicator circleIndicator;
    ViewAdapter viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_here);

        viewPager2 = (ViewPager) findViewById(R.id.viewPager2);
        circleIndicator = (CircleIndicator) findViewById(R.id.indicator);


        viewAdapter = new ViewAdapter(this);
        viewPager2.setAdapter(viewAdapter);
        circleIndicator.setViewPager(viewPager2);
    }
}