package com.example.user.habittracker.data;

import android.provider.BaseColumns;

/**
 * Created by Alexander Rashkov on 6/25/2017.
 */

public final class HabitContract {

    /**
     * Create a private constructor because no one should ever create a {@link HabitContract} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name HabitContract (and an object instance of HabitContract is not needed).
     */
    private HabitContract() {
    }

    public static abstract class HabitEntry implements BaseColumns {

        // This is the public constant for the database's name
        public static final String TABLE_NAME = "habits";

        // Declare public constants for the columns' names
        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "habit";
        public static final String COLUMN_HABIT_TIMES_PER_DAY = "times_per_day";
    }
}
