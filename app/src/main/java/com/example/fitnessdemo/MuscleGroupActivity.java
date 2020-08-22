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

    final int CHEST = 0;
    final int SHOULDERS = 1;
    final int BACK = 2;
    final int BICEPS = 3;
    final int LEGS = 4;
    final int ABS = 5;
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
                exercises = new String[]{"1. Bench press", "2. Legs raise", "3. Marine leg raise", "4. Bicycle", " 5. Side crunches"};
                info = new String[]{"You should", "You should", "You should", "You should", "You should"};
                break;
            case SHOULDERS:
                exercises = new String[]{"1. Shoulder press", "2. Legs raise", "3. Marine leg raise", "4. Bicycle", " 5. Side crunches"};
                info = new String[]{"You should", "You should", "You should", "You should", "You should"};
                break;
            case BACK:
                exercises = new String[]{"1. Pull up", "2. Legs raise", "3. Marine leg raise", "4. Bicycle", " 5. Side crunches"};
                info = new String[]{"You should", "You should", "You should", "You should", "You should"};
                break;
            case BICEPS:
                exercises = new String[]{"1. Bicep curls", "2. Legs raise", "3. Marine leg raise", "4. Bicycle", " 5. Side crunches"};
                info = new String[]{"You should", "You should", "You should", "You should", "You should"};
                break;
            case LEGS:
                exercises = new String[]{"1. Squat", "2. Legs raise", "3. Marine leg raise", "4. Bicycle", " 5. Side crunches"};
                info = new String[]{"You should", "You should", "You should", "You should", "You should"};
                break;
            case ABS:
                exercises = new String[]{"1. Static upper", "2. Legs raise", "3. Marine leg raise", "4. Bicycle", " 5. Side crunches"};
                info = new String[]{"You should", "You should", "You should", "You should", "You should"};
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
