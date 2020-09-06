package com.example.fitnessdemo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DbManager {
    SQLiteDatabase myDataBase;

    DbManager(SQLiteDatabase database){
        myDataBase = database;
        myDataBase.execSQL("CREATE TABLE IF NOT EXISTS exercisesParams (sets INT, weights INT, description VARCHAR)");
    }

    public void updateValues(String description, String weights){
        myDataBase.execSQL(
                "UPDATE exercisesParams" +
                " SET weights = ?" +
                "WHERE description=?", new String[]{weights, description});
                //If no update happened (i.e. the row didn't exist) then insert one\n
        myDataBase.execSQL("INSERT INTO exercisesParams (weights, description)" +
                "SELECT ?, ?" +
                "WHERE (Select Changes() = 0)", new String[]{weights, description});
    }

    public int[] getExerciseParams(String description){
        int weights = 0;
        Cursor c = myDataBase.rawQuery("SELECT * FROM exercisesParams Where description = ?", new String[]{description});
        int weightsIdx = c.getColumnIndex("weights");
        if (c!= null && c.moveToFirst()){
            weights = c.getInt(weightsIdx);
            c.close();
        }
        return new int[]{weights};
    }

}
