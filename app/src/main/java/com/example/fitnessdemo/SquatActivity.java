package com.example.fitnessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SquatActivity extends AppCompatActivity {

    public void backToLegs(View view){
        Intent mainIntent = new Intent(getApplicationContext(), LegsActivity.class);
        startActivity(mainIntent);
    }

    public void backToMain(View view){
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squat);

        ImageView warmUp = findViewById(R.id.splace_image_view);
        ImageView squat = findViewById(R.id.squat);

        Glide.with(this).load(R.drawable.marine_abs_gif).into(warmUp);
        Glide.with(this).load(R.drawable.marine_abs_gif).into(squat);
    }
}