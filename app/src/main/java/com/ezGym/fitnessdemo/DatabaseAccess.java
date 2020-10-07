package com.ezGym.fitnessdemo;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    /**
     * Read all quotes from the database.
     *
     * @return a List of quotes
     */
    public List<List<String>> getMuscleGroup(String muscleGroup) {
        List<List<String>> fnlList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM excerciseDB WHERE Category = ?", new String[]{muscleGroup});
        cursor.moveToFirst();
        int explanation = cursor.getColumnIndex("Explanation");
        int description = cursor.getColumnIndex("Description");
        while (!cursor.isAfterLast()) {
            List<String> list = new ArrayList<>();
            list.add(cursor.getString(description));
            list.add(cursor.getString(explanation));
            fnlList.add(list);
            cursor.moveToNext();
        }
        cursor.close();
        return fnlList;
    }

    public List<List<String>> getWorkout(String workout) {
        List<List<String>> fnlList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM excerciseDB WHERE TrainingCategory = ?", new String[]{workout});
        cursor.moveToFirst();
        int explanation = cursor.getColumnIndex("Explanation");
        int description = cursor.getColumnIndex("Description");
        while (!cursor.isAfterLast()) {
            List<String> list = new ArrayList<>();
            list.add(cursor.getString(description));
            list.add(cursor.getString(explanation));
            fnlList.add(list);
            cursor.moveToNext();
        }
        cursor.close();
        return fnlList;
    }
}
