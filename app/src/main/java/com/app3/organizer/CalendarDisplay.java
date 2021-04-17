package com.app3.organizer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.MonthDisplayHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.app.ActionBar;

import java.util.ArrayList;
import java.util.List;

public class CalendarDisplay extends AppCompatActivity {

    public final static String EXTRA_DATE = "DATE";
    public final static String EXTRA_MONTH = "MONTH";
    public final static String EXTRA_YEAR = "YEAR";

    TextView thisMonth;
    GridView daysView;
    GridView calendarView;
    static int a, b;

    String[] months = new String[]{"January", "February", "March", "April", "May", "June",   "July",
            "August", "September", "October", "November", "December"};

    String[] days = new String[]{"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_display);

        //setupActionBar();

        Intent intent = getIntent();
        int a = intent.getIntExtra(MainActivity_o.EXTRA_YEAR, 0);
        int b = intent.getIntExtra(MainActivity_o.EXTRA_MONTH, 0);

        String month = months[b];
        String year = Integer.toString(a);
        thisMonth = (TextView) findViewById(R.id.displayInfo);
        thisMonth.setText(month + "  " + year);

        daysView = (GridView) findViewById(R.id.daysView);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.dow_view, days);
        daysView.setAdapter(adapter);

        MonthDisplayHelper mdh = new MonthDisplayHelper(a, b);
        int y = mdh.getFirstDayOfMonth();

        calendarView = (GridView) findViewById(R.id.calendarView);
        List dates = new ArrayList();
        for (int i = y+1; i < 42; i++) {
            String k = Integer.toString(i);
            Button butt = new Button(this);
            butt.setText(k);
            dates.add(butt);
        }

        calendarView.setAdapter(new CalendarAdapter(this, R.layout.button_view, dates, a, b));

    }

    public void onClick(View v) {
        String sDate = v.getTag().toString();
        Intent intent = getIntent();
        String sMonth = months[intent.getIntExtra(MainActivity_o.EXTRA_MONTH, 0)];
        String sYear = Integer.toString(intent.getIntExtra(MainActivity_o.EXTRA_YEAR, 0));

        Intent intent1 = new Intent(this, PlansList.class);
        intent1.putExtra(EXTRA_DATE, sDate);
        intent1.putExtra(EXTRA_MONTH, sMonth);
        intent1.putExtra(EXTRA_YEAR, sYear);
        startActivity(intent1);

    }


   /*@TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }*/


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
