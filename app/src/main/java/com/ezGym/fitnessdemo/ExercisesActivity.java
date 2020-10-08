package com.ezGym.fitnessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.HashMap;

public class ExercisesActivity extends AppCompatActivity {

    final static String LINE_DROP = "\n";
    final static String KNEES_BENT = "Keep your knees a little bit bent.";
    final static String CHEST_BEFORE_SHOULDERS = "Make sure your shoulders are pulled back.";
    final static String CHEST_POINTERS = CHEST_BEFORE_SHOULDERS + LINE_DROP + "Keep your elbows no more than 45 degrees from your body and your core tight (abs and butt contracted).\nIn addition try to squeeze your armpits when pushing.";
    final static String FACE_PULL_POINTERS = "Your wrist should lead the movement not the elbow.";
    final static String OVERHEAD_PRESS_POINTERS = "The press should be over your head, so make sure your movement is not diagonally forward.";
    final static String PRONE_PRESS_POINTERS = "Small weight should do the job, even without any weight this exercise is hard.";
    final static String CORE_ENGAGED = "Make sure your core is engaged.";
    final static String LEGS_POINTER1 = "Keep your back straight.";
    final static String LEGS_POINTER2 = "Let the movement come from the hip joint, not the back.";
    final static String LEGS_POINTER3 = "Keep your toes aligned with your knees when going down.";
    final static String BASIC_STANDING_POINTERS = LINE_DROP + CORE_ENGAGED + LINE_DROP + KNEES_BENT;
    final static String OVERHEAD_BASIC = OVERHEAD_PRESS_POINTERS + BASIC_STANDING_POINTERS;
    final static String CORE_LEGS13 = CORE_ENGAGED + LINE_DROP + LEGS_POINTER1 + LINE_DROP + LEGS_POINTER3;
    final static String CORE_LEGS12 = CORE_ENGAGED + LINE_DROP + LEGS_POINTER1 + LINE_DROP + LEGS_POINTER2;
    final static String CORE_LEGS3 = CORE_ENGAGED + LINE_DROP + LEGS_POINTER3;
static
    Intent intent;
    int muscleGroup;
    TextView display1, display2, exDescription;
    ImageView firstGif, secondGif;
    HashMap<String, String> myHashMap;

    private void createHash(){
        myHashMap.put("CHEST_POINTERS", CHEST_POINTERS);
        myHashMap.put("CHEST_BEFORE_SHOULDERS", CHEST_BEFORE_SHOULDERS);
        myHashMap.put("BASIC_STANDING_POINTERS", BASIC_STANDING_POINTERS);
        myHashMap.put("CORE_LEGS12", CORE_LEGS12);
        myHashMap.put("CORE_LEGS13", CORE_LEGS13);
        myHashMap.put("FACE_PULL_POINTERS", FACE_PULL_POINTERS);
        myHashMap.put("OVERHEAD_BASIC", OVERHEAD_BASIC);
        myHashMap.put("PRONE_PRESS_POINTERS", PRONE_PRESS_POINTERS);
    }

    public void backToLegs(View view) {
        Intent mainIntent = new Intent(getApplicationContext(), MuscleGroupActivity.class);
        mainIntent.putExtra("muscle group", muscleGroup);
        startActivity(mainIntent);
    }

    public void backToMain(View view) {
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    public void showGif(String display1, String gif1, String display2, String gif2, String description){
        this.display1.setText(display1);
        firstGif.setVisibility(View.VISIBLE);
        this.display2.setVisibility(View.INVISIBLE);
        Context context = firstGif.getContext();
        int id = context.getResources().getIdentifier(gif1, "drawable", context.getPackageName());
        Glide.with(this).load(id).into(firstGif);
        if(display2 != null){
            this.display2.setText(display2);
            this.display2.setVisibility(View.VISIBLE);
            secondGif.setVisibility(View.VISIBLE);
            context = secondGif.getContext();
            id = context.getResources().getIdentifier(gif2, "drawable", context.getPackageName());
            Glide.with(this).load(id).into(secondGif);
        }
        if (exDescription != null){
            exDescription.setText(myHashMap.get(description));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        firstGif = findViewById(R.id.splace_image_view);
        secondGif = findViewById(R.id.squat);
        myHashMap = new HashMap<>();
        createHash();

        display1 = findViewById(R.id.GifDisaplay1);
        exDescription = findViewById(R.id.exDescription);
        display2 = findViewById(R.id.GifDisaplay2);

        intent = getIntent();
        muscleGroup = intent.getIntExtra("muscle group", -1);
        String display1 = intent.getStringExtra("display1");
        String gif1 = intent.getStringExtra("gif1");
        String display2 = intent.getStringExtra("display2");
        String gif2 = intent.getStringExtra("gif2");
        String exDescription = intent.getStringExtra("exDescription");
        showGif(display1, gif1, display2, gif2, exDescription);
        firstGif.setVisibility(View.VISIBLE);
        secondGif.setVisibility(View.VISIBLE);
    }
}