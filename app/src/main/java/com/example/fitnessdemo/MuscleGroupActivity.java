package com.example.fitnessdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class MuscleGroupActivity extends AppCompatActivity {

    final String warmUpSet = "Warm up set 50% max weight 15 reps";
    final String firstSet = "First set 75% max weight 12 reps";
    final String secondSet = "Second set 85% max weight 10 reps";
    final String thirdSet = "Third and fourth set max weight 8 reps or failure (max you can do).";
    final String classicSet = "Three sets of 95% max weight 12 reps or failure";
    final String LINE_DROP = "\n";
    final static int CHEST = 0;
    final static int SHOULDERS = 1;
    final static int BACK = 2;
    final static int BICEPS = 3;
    final static int LEGS = 4;
    final static int ABS = 5;
    final String[] descriptions= {"Chest","Shoulders","Back","Biceps/Triceps","Legs","Abs & Core"};

    ArrayList<String> exerciseArrList, exerciseInfo;
    ListView exerciseListView;
    TextView trainingDescription;
    AlertDialog.Builder alertDialog;

    public void backToMain(View view) {
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        mainIntent.putExtra("start screen", 0);
        startActivity(mainIntent);
    }

    public void matchListView(int numMuscleGroup){
        String[] exercises;
        String[] info;

        switch(numMuscleGroup){
            case CHEST:
                exercises = new String[]{"1. Bench press", "2. Dumbbell bench press ", "3. dips"};
                info = new String[]{"5 sets:"+LINE_DROP+firstSet+LINE_DROP+secondSet+LINE_DROP+thirdSet, "You should", "You should"};
                break;
            case SHOULDERS:
                exercises = new String[]{"1. Shoulder press"};
                info = new String[]{"You should"};
                break;
            case BACK:
                exercises = new String[]{"1. Pull up", "2. Hip row", "3. Sitting row", "4. Pull over", " 5. One arm pull"};
                info = new String[]{"You should", "You should", "You should", "You should", "You should"};
                break;
            case BICEPS:
                exercises = new String[]{"1. Bicep curls", "2. French press"};
                info = new String[]{"You should", "You should"};
                break;
            case LEGS:
                exercises = new String[]{"1. Squat", "2. Deadlift", "3. lunges", "4. Bulgarian split squat"};
                info = new String[]{"You should", "You should", "You should", "You should"};
                break;
            case ABS:
                exercises = new String[]{"1. Static upper", "2. Accordion", "3. Legs raise", "4. Marine leg raise", "5. Bicycle", " 6. Side accordion"};
                info = new String[]{"You should", "You should", "You should", "You should", "You should", "You should"};
                break;
            default:
                exercises = new String[]{};
                info = new String[]{};
        }
        exerciseArrList = new ArrayList<String>(Arrays.asList(exercises));
        exerciseInfo = new ArrayList<String>(Arrays.asList(info));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exerciseArrList);
        exerciseListView.setAdapter(arrayAdapter);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs);

        Intent intent = getIntent();
        final int muscleGroup = intent.getIntExtra("muscle group", -1);

        trainingDescription = findViewById(R.id.trainingDescription);
        trainingDescription.setText(descriptions[muscleGroup]);
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
                            public void onClick(DialogInterface dialogInterface, int i) {}
                        }).show();
                return true;
            }
        });
    }
}
