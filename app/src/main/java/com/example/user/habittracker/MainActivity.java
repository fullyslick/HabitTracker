package com.example.user.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.user.habittracker.data.HabitContract.HabitEntry;
import com.example.user.habittracker.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    // Declare private variable holding HabitDbHelper object,
    // for multiple referencing
    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Since there is no UI required for this task, this line is probably obsolete
        setContentView(R.layout.activity_main);

        // To access the database, a subclass of SQLiteOpenHelper is instantiated
        // and the context is passed, which is the current activity.
        mDbHelper = new HabitDbHelper(this);

        // This method inserts data into the database
        insertHabit();

        // Creates a Cursor the holds the returned object from displayCursor() method
        Cursor cursorHabit = displayCursor();

        // This method displays the rows from the Cursor object,
        // passed by displayCursor() method.
        // It shows the rows in the Log.Info
        // but it can be modified to show the rows on the UI.
        // It also closes the cursor object!
        showCursorData(cursorHabit);
    }

    // Shows the database rows when the activity is not destroyed.
    // This method is probably obsolete but since I will use this App for future references
    // it is important for me to know that I should use this method to update UI when the App is
    // called from any background state.
    @Override
    protected void onStart() {
        super.onStart();
        Cursor cursorHabit = displayCursor();
        showCursorData(cursorHabit);
    }

    // This method inserts dummy data, for the purpose of checking the functionality of the app.
    // But it can be also modified and then referenced when the user interacts with the UI
    // The inserted data is: 'Drinking Water' 6 times per day
    private void insertHabit() {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new  ContentValues values object
        // which holds the keys and values for the SQL command
        // The SQL command line should look like this:
        // ( habit, times_per_day) VALUES ( "Drinking water" , 6);
        // I know I can use strings.xml for "Drinking Water" ,
        // but the idea is of this method is only to checking functionality.
        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Drinking Water");
        values.put(HabitEntry.COLUMN_HABIT_TIMES_PER_DAY, 6);

        // Insert the new row, returning the primary key value of the new row.
        // "long newRowId" is obsolete but I just check if the data is properly inserted.
        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);
        // Here I check if the new row is inserted. If it is different from -1 everything is OK
        Log.i("MainActivity", "The newRowId is: " + newRowId);
    }

    // This method returns a Cursor object,
    // which holds the rows returned from the database
    private Cursor displayCursor() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Create new cursor object
        // to retrieve all the rows from the database ( I do not use projection here ).
        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);

        // Return the cursor object
        return cursor;
    }

    // This method gets the Cursor object from displayCursor() method
    // Then create a while loop to take all the rows from the object
    // Display the rows in the log cat
    // And Deletes the cursor object
    private void showCursorData(Cursor cursorHabitsData) {
        try {
            // Get the  column indexes of the data
            int idColumnIndex = cursorHabitsData.getColumnIndex(HabitEntry._ID);
            int nameColumnIndex = cursorHabitsData.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
            int timeColumnIndex = cursorHabitsData.getColumnIndex(HabitEntry.COLUMN_HABIT_TIMES_PER_DAY);

            // Create a while loop to retrieve all the rows from the database
            while (cursorHabitsData.moveToNext()) {
                int currentID = cursorHabitsData.getInt(idColumnIndex);
                String currentHabitName = cursorHabitsData.getString(nameColumnIndex);
                int currentHabitOccupancies = cursorHabitsData.getInt(timeColumnIndex);
                //Display into the Log INFO the cursor's data
                Log.i("MainActivity", "Here is the full database: " + currentID + " - " +
                        currentHabitName + " - " +
                        currentHabitOccupancies);
            }
        } finally {
            // Close the cursor because we have done reading from it. This releases all its
            // resources and makes it invalid.
            cursorHabitsData.close();
        }
    }
}
