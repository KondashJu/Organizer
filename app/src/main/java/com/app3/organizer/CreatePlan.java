package com.app3.organizer;

import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

public class CreatePlan extends AppCompatActivity {

    PlansDataSource datasource;
    private TimePicker timePicker;
    private int hour, minute;
    static final int TIME_DIALOG_ID = 999;

    TextView showDate;
    EditText name, details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);
        // Show the Up button in the action bar.
        setupActionBar();
        timePicker = (TimePicker) findViewById(R.id.setTime);
        name = (EditText) findViewById(R.id.nameField);
        details = (EditText) findViewById(R.id.detailsField);

        Intent intent = getIntent();
        String a = intent.getStringExtra(PlansList.EXTRA_MONTH);
        String b = intent.getStringExtra(PlansList.EXTRA_DATE);
        String d = intent.getStringExtra(PlansList.EXTRA_YEAR);

        showDate = (TextView) findViewById(R.id.dateYear);
        showDate.setText("New Plan For " + a + " " + b + ", " + d);
    }

    public void setTime(View v) {
        showDialog(TIME_DIALOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timePickerListener, hour, minute,false);

        }
        return null;
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener = new
            TimePickerDialog.OnTimeSetListener() {
                public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;
                    timePicker.setCurrentHour(hour);
                    timePicker.setCurrentMinute(minute);
                }
            };

    public void onClick(View v) {
        name = (EditText) findViewById(R.id.nameField);
        details = (EditText) findViewById(R.id.detailsField);
        String planName = name.getText().toString();
        String planDetails = details.getText().toString();
        timePicker = (TimePicker) findViewById(R.id.setTime);
        String hr = timePicker.getCurrentHour().toString();
        int minute = timePicker.getCurrentMinute();
        String min = String.format("%02d", minute);

        String planTime = hr + ":" + min;

        Intent intent = getIntent();
        String month = intent.getStringExtra(PlansList.EXTRA_MONTH);
        String date = intent.getStringExtra(PlansList.EXTRA_DATE);
        String year = intent.getStringExtra(PlansList.EXTRA_YEAR);
        String planDate = month+date+year;

        datasource = new PlansDataSource(this);
        datasource.open();

        Plan newPlan = null;
        Plan plan = new Plan(planName, planDate, planTime, planDetails);
        newPlan = datasource.createPlan(plan);
        finish();

    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
}
