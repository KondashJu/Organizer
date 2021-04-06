package com.app3.organizer;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class PlansList extends AppCompatActivity {

    public final static String EXTRA_DATE = "DATE";
    public final static String EXTRA_MONTH = "MONTH";
    public final static String EXTRA_YEAR = "YEAR";

    PlansDataSource datasource;
    TextView listTitle;
    ListView theList;
    Button newPlan;
    EditText planName, planDetails, planTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plans_list);
        setupActionBar();

        datasource = new PlansDataSource(this);
        datasource.open();

        Intent intent = getIntent();
        String yr = intent.getStringExtra(CalendarDisplay.EXTRA_YEAR);
        String mo = intent.getStringExtra(CalendarDisplay.EXTRA_MONTH);
        String day = intent.getStringExtra(CalendarDisplay.EXTRA_DATE);
        String date = mo+day+yr;

        listTitle = (TextView) findViewById(R.id.listTitle);
        listTitle.setText("Plans for " + mo + " " + day + ", " + yr);


        theList = (ListView) findViewById(R.id.theList);
        List<Plan> myPlans = datasource.getAllPlansOnDate(date);
        theList.setAdapter(new PlanListAdapter(this, R.layout.list_row, myPlans));
        theList.setEmptyView(findViewById(R.id.empty));

    }

    public void newPlan(View v) {
        Intent intent = getIntent();
        String yr = intent.getStringExtra(CalendarDisplay.EXTRA_YEAR);
        String mo = intent.getStringExtra(CalendarDisplay.EXTRA_MONTH);
        String day = intent.getStringExtra(CalendarDisplay.EXTRA_DATE);

        Intent intent1 = new Intent(this, CreatePlan.class);
        intent1.putExtra(EXTRA_YEAR, yr);
        intent1.putExtra(EXTRA_MONTH, mo);
        intent1.putExtra(EXTRA_DATE, day);
        startActivity(intent1);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }


}
