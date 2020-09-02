package com.example.fitnessdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
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
    DbManager dbManager;
    final String LINE_DROP = "\n";
    final String firstOption = "First option:\n";
    final String secondOption = "Second option (to vary training from time to time):\n";
    final String warmUpSet = "Warm up set 50% max weight 15 reps.";
    final String firstSet = "First set 75% max weight 12 reps";
    final String secondSet = "Second set 85% max weight 10 reps";
    final String thirdSet = "Third and fourth set max weight 8 reps or failure (max you can do).";
    final String classicSet = warmUpSet + LINE_DROP + "Three sets of 90% max weight 12 reps or failure";
    final String raisingPyramide = "5 sets:" + LINE_DROP + warmUpSet + LINE_DROP + firstSet + LINE_DROP + secondSet + LINE_DROP + thirdSet;
    final String bodyWeight = "4 sets overall.\nThe first is a warm up set, do half your maximum. On the other sets do the maximum you can.";
    final String health = "This exercise require high rep so do three sets of 80% max weight, 15 reps.\nIf too hard drop to a weight that allows you this number of reps.";
    final String combineClassicPyramide = firstOption + raisingPyramide + LINE_DROP + LINE_DROP + secondOption + classicSet;
    final String absClassic = "3 sets, slow movements, each set 30-45 seconds\nIf it is too hard, try to do your best.\nIf its easy peasy lemon squeezy you go ahead and do it for longer.";

    final String dontForget = "Don't forget to change them every once in a while!";
    final String bigMuscle = "We recommend you to choose 4 exercises for this muscle, choose the ones you like.\n\n" + dontForget;
    final String mediumMuscle = "We recommend you to choose 3 exercises for this muscle, choose the ones you like.\n\n" + dontForget;
    final String smallMuscle = "We recommend you to choose 2 exercises for this muscle, choose the ones you like.\n\n" + dontForget;
    final String forBicepsTriceps = "We recommend you to choose 2 exercises for this muscle, choose the ones you like.\nThat's two for biceps and two for triceps\n\n"+dontForget;
    final String forAbs = "We recommend you to choose 4 exercises for this muscle, try to work one day on upper and lower and the other on obliques and sides.\n\n"+dontForget;
    final static int CHEST = 0;
    final static int SHOULDERS = 1;
    final static int BACK = 2;
    final static int BICEPS = 3;
    final static int LEGS = 4;
    final static int ABS = 5;
    final String[] descriptions = {"Chest", "Shoulders", "Back", "Biceps/Triceps", "Legs", "Abs & Core"};
    final String[] muscleGroupRecommendations = {bigMuscle, mediumMuscle, bigMuscle, forBicepsTriceps, mediumMuscle, forAbs};

    ArrayList<String> exerciseArrList, exerciseInfo;
    ListView exerciseListView;
    TextView trainingDescription, instructions;
    AlertDialog.Builder alertDialog;

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
                exercises = new String[]{"1. Bench press", "2. Dumbbell bench press ", "3. dips"};
                info = new String[]{raisingPyramide, raisingPyramide, bodyWeight};
                break;
            case SHOULDERS:
                exercises = new String[]{"1. Shoulder press"};
                info = new String[]{raisingPyramide};
                break;
            case BACK:
                exercises = new String[]{"1. Pull up", "2. Hip row", "3. Sitting row", "4. Pull over", " 5. One arm pull"};
                info = new String[]{bodyWeight, raisingPyramide, raisingPyramide, health, raisingPyramide};
                break;
            case BICEPS:
                exercises = new String[]{"1. Bicep curls", "2. French press"};
                info = new String[]{combineClassicPyramide, combineClassicPyramide};
                break;
            case LEGS:
                exercises = new String[]{"1. Squat", "2. Deadlift", "3. lunges", "4. Bulgarian split squat"};
                info = new String[]{raisingPyramide, raisingPyramide, combineClassicPyramide, combineClassicPyramide};
                break;
            case ABS:
                exercises = new String[]{"1. Static upper", "2. Accordion", "3. Legs raise", "4. Marine leg raise", "5. Bicycle", " 6. Side accordion"};
                info = new String[]{absClassic, absClassic, absClassic, absClassic, absClassic, absClassic};
                break;
            default:
                exercises = new String[]{};
                info = new String[]{};
        }
        exerciseArrList = new ArrayList<String>(Arrays.asList(exercises));
        exerciseInfo = new ArrayList<String>(Arrays.asList(info));
        MyAdapter adapter = new MyAdapter(this, exerciseArrList);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exerciseArrList);
        exerciseListView.setAdapter(adapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs);
        Intent intent = getIntent();
        final int muscleGroup = intent.getIntExtra("muscle group", -1);
        dbManager = new DbManager(this.openOrCreateDatabase("exerciseDescription", MODE_PRIVATE, null));
        trainingDescription = findViewById(R.id.trainingDescription);
        trainingDescription.setText(descriptions[muscleGroup]);
        instructions = findViewById(R.id.instructions);
        instructions.setText(muscleGroupRecommendations[muscleGroup]);
        exerciseListView = (ListView) findViewById(R.id.exerciseListView);
        matchListView(muscleGroup);

        // onClick for the listView
        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent exerciseIntent = new Intent(getApplicationContext(), SquatActivity.class);
                exerciseIntent.putExtra("muscle group", muscleGroup);
                exerciseIntent.putExtra("exercise", i);
                startActivity(exerciseIntent);
            }
        });

        // LONG onClick for the listView
        exerciseListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                alertDialog = new AlertDialog.Builder(MuscleGroupActivity.this);
                alertDialog.setMessage(exerciseInfo.get(i));
                alertDialog.setView(R.layout.layout_dialog).
                        setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                            }
                        }).show();
                return true;
            }
        });
    }

    public void setExerciseParams(int x, int y, final String exercise){
        final Dialog pw = new Dialog(MuscleGroupActivity.this);
        pw.setContentView(R.layout.popup_example);
        pw.setCanceledOnTouchOutside(true);
        int[] params = dbManager.getExerciseParams(exercise);
        EditText sets = pw.findViewById(R.id.sets);
        sets.setText(Integer.toString(params[0]));
        EditText weights = pw.findViewById(R.id.weights);
        weights.setText(Integer.toString(params[1]));
        Window window = pw.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.setGravity(Gravity.TOP);
        WindowManager.LayoutParams wmlp = pw.getWindow().getAttributes();
        wmlp.width = 360;
        wmlp.height = 360;
        wmlp.x = x;
        wmlp.y = y - 75;
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

    class MyAdapter extends ArrayAdapter<String>{
        Context context;
        ArrayList<String> exercises;
        View row;
        MyAdapter(Context c, ArrayList<String> exercises1){
            super(c, R.layout.row, exercises1);
            this.context = c;
            this.exercises = exercises1;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row, parent, false);
            final ImageView imageView = row.findViewById(R.id.options);
            final TextView description = row.findViewById(R.id.exercise_description);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int[] location = new int[2];
                    imageView.getLocationOnScreen(location);
                    setExerciseParams(location[0], location[1], description.getText().toString());
                }
            });
            description.setText(this.exercises.get(position));
            return row;
        }
    }
}
