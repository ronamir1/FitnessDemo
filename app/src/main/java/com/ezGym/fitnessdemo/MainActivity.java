package com.ezGym.fitnessdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    ImageView entry;
    ScrollView scrollView;
    static DatabaseAccess databaseAccess;
    final String DAILY_TIP = "Daily tip:";
    final String LINE_DROP = "\n";

    final String ABOUT_MSG = "This app was built by Ron Amir and Sagi Dekel.\n\n" +
            "The main purpose of this app is to guide gym beginners how to start working out with less probability of being injured due to inexperience (with some exercises or with the fitness world).\n" +
            "We spent most of our life in the gym without the right guidance, we hope this app can be your guide for an easier walk into the fitness world!\n\n" +
            "This app is not a replacement for a gym instructor, for each exercise you are not sure about you should go to them and make a double check on it.\nWe do not take responsibility if any of our users will cause himself an injury.\n\n" +
            "We Created our free logo at LogoMakr.com\n\nYou can contact us on ezgym.contact@gmail.com" +
            "\n\n intensity icon was made using icons Designed by Freepik, <a href='https://www.freepik.com/vectors/infographic'>Infographic vector created by ibrandify - www.freepik.com</a>";

    final String tip1 = "Drink at least 3 liters of water every day";
    final String tip2 = "Eat at least 1 gram of protein for each kg you weigh\ni.e a 70kg men should eat at least 70 gram of protein a day (don't forget to hydrate enough)";
    final String tip3 = "It is OK to take a day off to rest and gather strength for the next workout";
    final String tip4 = "Eating veggies and fruits is important, make sure you eat a few every day!";
    final String tip5 = "Drinking black coffee, eating a banana or a date with some nut might improve your workout, try it!";
    final String tip6 = "A good night sleep is essential, make sure you get at least 7-8 hours of sleep every day";
    final String tip7 = "Good posture and strong core can improve your deadlift, squat and many more exercises so don't neglect it";
    final String tip8 = "Warm up before each workout to reduce the risk of an injury";
    final String tip9 = "Nutrition menu should be given by a professional only!";
    final String tip10 = "There is no such thing as local fat loss (i.e. only in your arms or abs)";
    final String tip11 = "Stressed mind can stress your body, make sure to relax sometimes";
    final String tip12 = "Every exercise you are not sure about - ask your gym instructor they will be happy to help!";
    final String tip13 = "Change doesn't come in a week, patience and consistency are key players";
    final String tip14 = "you should NOT feel pain in any exercise, if you do feel pain you can ask the gym instructor for replacements.";
    final String tip15 = "No one cares how much you lift if you don't do it in the right form, so take your time with increasing weight.";
    final String tip16 = "Try to keep your core engaged in all of the exercises - this will stabilize you and will help you achieve more quality reps.";
    final String tip17 = "Quality > Quantity\nSo in each exercise try to focus on the contraction of muscles.";
    String[] dailyTips = {tip1, tip2, tip3, tip4, tip5, tip6, tip7, tip8, tip9, tip10, tip11, tip12, tip13, tip14, tip15, tip16, tip17};
    Random rand = new Random();

    public void newHereActivity(MenuItem item) {
        Intent newIntent = new Intent(getApplicationContext(), NewHereActivity.class);
        startActivity(newIntent);
    }

// if we decide to create profile again
//    public void openProfile(MenuItem item){
//        Intent newIntent = new Intent(getApplicationContext(), ProfileActivity.class);
//        startActivity(newIntent);
//    }

    public void aboutMessage(MenuItem item) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(ABOUT_MSG).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        }).show();
    }

    public void muscleGroupMain(View view) {
        int muscleGroup = Integer.parseInt(view.getTag().toString());
        Intent muscleGIntent = new Intent(getApplicationContext(), MuscleGroupActivity.class);
        muscleGIntent.putExtra("muscle group", muscleGroup);
        startActivity(muscleGIntent);
    }

    public void directions(MenuItem item) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setMessage("Each row contains a workout, we recommend to start with the first muscle group ,then the second one.\n(left button then the right one)");
        alertDialog.setView(R.layout.layout_dialog).
                setPositiveButton("Got It!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("EZ GYM");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseAccess = DatabaseAccess.getInstance(this);
        final int todayTip = rand.nextInt(dailyTips.length);
        entry = findViewById(R.id.entry);
        scrollView = findViewById(R.id.scroll);

        // fading out opening screen
        Intent intent = getIntent();
        int isStartScreen = intent.getIntExtra("start screen", -1);
        if (isStartScreen != 0) {
            new CountDownTimer(3410, 1000) {
                @Override
                public void onTick(long l) {
                }

                @Override
                public void onFinish() {
                    entry.animate().alpha(0f).setDuration(2000);
                    scrollView.setVisibility(View.VISIBLE);
                    scrollView.animate().alpha(1f).setDuration(2000);
                }
            }.start();

            // a daily tip toast during waiting time till opening screen fades out
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Toast toast = Toast.makeText(getApplicationContext(), DAILY_TIP + LINE_DROP + dailyTips[todayTip],
                            Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.setDuration(Toast.LENGTH_LONG);
                    toast.show();
                }
            }, 250);
        } else {
            entry.setAlpha(0f);
            scrollView.setVisibility(View.VISIBLE);
            scrollView.setAlpha(1f);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }
}