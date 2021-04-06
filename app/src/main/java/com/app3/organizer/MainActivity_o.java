package com.app3.organizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.DatePicker;
import android.app.DatePickerDialog;
import android.app.Dialog;

import java.util.Date;
import java.util.Calendar;


public class MainActivity_o extends AppCompatActivity {

    public final static String EXTRA_DATE = "DATE";
    public final static String EXTRA_MONTH = "MONTH";
    public final static String EXTRA_YEAR = "YEAR";

    static final int DATE_DIALOG_ID = 999;
    private int month, day, year;
    DatePicker datePicker;
    Button setMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_o);

        datePicker = (DatePicker) findViewById(R.id.monthSelector);
        Calendar c = Calendar.getInstance();
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DATE);
        year = c.get(Calendar.YEAR);

        datePicker.init(year, month, day, null);

    }

    public void onClick(View v) {
        showDialog(DATE_DIALOG_ID);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener, year, month, day);

        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new
            DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                    year = selectedYear;
                    month = selectedMonth;
                    day = selectedDay;
                    datePicker.init(year, month, day, null);
                }
            };

    public void onClick2(View v) {
        int yr = datePicker.getYear();
        int mo = datePicker.getMonth();

        Intent intent = new Intent(this, CalendarDisplay.class);
        intent.putExtra(EXTRA_YEAR, yr);
        intent.putExtra(EXTRA_MONTH, mo);
        startActivity(intent);
    }



}
