package com.example.user.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.user.habittracker.data.HabitContract.HabitEntry;

/**
 * Created by Alexander Rashkov on 6/25/2017.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    // Declare database's version and name
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "habit_tracker.db";

    // Construct the HabitDbHelper class
    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Constructs the CREATE TABLE command for SQL and creates the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ENTRIES = "CREATE TABLE " + HabitEntry.TABLE_NAME + " (" +
                HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL," +
                HabitEntry.COLUMN_HABIT_TIMES_PER_DAY + " INTEGER NOT NULL DEFAULT 0);";
        //Create the SQL database
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    // Replaces the database if there is a newer version
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
