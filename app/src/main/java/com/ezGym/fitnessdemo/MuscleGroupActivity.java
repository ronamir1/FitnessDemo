package com.ezGym.fitnessdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MuscleGroupActivity extends AppCompatActivity {
    final String LINE_DROP = "\n";
    final String firstOption = "First option:\n";
    final String secondOption = "Second option (to vary training from time to time):\n";
    final String warmUpSet = "Warm up set 50% max weight 15 reps.";
    final String firstSet = "First set 75% max weight 12 reps";
    final String secondSet = "Second set 85% max weight 10 reps";
    final String thirdSet = "Third and fourth set max weight 8 reps or failure (max you can do).";
    final String classicSet = warmUpSet + LINE_DROP + "3 sets, 90% max weight 12 reps or failure, 60-90 seconds rest between each set";
    final String raisingPyramide = "5 sets, 60-120 seconds rest between each set:" + LINE_DROP + warmUpSet + LINE_DROP + firstSet + LINE_DROP + secondSet + LINE_DROP + thirdSet;
    final String bodyWeight = "4 sets overall, 60-120 seconds rest between each set.\nThe first is a warm up set, do half your maximum. On the other sets do the maximum you can.";
    final String health = "This exercise require high rep so do three sets of 80% max weight, 15 reps, 60 seconds rest between each set.\nIf too hard drop to a weight that allows you this number of reps.";
    final String combineClassicPyramide = firstOption + raisingPyramide + LINE_DROP + LINE_DROP + secondOption + classicSet;
    final String absClassic = "3 sets, slow movements, each set 30-45 seconds\nIf it is too hard, try to do your best.\nIf its easy peasy lemon squeezy you go ahead and do it for longer.";

    final String CHEST_A = "1. Bench press\n2. Cable crossover + Incline Bench press (set=lower weight cable crossover set and then an incline bench press set)\n3. Decline push ups";
    final String CHEST_B = "1. Dumbbell bench press\n2. Cable crossover + Push ups(set = lower weight cable crossover set and then a push ups set)\n3. Dips";
    final String CHEST_C = "";

    final String SHOULDERS_A = "1. UCV raise/Front raise\n2. Overhead press (Barbell or Dumbbell)\n3. Face pull";
    final String SHOULDERS_B = "1. Shoulder press out\n2. Prone press\n3. Lateral raise";
    final String SHOULDERS_C = "";

    final String BACK_A = "1. Chin up\n2. Sitting row (narrow grip)\n3. Lat pulldown + One arm high row (superset)";
    final String BACK_B = "1. Barbell row \n2. Lat pulldown + pull up (superset)\n3. One arm high row";
    final String BACK_C = "";

    final String BICEPS_A = "1. Barbell curls\n2. Dumbbell hammer curls\n3. French press";
    final String BICEPS_B = "1. Waiter's curls\n2. Robot curls\n3. Triceps extension";
    final String BICEPS_C = "";

    final String LEGS_A = "1. Squat\n2. Deadlift\n3. Lunges";
    final String LEGS_B = "1. Squat\n2. Deadlift\n3. Bulgarian split squat";
    final String LEGS_C = "";

    final String ABS_A = "1. Static upper\n2. Accordion\n3. Leg raise + Marine leg raise (one after the other)";
    final String ABS_B = "1. Bicycle\n2. Side accordion (beginner/intermediate)\n3. side pocketknife";
    final String ABS_C = "";

    final String SETS_INFO_BUTTON = "\n\nDon't forget to click on sets info button to get the sets information!";
    final String DO_THIS = "Do these exercises in this order:\n";

    final String[][] exercises_combinations = {{CHEST_A, CHEST_B, CHEST_C}, {SHOULDERS_A, SHOULDERS_B, SHOULDERS_C}, {BACK_A, BACK_B, BACK_C}, {BICEPS_A, BICEPS_B, BICEPS_C}, {LEGS_A, LEGS_B, LEGS_C}, {ABS_A, ABS_B, ABS_C}};

    final static int CHEST = 0;
    final static int SHOULDERS = 1;
    final static int BACK = 2;
    final static int BICEPS = 3;
    final static int LEGS = 4;
    final static int ABS = 5;
    final static int WORKOUT1 = 6;
    final static int WORKOUT2 = 7;
    final static int WORKOUT3 = 8;
    final static int WORKOUT4 = 9;
    final String[] descriptions = {"Chest", "Shoulders", "Back", "Biceps/Triceps", "Legs", "Abs & Core", "Workout 1", "Workout 2", "Workout 3", "Workout 4"};

    int muscleGroup;
    DbManager dbManager;
    ArrayList<String> exerciseArrList, exerciseInfo;
    ListView exerciseListView;
    TextView trainingDescription;
    AlertDialog.Builder alertDialog;
    Intent intent;

    public void backToMain(View view) {
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        mainIntent.putExtra("start screen", 0);
        startActivity(mainIntent);
    }

    public void matchListView(int numMuscleGroup) {
        String[] exercises;
        String[] info;

        switch (numMuscleGroup) {
            case CHEST:
                exercises = new String[]{"1. Bench press", "2. Dumbbell bench press ", "3. Dips", "4. Cable crossover", "5. Push up", "6. Incline/Decline push up"};
                info = new String[]{combineClassicPyramide, combineClassicPyramide, bodyWeight, combineClassicPyramide, bodyWeight, bodyWeight};
                break;
            case SHOULDERS:
                exercises = new String[]{"1. Overhead press", "2. Front raise", "3. Shoulder press out", "4. Face pull", "5. Lateral raise", "6. UCV raise", "7. Prone press"};
                info = new String[]{combineClassicPyramide, combineClassicPyramide, health, health, combineClassicPyramide, classicSet, health};
                break;
            case BACK:
                exercises = new String[]{"1. Pull up", "2. Chin up", "3. Barbell row", "4. Sitting row", "5. Lat pulldown", " 6. One arm high row"};
                info = new String[]{bodyWeight, bodyWeight, raisingPyramide, combineClassicPyramide, health, combineClassicPyramide};
                break;
            case BICEPS:
                exercises = new String[]{"1. Barbell curls", "2. Waiter's curls", "3. Dumbbell hammer curls", "4. Robot hammer curls", "5. French press", "6. Triceps extension"};
                info = new String[]{combineClassicPyramide, combineClassicPyramide, combineClassicPyramide, combineClassicPyramide, combineClassicPyramide, combineClassicPyramide};
                break;
            case LEGS:
                exercises = new String[]{"1. Squat", "2. Deadlift", "3. Romanian deadlift", "4. Lunges", "5. Bulgarian split squat", "6. Calf raise"};
                info = new String[]{raisingPyramide, raisingPyramide, raisingPyramide, combineClassicPyramide, combineClassicPyramide, classicSet};
                break;
            case ABS:
                exercises = new String[]{"1. Static upper", "2. Accordion", "3. Legs raise", "4. Marine leg raise", "5. Bicycle", " 6. Side accordion (begginer)", "7. Side accordion (intermediate)", "8. Side pocketknife"};
                info = new String[]{absClassic, absClassic, absClassic, absClassic, absClassic, absClassic, absClassic, absClassic};
                break;
            case WORKOUT1:
                exercises = new String[]{"1. Bench press/Dumbbell bench press", "2. Pull up/Chin up ", "3. Lateral raise", "4. Waiter's curls", "5. French press", "6. Squat"};
                info = new String[]{combineClassicPyramide, bodyWeight, classicSet, classicSet, classicSet, combineClassicPyramide};
                break;
            case WORKOUT2:
                exercises = new String[]{"1. Dips", "2. One arm high row", "3. Overhead press", "4. Robot hammer curls", "5. Triceps extension", "6. Lunges"};
                info = new String[]{bodyWeight, combineClassicPyramide, combineClassicPyramide, classicSet, classicSet, combineClassicPyramide};
                break;
            case WORKOUT3:
                exercises = new String[]{"1. Push up (regular, incline or decline)", "2. Barbell row/Sitting row", "3. Prone press/UCV raise", "4. Dumbbell hammer curls", "5. Triceps extension", "6. Deadlift/Romanian deadlift"};
                info = new String[]{bodyWeight, combineClassicPyramide, classicSet, classicSet, classicSet, raisingPyramide};
                break;
            case WORKOUT4:
                exercises = new String[]{"1. Cable crossover", "2. Lat pulldown", "3. Face pull", "4. Barbell curls", "5. French press", "6. Bulgarian split squat"};
                info = new String[]{combineClassicPyramide, health, health, classicSet, classicSet, combineClassicPyramide};
                break;
            default:
                exercises = new String[]{};
                info = new String[]{};
        }
        exerciseArrList = new ArrayList<String>(Arrays.asList(exercises));
        exerciseInfo = new ArrayList<String>(Arrays.asList(info));
        MyAdapter adapter = new MyAdapter(this, exerciseArrList);
        exerciseListView.setAdapter(adapter);
    }

    public void recommendedTraining(View view) {
        int tag = Integer.parseInt(view.getTag().toString());
        AlertDialog.Builder recommended = new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.AppTheme));
        recommended.setMessage(DO_THIS + LINE_DROP + exercises_combinations[muscleGroup][tag] + SETS_INFO_BUTTON);
        recommended.setPositiveButton("Ok", null);
        recommended.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_group);
        intent = getIntent();
        muscleGroup = intent.getIntExtra("muscle group", -1);
        dbManager = new DbManager(this.openOrCreateDatabase("exerciseDescription", MODE_PRIVATE, null));
        trainingDescription = findViewById(R.id.trainingDescription);
        trainingDescription.setText(descriptions[muscleGroup]);
        exerciseListView = (ListView) findViewById(R.id.exerciseListView);
        matchListView(muscleGroup);

        // onClick for the listView
        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent exerciseIntent = new Intent(getApplicationContext(), ExercisesActivity.class);
                exerciseIntent.putExtra("muscle group", muscleGroup);
                exerciseIntent.putExtra("exercise", i);
                startActivity(exerciseIntent);
            }
        });
    }

    public void setExerciseParams(int x, int y, final String exercise) {
        final Dialog pw = new Dialog(MuscleGroupActivity.this);
        pw.setContentView(R.layout.popup_example);
        pw.setCanceledOnTouchOutside(true);
        int[] params = dbManager.getExerciseParams(exercise);
        EditText sets = pw.findViewById(R.id.sets);
        sets.setText(Integer.toString(params[0]));
        sets.setSelection(Integer.toString(params[0]).length());
        EditText weights = pw.findViewById(R.id.weights);
        weights.setText(Integer.toString(params[1]));
        weights.setSelection(Integer.toString(params[1]).length());
        Window window = pw.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.TOP);
        WindowManager.LayoutParams wmlp = pw.getWindow().getAttributes();
        wmlp.width = 360;
        wmlp.height = 300;
        wmlp.x = x - 25;
        wmlp.y = y - 100;
        pw.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                EditText sets = pw.findViewById(R.id.sets);
                EditText weights = pw.findViewById(R.id.weights);
                dbManager.updateValues(exercise, sets.getText().toString(), weights.getText().toString());
            }
        });
        pw.show();
    }

    class MyAdapter extends ArrayAdapter<String> {
        Context context;
        ArrayList<String> exercises;
        View row;

        MyAdapter(Context c, ArrayList<String> exercises1) {
            super(c, R.layout.row, exercises1);
            this.context = c;
            this.exercises = exercises1;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row, parent, false);
            final ImageView imageView = row.findViewById(R.id.options);
            final ImageView imageView1 = row.findViewById(R.id.options1);
            final TextView description = row.findViewById(R.id.exercise_description);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int[] location = new int[2];
                    imageView.getLocationOnScreen(location);
                    setExerciseParams(location[0], location[1], description.getText().toString());
                }
            });

            imageView1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog = new AlertDialog.Builder(new ContextThemeWrapper(MuscleGroupActivity.this, R.style.CustomDialogTheme));
                    alertDialog.setMessage(exerciseInfo.get(position)).setTitle("Exercise Explanation:");
                    alertDialog.
                            setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            }).show();
                }
            });
            description.setText(this.exercises.get(position));
            return row;
        }
    }
}
