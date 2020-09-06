package com.example.fitnessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SquatActivity extends AppCompatActivity {

    Intent intent;
    int muscleGroup;
    TextView firstTextView, secondTextView;
    ImageView firstGif ,secondGif;

    public void backToLegs(View view){
        Intent mainIntent = new Intent(getApplicationContext(), MuscleGroupActivity.class);
        mainIntent.putExtra("muscle group", muscleGroup);
        startActivity(mainIntent);
    }

    public void backToMain(View view){
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    public void oneGifOnly(){
        secondGif.setVisibility(View.INVISIBLE);
        secondTextView.setVisibility(View.INVISIBLE);
    }

    public void chestMain(int exercise){
        switch(exercise){
            case 0:
                firstTextView.setText("Bench Press");
                secondTextView.setText("Upper bench Press");
                Glide.with(this).load(R.drawable.bench_press).into(firstGif);
                Glide.with(this).load(R.drawable.upper_bench_press).into(secondGif);
                break;
            case 1:
                firstTextView.setText("Dumbbell bench press - side");
                secondTextView.setText("Dumbbell bench press - back");
                Glide.with(this).load(R.drawable.dumbell_bench_press).into(firstGif);
                Glide.with(this).load(R.drawable.dumbell_bench_press_back).into(secondGif);
                break;
            case 2:
                firstTextView.setText("Dips - side");
                secondTextView.setText("Dips - back");
                Glide.with(this).load(R.drawable.dips_side).into(firstGif);
                Glide.with(this).load(R.drawable.dips_back).into(secondGif);
                break;
            case 3:
                oneGifOnly();
                firstTextView.setText("Cable crossover");
                Glide.with(this).load(R.drawable.cable_crossover).into(firstGif);
                break;
        }
    }

    public void shouldersMain(int exercise){
        switch(exercise){
            case 0:
                oneGifOnly();
                firstTextView.setText("Shoulder press");
                Glide.with(this).load(R.drawable.shoulder_press).into(firstGif);
                break;
            case 1:
                oneGifOnly();
                firstTextView.setText("Face pull");
                Glide.with(this).load(R.drawable.face_pull).into(firstGif);
                break;
            case 2:
        }
    }

    public void backMain(int exercise){
        switch(exercise){
            case 0:
                firstTextView.setText("Pull up - side");
                secondTextView.setText("Pull up - back");
                Glide.with(this).load(R.drawable.pull_up_side).into(firstGif);
                Glide.with(this).load(R.drawable.pull_up_back).into(secondGif);
                break;
            case 1:
                firstTextView.setText("Chin up - side");
                secondTextView.setText("Chin up - back");
                Glide.with(this).load(R.drawable.chin_up_side).into(firstGif);
                Glide.with(this).load(R.drawable.chin_up_back).into(secondGif);
                break;
            case 2:
                firstTextView.setText("Barbell row - side");
                secondTextView.setText("Barbell row - front");
                Glide.with(this).load(R.drawable.barbell_row_side).into(firstGif);
                Glide.with(this).load(R.drawable.barbell_row_front).into(secondGif);
                break;
            case 3:
                firstTextView.setText("Sitting row - narrow grip");
                secondTextView.setText("Sitting row - wide grip");
                Glide.with(this).load(R.drawable.row_narrow_grip).into(firstGif);
                Glide.with(this).load(R.drawable.row_wide_grip).into(secondGif);
                break;
            case 4:
                oneGifOnly();
                firstTextView.setText("Lat pulldown");
                Glide.with(this).load(R.drawable.lat_pulldown).into(firstGif);
                break;
            case 5:
                firstTextView.setText("One arm high row - right hand");
                secondTextView.setText("One arm high row - left hand");
                Glide.with(this).load(R.drawable.one_arm_high_cable_row_right).into(firstGif);
                Glide.with(this).load(R.drawable.one_arm_high_cable_row_left).into(secondGif);
                break;
        }
    }

    public void bicepsMain(int exercise){
        switch(exercise){
            case 0:
                oneGifOnly();
                firstTextView.setText("Barbell curls");
                Glide.with(this).load(R.drawable.barbell_curls).into(firstGif);
                break;
            case 1:
                oneGifOnly();
                firstTextView.setText("French press");
                Glide.with(this).load(R.drawable.french_press).into(firstGif);
                break;
            case 2:
        }
    }

    public void absMain(int exercise){
        oneGifOnly();
        switch(exercise){
            case 0:
                firstTextView.setText("Static upper");
                Glide.with(this).load(R.drawable.static_upper).into(firstGif);
                break;
            case 1:
                firstTextView.setText("Accordion");
                Glide.with(this).load(R.drawable.accordion).into(firstGif);
                break;
            case 2:
                firstTextView.setText("Leg raise");
                Glide.with(this).load(R.drawable.leg_raise).into(firstGif);
                break;
            case 3:
                firstTextView.setText("Marine leg raise");
                Glide.with(this).load(R.drawable.marine_abs).into(firstGif);

                break;
            case 4:
                firstTextView.setText("Bicycle");
                Glide.with(this).load(R.drawable.bicycle).into(firstGif);
                break;
            case 5:
                firstTextView.setText("Side accordion");
                Glide.with(this).load(R.drawable.side_accordion).into(firstGif);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_squat);

        firstGif = findViewById(R.id.splace_image_view);
        secondGif = findViewById(R.id.squat);

        firstTextView = (TextView)findViewById(R.id.firstGifTextView);
        secondTextView = (TextView)findViewById(R.id.secondGifTextView);

        intent = getIntent();
        int exercise = intent.getIntExtra("exercise", -1);
        muscleGroup = intent.getIntExtra("muscle group", -1);
        firstGif.setVisibility(View.VISIBLE);
        secondGif.setVisibility(View.VISIBLE);
        switch (muscleGroup){
            case MuscleGroupActivity.CHEST:
                chestMain(exercise);
                break;
            case MuscleGroupActivity.SHOULDERS:
                shouldersMain(exercise);
                break;
            case MuscleGroupActivity.BACK:
                backMain(exercise);
                break;
            case MuscleGroupActivity.BICEPS:
                bicepsMain(exercise);
                break;
            case MuscleGroupActivity.LEGS:
            case MuscleGroupActivity.ABS:
                absMain(exercise);
        }
//        Glide.with(this).load(R.drawable.marine_abs_gif).into(firstGif);
//        Glide.with(this).load(R.drawable.marine_abs_gif).into(secondGif);
    }
}