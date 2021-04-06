package com.app3.organizer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlanDBHelper extends SQLiteOpenHelper {

    public static final String TABLE_PLANS = "plans";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_TIME = "time";
    public static final String COLUMN_DETAILS = "details";

    private static final String DATABASE_NAME = "plans.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_CREATE = "create table " +    TABLE_PLANS + " (" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_NAME + " text, " + COLUMN_DATE + " text, " + COLUMN_TIME + " text, " + COLUMN_DETAILS + " text);";

    public PlanDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(PlanDBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLANS);
        onCreate(db);

    }


}
