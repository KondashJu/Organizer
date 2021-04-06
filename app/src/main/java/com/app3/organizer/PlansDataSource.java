package com.app3.organizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PlansDataSource {

    private SQLiteDatabase database;
    private PlanDBHelper dbHelper;
    private String[] allColumns = {PlanDBHelper.COLUMN_ID, PlanDBHelper.COLUMN_NAME,
            PlanDBHelper.COLUMN_DATE, PlanDBHelper.COLUMN_TIME,
            PlanDBHelper.COLUMN_DETAILS };

    public PlansDataSource(Context context){
        dbHelper = new PlanDBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Plan createPlan(Plan plan) {
        ContentValues values = new ContentValues();
        values.put(PlanDBHelper.COLUMN_NAME, plan.getName());
        values.put(PlanDBHelper.COLUMN_DATE, plan.getDate());
        values.put(PlanDBHelper.COLUMN_TIME, plan.getTime());
        values.put(PlanDBHelper.COLUMN_DETAILS, plan.getDetails());
        long insertId = database.insert(PlanDBHelper.TABLE_PLANS, null, values);
        Cursor cursor = database.query(PlanDBHelper.TABLE_PLANS, allColumns,
                PlanDBHelper.COLUMN_ID + " = " + insertId, null,
                null, null, null);
        cursor.moveToFirst();
        Plan newPlan = cursorToPlan(cursor);
        cursor.close();
        return newPlan;

    }

    public void deletePlan(Plan plan) {
        long id = plan.getId();
        System.out.println("Plan deleted with id: " + id);
        database.delete(PlanDBHelper.TABLE_PLANS, PlanDBHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<Plan> getAllPlansOnDate(String planDate) {
        List<Plan> plans = new ArrayList<Plan>();
        Cursor cursor = database.query(true, PlanDBHelper.TABLE_PLANS, allColumns,
                PlanDBHelper.COLUMN_DATE + " =?" , new String[]{planDate}, null,
                null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Plan plan = cursorToPlan(cursor);
            plans.add(plan);
            cursor.moveToNext();
        }
        cursor.close();
        return plans;
    }

    private Plan cursorToPlan(Cursor cursor) {
        Plan plan = new Plan(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
        plan.setId(cursor.getLong(0));
        plan.setName(cursor.getString(1));
        plan.setDate(cursor.getString(2));
        plan.setTime(cursor.getString(3));
        plan.setDetails(cursor.getString(4));
        return plan;
    }
}
