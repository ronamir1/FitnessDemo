package com.ezGym.fitnessdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ExercisesActivity extends AppCompatActivity {

    final String LINE_DROP = "\n";
    final String KNEES_BENT = "Keep your knees a little bit bent.";
    final String CHEST_BEFORE_SHOULDERS = "Make sure your shoulders are pulled back.";
    final String CHEST_POINTERS = CHEST_BEFORE_SHOULDERS + LINE_DROP + "Keep your elbows no more than 45 degrees from your body and your core tight (abs and butt contracted).\nIn addition try to squeeze your armpits when pushing.";
    final String FACE_PULL_POINTERS = "Your wrist should lead the movement not the elbow.";
    final String OVERHEAD_PRESS_POINTERS = "The press should be over your head, so make sure your movement is not diagonally forward.";
    final String PRONE_PRESS_POINTERS = "Small weight should do the job, even without any weight this exercise is hard.";
    final String CORE_ENGAGED = "Make sure your core is engaged.";
    final String LEGS_POINTER1 = "Keep your back straight.";
    final String LEGS_POINTER2 = "Let the movement come from the hip joint, not the back.";
    final String LEGS_POINTER3 = "Keep your toes aligned with your knees when going down.";
    final String BASIC_STANDING_POINTERS = LINE_DROP + CORE_ENGAGED + LINE_DROP + KNEES_BENT;

    Intent intent;
    int muscleGroup;
    TextView firstTextView, secondTextView;
    ImageView firstGif, secondGif;

    public void backToLegs(View view) {
        Intent mainIntent = new Intent(getApplicationContext(), MuscleGroupActivity.class);
        mainIntent.putExtra("muscle group", muscleGroup);
        startActivity(mainIntent);
    }

    public void backToMain(View view) {
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    public void oneGifOnly() {
        secondGif.setVisibility(View.INVISIBLE);
        secondTextView.setVisibility(View.INVISIBLE);
    }

    public void chestMain(int exercise) {
        switch (exercise) {
            case 0:
                firstTextView.setText("Bench press" + LINE_DROP + CHEST_POINTERS);
                secondTextView.setText("Incline bench press");
                Glide.with(this).load(R.drawable.bench_press).into(firstGif);
                Glide.with(this).load(R.drawable.incline_bench_press).into(secondGif);
                break;
            case 1:
                firstTextView.setText("Dumbbell bench press - side" + LINE_DROP + CHEST_POINTERS);
                secondTextView.setText("Dumbbell bench press - back");
                Glide.with(this).load(R.drawable.dumbell_bench_press).into(firstGif);
                Glide.with(this).load(R.drawable.dumbell_bench_press_back).into(secondGif);
                break;
            case 2:
                firstTextView.setText("Dips - side" + LINE_DROP + CHEST_POINTERS);
                secondTextView.setText("Dips - back");
                Glide.with(this).load(R.drawable.dips_side).into(firstGif);
                Glide.with(this).load(R.drawable.dips_back).into(secondGif);
                break;
            case 3:
                oneGifOnly();
                firstTextView.setText("Cable crossover" + LINE_DROP + CHEST_BEFORE_SHOULDERS);
                Glide.with(this).load(R.drawable.cable_crossover).into(firstGif);
                break;
            case 4:
                oneGifOnly();
                firstTextView.setText("Push up" + LINE_DROP + CHEST_POINTERS);
                Glide.with(this).load(R.drawable.push_up).into(firstGif);
                break;
            case 5:
                firstTextView.setText("Incline push up" + LINE_DROP + "same pointers as the regular push up");
                secondTextView.setText("Decline push up");
                Glide.with(this).load(R.drawable.incline_push_up).into(firstGif);
                Glide.with(this).load(R.drawable.decline_push_up).into(secondGif);
                break;
        }
    }

    public void shouldersMain(int exercise) {
        switch (exercise) {
            case 0:
                firstTextView.setText("Overhead press - barbell" + LINE_DROP + OVERHEAD_PRESS_POINTERS + BASIC_STANDING_POINTERS);
                secondTextView.setText("Overhead press - dumbbell" + LINE_DROP + "Same pointers as the barbell press");
                Glide.with(this).load(R.drawable.overhead_press).into(firstGif);
                Glide.with(this).load(R.drawable.dumbbell_overhead_press_side).into(secondGif);
                break;
            case 1:
                firstTextView.setText("Front raise - side" + BASIC_STANDING_POINTERS);
                secondTextView.setText("Front raise - front");
                Glide.with(this).load(R.drawable.front_raise_side).into(firstGif);
                Glide.with(this).load(R.drawable.front_raise_front).into(secondGif);
                break;
            case 2:
                oneGifOnly();
                firstTextView.setText("Shoulder press out" + BASIC_STANDING_POINTERS);
                Glide.with(this).load(R.drawable.shoulder_press_out_side).into(firstGif);
                break;

            case 3:
                oneGifOnly();
                firstTextView.setText("Face pull" + LINE_DROP + FACE_PULL_POINTERS);
                Glide.with(this).load(R.drawable.face_pull).into(firstGif);
                break;
            case 4:
                oneGifOnly();
                firstTextView.setText("Lateral raise" + LINE_DROP + BASIC_STANDING_POINTERS);
                Glide.with(this).load(R.drawable.lateral_raise).into(firstGif);
                break;
            case 5:
                oneGifOnly();
                firstTextView.setText("UCV raise" + LINE_DROP + BASIC_STANDING_POINTERS);
                Glide.with(this).load(R.drawable.ucv_raise).into(firstGif);
                break;
            case 6:
                oneGifOnly();
                firstTextView.setText("Prone press" + LINE_DROP + PRONE_PRESS_POINTERS);
                Glide.with(this).load(R.drawable.prone_press).into(firstGif);
        }
    }

    public void backMain(int exercise) {
        switch (exercise) {
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

    public void bicepsMain(int exercise) {
        switch (exercise) {
            case 0:
                firstTextView.setText("Barbell curls - front" + BASIC_STANDING_POINTERS);
                secondTextView.setText("Barbell curls - side");
                Glide.with(this).load(R.drawable.barbell_cruls_side).into(firstGif);
                Glide.with(this).load(R.drawable.barbell_curls_front).into(secondGif);
                break;
            case 1:
                firstTextView.setText("Waiter's curls - front" + BASIC_STANDING_POINTERS);
                secondTextView.setText("Waiter's curls - side");
                Glide.with(this).load(R.drawable.waiter_curls_side).into(firstGif);
                Glide.with(this).load(R.drawable.waiter_curls_front).into(secondGif);
                break;
            case 2:
                firstTextView.setText("Dumbbell hammer curls - front" + BASIC_STANDING_POINTERS);
                secondTextView.setText("Dumbbell hammer curls - side");
                Glide.with(this).load(R.drawable.dumbbell_hammer_curls_side).into(firstGif);
                Glide.with(this).load(R.drawable.dumbbell_hammer_curls_front).into(secondGif);
                break;
            case 3:
                firstTextView.setText("Robot hammer curls - front" + BASIC_STANDING_POINTERS);
                secondTextView.setText("Robot hammer curls - side");
                Glide.with(this).load(R.drawable.robot_curls_side).into(firstGif);
                Glide.with(this).load(R.drawable.robot_curls_front).into(secondGif);
                break;
            case 4:
                oneGifOnly();
                firstTextView.setText("French press");
                Glide.with(this).load(R.drawable.french_press).into(firstGif);
                break;
            case 5:
                oneGifOnly();
                firstTextView.setText("Triceps extension" + BASIC_STANDING_POINTERS);
                Glide.with(this).load(R.drawable.triceps_extension_side).into(firstGif);
                break;
        }
    }

    public void legsMain(int exercise) {
        switch (exercise) {
            case 0:
                oneGifOnly();
                firstTextView.setText("Squat" + LINE_DROP + CORE_ENGAGED + LINE_DROP + LEGS_POINTER1 + LINE_DROP + LEGS_POINTER3);
                Glide.with(this).load(R.drawable.squat).into(firstGif);
                break;
            case 1:
                oneGifOnly();
                firstTextView.setText("Deadlift" + LINE_DROP + CORE_ENGAGED + LINE_DROP + LEGS_POINTER1 + LINE_DROP + LEGS_POINTER2);
                Glide.with(this).load(R.drawable.deadlift).into(firstGif);
                break;
            case 2:
                oneGifOnly();
                firstTextView.setText("Romanian deadlift" + LINE_DROP + CORE_ENGAGED + LINE_DROP + LEGS_POINTER1 + LINE_DROP + LEGS_POINTER2);
                Glide.with(this).load(R.drawable.romanian_deadlift).into(firstGif);
                break;
            case 3:
                oneGifOnly();
                firstTextView.setText("Lunges" + LINE_DROP + CORE_ENGAGED + LINE_DROP + LEGS_POINTER3);
                Glide.with(this).load(R.drawable.lunges).into(firstGif);
                break;
            case 4:
                oneGifOnly();
                firstTextView.setText("Bulgarian split squat" + LINE_DROP + CORE_ENGAGED + LINE_DROP + LEGS_POINTER3);
                Glide.with(this).load(R.drawable.bulgarian_split_squat).into(firstGif);
                break;
            case 5:
                oneGifOnly();
                firstTextView.setText("Calf raise");
                Glide.with(this).load(R.drawable.calf_raise).into(firstGif);
        }
    }

    public void absMain(int exercise) {
        oneGifOnly();
        switch (exercise) {
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
                firstTextView.setText("Side accordion (beginner)");
                Glide.with(this).load(R.drawable.side_accordionv1).into(firstGif);
                break;
            case 6:
                firstTextView.setText("Side accordion (intermediate)");
                Glide.with(this).load(R.drawable.side_accordionv2).into(firstGif);
                break;
            case 7:
                firstTextView.setText("Side pocketknife");
                Glide.with(this).load(R.drawable.side_pocketknife).into(firstGif);
                break;
        }
    }

    public void Workout1(int exercise) {
        switch (exercise) {
            case 0:
                firstTextView.setText("Bench press" + LINE_DROP + CHEST_POINTERS);
                secondTextView.setText("Dumbbell bench press");
                Glide.with(this).load(R.drawable.bench_press).into(firstGif);
                Glide.with(this).load(R.drawable.dumbell_bench_press).into(secondGif);
                break;
            case 1:
                firstTextView.setText("Pull up");
                secondTextView.setText("chin up");
                Glide.with(this).load(R.drawable.pull_up_back).into(firstGif);
                Glide.with(this).load(R.drawable.chin_up_back).into(secondGif);
                break;
            case 2:
                oneGifOnly();
                firstTextView.setText("Lateral raise" + BASIC_STANDING_POINTERS);
                Glide.with(this).load(R.drawable.lateral_raise).into(firstGif);
                break;

            case 3:
                firstTextView.setText("Waiter's curls - front" + BASIC_STANDING_POINTERS);
                secondTextView.setText("Waiter's curls - side");
                Glide.with(this).load(R.drawable.waiter_curls_side).into(firstGif);
                Glide.with(this).load(R.drawable.waiter_curls_front).into(secondGif);
                break;
            case 4:
                oneGifOnly();
                firstTextView.setText("French press");
                Glide.with(this).load(R.drawable.french_press).into(firstGif);
                break;
            case 5:
                oneGifOnly();
                firstTextView.setText("Squat" + LINE_DROP + CORE_ENGAGED + LINE_DROP + LEGS_POINTER1 + LINE_DROP + LEGS_POINTER3);
                Glide.with(this).load(R.drawable.squat).into(firstGif);
        }
    }

    public void Workout2(int exercise) {
        switch (exercise) {
            case 0:
                firstTextView.setText("Dips - side" + LINE_DROP + CHEST_POINTERS);
                secondTextView.setText("Dips - back");
                Glide.with(this).load(R.drawable.dips_side).into(firstGif);
                Glide.with(this).load(R.drawable.dips_back).into(secondGif);
                break;
            case 1:
                firstTextView.setText("One arm high row - right hand");
                secondTextView.setText("One arm high row - left hand");
                Glide.with(this).load(R.drawable.one_arm_high_cable_row_right).into(firstGif);
                Glide.with(this).load(R.drawable.one_arm_high_cable_row_left).into(secondGif);
                break;
            case 2:
                firstTextView.setText("Overhead press - barbell" + LINE_DROP + OVERHEAD_PRESS_POINTERS + BASIC_STANDING_POINTERS);
                secondTextView.setText("Overhead press - dumbbell" + LINE_DROP + "Same pointers as the barbell press");
                Glide.with(this).load(R.drawable.overhead_press).into(firstGif);
                Glide.with(this).load(R.drawable.dumbbell_overhead_press_side).into(secondGif);
                break;
            case 3:
                firstTextView.setText("Robot hammer curls - front" + BASIC_STANDING_POINTERS);
                secondTextView.setText("Robot hammer curls - side");
                Glide.with(this).load(R.drawable.robot_curls_side).into(firstGif);
                Glide.with(this).load(R.drawable.robot_curls_front).into(secondGif);
                break;
            case 4:
                oneGifOnly();
                firstTextView.setText("Triceps extension" + BASIC_STANDING_POINTERS);
                Glide.with(this).load(R.drawable.triceps_extension_side).into(firstGif);
                break;
            case 5:
                oneGifOnly();
                firstTextView.setText("Lunges" + LINE_DROP + CORE_ENGAGED + LINE_DROP + LEGS_POINTER3);
                Glide.with(this).load(R.drawable.lunges).into(firstGif);
        }
    }

    public void Workout3(int exercise) {
        switch (exercise) {
            case 0:
                firstTextView.setText("Incline push up" + LINE_DROP + "same pointers as the regular push up");
                secondTextView.setText("Decline push up");
                Glide.with(this).load(R.drawable.incline_push_up).into(firstGif);
                Glide.with(this).load(R.drawable.decline_push_up).into(secondGif);
                break;
            case 1:
                firstTextView.setText("Barbell row - side");
                secondTextView.setText("Sitting row (narrow grip)");
                Glide.with(this).load(R.drawable.barbell_row_side).into(firstGif);
                Glide.with(this).load(R.drawable.row_narrow_grip).into(secondGif);
                break;
            case 2:
                firstTextView.setText("Prone press" + LINE_DROP + PRONE_PRESS_POINTERS);
                secondTextView.setText("UCV raise" + LINE_DROP + BASIC_STANDING_POINTERS);
                Glide.with(this).load(R.drawable.prone_press).into(firstGif);
                Glide.with(this).load(R.drawable.ucv_raise).into(secondGif);
                break;
            case 3:
                firstTextView.setText("Dumbbell hammer curls - front" + BASIC_STANDING_POINTERS);
                secondTextView.setText("Dumbbell hammer curls - side");
                Glide.with(this).load(R.drawable.dumbbell_hammer_curls_side).into(firstGif);
                Glide.with(this).load(R.drawable.dumbbell_hammer_curls_front).into(secondGif);
                break;
            case 4:
                oneGifOnly();
                firstTextView.setText("Triceps extension" + BASIC_STANDING_POINTERS);
                Glide.with(this).load(R.drawable.triceps_extension_side).into(firstGif);
                break;
            case 5:
                firstTextView.setText("Romanian deadlift" + LINE_DROP + CORE_ENGAGED + LINE_DROP + LEGS_POINTER1 + LINE_DROP + LEGS_POINTER2);
                secondTextView.setText("Deadlift" + LINE_DROP + CORE_ENGAGED + LINE_DROP + LEGS_POINTER1 + LINE_DROP + LEGS_POINTER2);
                Glide.with(this).load(R.drawable.romanian_deadlift).into(firstGif);
                Glide.with(this).load(R.drawable.deadlift).into(secondGif);
                break;
        }
    }

    public void Workout4(int exercise) {
        switch (exercise) {
            case 0:
                oneGifOnly();
                firstTextView.setText("Cable crossover" + LINE_DROP + CHEST_BEFORE_SHOULDERS);
                Glide.with(this).load(R.drawable.cable_crossover).into(firstGif);
                break;
            case 1:
                oneGifOnly();
                firstTextView.setText("Lat pulldown");
                Glide.with(this).load(R.drawable.lat_pulldown).into(firstGif);
                break;
            case 2:
                oneGifOnly();
                firstTextView.setText("Face pull" + LINE_DROP + FACE_PULL_POINTERS);
                Glide.with(this).load(R.drawable.face_pull).into(firstGif);
                break;
            case 3:
                firstTextView.setText("Barbell curls - front" + BASIC_STANDING_POINTERS);
                secondTextView.setText("Barbell curls - side");
                Glide.with(this).load(R.drawable.barbell_cruls_side).into(firstGif);
                Glide.with(this).load(R.drawable.barbell_curls_front).into(secondGif);
                break;
            case 4:
                oneGifOnly();
                firstTextView.setText("French press");
                Glide.with(this).load(R.drawable.french_press).into(firstGif);
                break;
            case 5:
                oneGifOnly();
                firstTextView.setText("Bulgarian split squat" + LINE_DROP + CORE_ENGAGED + LINE_DROP + LEGS_POINTER3);
                Glide.with(this).load(R.drawable.bulgarian_split_squat).into(firstGif);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        firstGif = findViewById(R.id.splace_image_view);
        secondGif = findViewById(R.id.squat);

        firstTextView = (TextView) findViewById(R.id.firstGifTextView);
        secondTextView = (TextView) findViewById(R.id.secondGifTextView);

        intent = getIntent();
        int exercise = intent.getIntExtra("exercise", -1);
        muscleGroup = intent.getIntExtra("muscle group", -1);
        firstGif.setVisibility(View.VISIBLE);
        secondGif.setVisibility(View.VISIBLE);
        switch (muscleGroup) {
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
                legsMain(exercise);
                break;
            case MuscleGroupActivity.ABS:
                absMain(exercise);
                break;
            case MuscleGroupActivity.WORKOUT1:
                Workout1(exercise);
                break;
            case MuscleGroupActivity.WORKOUT2:
                Workout2(exercise);
                break;
            case MuscleGroupActivity.WORKOUT3:
                Workout3(exercise);
                break;
            case MuscleGroupActivity.WORKOUT4:
                Workout4(exercise);
        }
    }
}