package com.example.fitnessdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView entry;
    TableLayout tableLayout;
    TextView dailyTip;
    final String DAILY_TIP = "Daily tip:";
    final String LINE_DROP = "\n";
    String[] dailyTips = {"Drink at least 2 cups of water upon waking up in the morning",
            "Eat at least 1 gram of protein for each kg you weigh\ni.e a 70kg men should eat at least 70 gram of protein a day",
            "It is OK to take a day off to rest and gather strength for the next workout"};
    Random rand = new Random();

    public void newHereActivity(View view){
        Intent newIntent = new Intent(getApplicationContext(), NewHereActivity.class);
        startActivity(newIntent);
    }

    public void muscleGroupMain(View view) {
        Intent absIntent = new Intent(getApplicationContext(), MuscleGroupActivity.class);
        int muscleGroup = Integer.parseInt(view.getTag().toString());
        absIntent.putExtra("muscle group", muscleGroup);
        startActivity(absIntent);
    }

    public void directions(View view){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setMessage("each row contains a workout, we recommend to start with the first muscle group ,then the second one.\n(left button then the right one)");
        alertDialog.setView(R.layout.layout_dialog).
                setPositiveButton("Got It!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                }).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int todayTip = rand.nextInt(dailyTips.length);
        entry = findViewById(R.id.entry);
        tableLayout = findViewById(R.id.tableLayout);

        // fading out opening screen
        Intent intent = getIntent();
        int isStartScreen = intent.getIntExtra("start screen", -1);
        if (isStartScreen != 0) {
            new CountDownTimer(3000, 1000) {
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

            // a daily tip toast during waiting time till opening screen fades out
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(getApplicationContext(), DAILY_TIP + LINE_DROP + dailyTips[todayTip],
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.TOP, 0, 0);
                    toast.show();
                }
            }, 250);
        }
        else{
            entry.setAlpha(0f);
            tableLayout.setVisibility(View.VISIBLE);
            tableLayout.setAlpha(1f);
        }
    }
}