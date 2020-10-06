package com.ezGym.fitnessdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import me.relex.circleindicator.CircleIndicator;

public class full_body extends AppCompatActivity {

    public void backToMain(View view) {
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        mainIntent.putExtra("start screen", 0);
        startActivity(mainIntent);
    }

    ViewPager viewPager2;
    CircleIndicator circleIndicator;
    ViewAdapter2 viewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_body);

        viewPager2 = (ViewPager) findViewById(R.id.viewPager3);
        circleIndicator = (CircleIndicator) findViewById(R.id.indicator);

        Intent intent = getIntent();
        int muscleGroup = intent.getIntExtra("muscle group", -1);
        viewAdapter = new ViewAdapter2(this, muscleGroup);
        viewPager2.setAdapter(viewAdapter);
        circleIndicator.setViewPager(viewPager2);
    }
}