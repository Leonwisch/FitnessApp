package com.example.fitnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "FITNESSAPP.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_Date = "date";

    private static final String TABLE_NAME2 = "my_library2";
    private static final String COLUMN_uebung_id = "uebung_id";
    private static final String COLUMN_uebung_name = "uebung_name";
    private static final String COLUMN_uebung_weight = "uebung_weight";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_Date + " TEXT);";
        db.execSQL(query);

        String query2 = "CREATE TABLE " + TABLE_NAME2 +
                " (" + COLUMN_uebung_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_uebung_name + " TEXT, " +
                COLUMN_uebung_weight + " TEXT, " +
                "FOREIGN KEY(" + COLUMN_uebung_id + ") REFERENCES " + TABLE_NAME + "(" + COLUMN_ID + "));";
        db.execSQL(query2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }
    void addTraining(String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_Date, date);
        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1){
            Toast.makeText(context, "Fehlgeschlagen", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Erfolgreich Hinzugefügt!", Toast.LENGTH_SHORT).show();
        }
    }

    void addUebungen(String name, String weight){
        SQLiteDatabase db2 = this.getWritableDatabase();
        ContentValues cv2 = new ContentValues();

        cv2.put(COLUMN_uebung_name, name);
        cv2.put(COLUMN_uebung_weight, weight);
        long result2 = db2.insert(TABLE_NAME2, null, cv2);
        if (result2 == -1){
            Toast.makeText(context, "Fehlgeschlagen", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Erfolgreich Hinzugefügt!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllDataTraining(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
           cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    Cursor readAllDataUebung(){
        String query2 = "SELECT * FROM " + TABLE_NAME2;
        SQLiteDatabase db2 = this.getReadableDatabase();

        Cursor cursor = null;
        if (db2 != null){
            cursor = db2.rawQuery(query2, null);
        }
        return cursor;
    }
}
