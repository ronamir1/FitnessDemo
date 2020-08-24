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
                secondGif.setVisibility(View.INVISIBLE);
                secondTextView.setVisibility(View.INVISIBLE);
                firstTextView.setText("dips - side");
                Glide.with(this).load(R.drawable.dips).into(firstGif);
        }
    }

    public void shouldersMain(int exercise){
        switch(exercise){
            case 0:
                secondGif.setVisibility(View.INVISIBLE);
                secondTextView.setVisibility(View.INVISIBLE);
                firstTextView.setText("Shoulder press");
                Glide.with(this).load(R.drawable.shoulder_press).into(firstGif);
                break;
            case 1:
            case 2:
        }
    }

    public void bicepsMain(int exercise){
        switch(exercise){
            case 0:
                secondGif.setVisibility(View.INVISIBLE);
                secondTextView.setVisibility(View.INVISIBLE);
                firstTextView.setText("Barbell curls");
                Glide.with(this).load(R.drawable.barbell_curls).into(firstGif);
                break;
            case 1:
                secondGif.setVisibility(View.INVISIBLE);
                secondTextView.setVisibility(View.INVISIBLE);
                firstTextView.setText("French press");
                Glide.with(this).load(R.drawable.french_press).into(firstGif);
                break;
            case 2:
        }
    }

    public void absMain(int exercise){
        secondGif.setVisibility(View.INVISIBLE);
        secondTextView.setVisibility(View.INVISIBLE);
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