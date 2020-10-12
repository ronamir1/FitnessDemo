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
import java.util.HashMap;
import java.util.List;

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
    final String[] categories = {"CHEST", "SHOULDERS", "BACK", "BICEPS", "LEGS", "ABS", "WORKOUT1", "WORKOUT2", "WORKOUT3", "WORKOUT4"};

    final String[][] exercises_combinations = {{CHEST_A, CHEST_B, CHEST_C}, {SHOULDERS_A, SHOULDERS_B, SHOULDERS_C}, {BACK_A, BACK_B, BACK_C}, {BICEPS_A, BICEPS_B, BICEPS_C}, {LEGS_A, LEGS_B, LEGS_C}, {ABS_A, ABS_B, ABS_C}};
    final String[] descriptions = {"Chest", "Shoulders", "Back", "Biceps/Triceps", "Legs", "Abs & Core", "Workout 1", "Workout 2", "Workout 3", "Workout 4"};

    int muscleGroup;
    DbManager dbManager;
    List<List<String>> myExercises;
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

    public void matchListView() {
        if(muscleGroup < 6){
            dbAccess(0);
        }
        else{
            dbAccess(1);
        }
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
        exerciseListView = findViewById(R.id.exerciseListView);
        matchListView();

        // onClick for the listView
        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent exerciseIntent = new Intent(getApplicationContext(), ExercisesActivity.class);
                exerciseIntent.putExtra("muscle group", muscleGroup);
                exerciseIntent.putExtra("display1", myExercises.get(i).get(2));
                exerciseIntent.putExtra("gif1", myExercises.get(i).get(3));
                exerciseIntent.putExtra("display2", myExercises.get(i).get(4));
                exerciseIntent.putExtra("gif2", myExercises.get(i).get(5));
                exerciseIntent.putExtra("exDescription", myExercises.get(i).get(6));
                startActivity(exerciseIntent);
            }
        });
    }

    public void setExerciseParams(final String exercise) {
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
        window.setBackgroundDrawable(getDrawable(R.drawable.bg1));
        window.setGravity(Gravity.CENTER);
        WindowManager.LayoutParams wmlp = pw.getWindow().getAttributes();
        wmlp.width = 360;
        wmlp.height = 300;
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

    private HashMap<String, String> createHash(){
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("absClassic", absClassic);
        hashMap.put("bodyWeight", bodyWeight);
        hashMap.put("raisingPyramide", raisingPyramide);
        hashMap.put("health", health);
        hashMap.put("combineClassicPyramide", combineClassicPyramide);
        return hashMap;
    }

    private void dbAccess(int j){
        DatabaseAccess database = MainActivity.databaseAccess;
        HashMap<String, String> exercisesDescription = createHash();
        exerciseArrList = new ArrayList<>();
        exerciseInfo = new ArrayList<>();
        database.open();
        if (j == 0){
            myExercises = database.getMuscleGroup(categories[muscleGroup]);
            database.close();
            for (int i = 0; i < myExercises.size(); i++){
                exerciseArrList.add(myExercises.get(i).get(0));
                exerciseInfo.add(exercisesDescription.get(myExercises.get(i).get(1)));
            }
        }
        else{
            myExercises = database.getWorkout(categories[muscleGroup]);
            database.close();
            String[] exercisesArr = new String[myExercises.size()];
            String[] infoArr = new String[myExercises.size()];
            for (int i = 0; i < myExercises.size(); i++){
                List<String> exercise = myExercises.get(i);
                exercisesArr[Integer.parseInt(exercise.get(exercise.size() - 1)) - 1] = (exercise.get(0));
                infoArr[Integer.parseInt(exercise.get(exercise.size() - 1)) - 1] = exercisesDescription.get(exercise.get(1));
            }
            for(int i = 0; i < exercisesArr.length ; i++){
                exerciseArrList.add(exercisesArr[i]);
                exerciseInfo.add(infoArr[i]);
            }
        }
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
                    setExerciseParams(description.getText().toString());
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
