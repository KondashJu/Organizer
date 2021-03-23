package com.app3.organizer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity_o extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView thedate;
    private Button btncalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_o);
        thedate = (TextView) findViewById(R.id.date);
        btncalendar = (Button) findViewById(R.id.btncalendar);

        Intent incoming = getIntent();
        String date = incoming.getStringExtra("date");
        thedate.setText(date);

        btncalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_o.this,CalendarActivity.class);
                startActivity(intent);
            }
        });
    }
}
