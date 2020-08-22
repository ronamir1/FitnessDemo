package com.example.fitnessdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class absActivity extends AppCompatActivity {

    ArrayList<String> exerciseArrList;
    ListView exerciseListView;
    TextView trainingDescription;

    public void backToMain(View view) {
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(mainIntent);
    }

    public void setExerciseList() {
        exerciseArrList = new ArrayList<String>();
        exerciseArrList.add("1. Static upper");
        exerciseArrList.add("2. Legs raise");
        exerciseArrList.add("3. Marine leg raise");
        exerciseArrList.add("4. Bicycle");
        exerciseArrList.add("5. Side crunches");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs);
        trainingDescription = findViewById(R.id.trainingDescription);
        trainingDescription.setText("Abs & Core_day");
        setExerciseList();
        exerciseListView = (ListView) findViewById(R.id.exerciseListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exerciseArrList);
        exerciseListView.setAdapter(arrayAdapter);

        exerciseListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent mainIntent = new Intent(getApplicationContext(), SquatActivity.class);
                startActivity(mainIntent);
            }
        });

        exerciseListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                new AlertDialog.Builder(absActivity.this).setView(R.layout.layout_dialog).
                        setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //open or create - with sqlLite
                            }
                        }).show();
                return true;
            }
        });
    }
}
