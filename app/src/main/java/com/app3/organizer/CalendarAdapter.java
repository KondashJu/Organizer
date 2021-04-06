package com.app3.organizer;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.MonthDisplayHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Calendar;
import java.util.List;

public class CalendarAdapter extends ArrayAdapter<Button> {
    private int  resource;
    private LayoutInflater inflater;
    private Context context;
    private int year;
    private int month;

    public CalendarAdapter(Context ctx, int resourceId, List<Button> objects, int calendarYear, int calendarMonth) {
        super( ctx, resourceId, objects);
        resource = resourceId;
        inflater = LayoutInflater.from( ctx );
        context = ctx;
        year = calendarYear;
        month = calendarMonth;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent ) {

        convertView = (LinearLayout) inflater.inflate( resource, null );

        Button button = getItem(position);

        Calendar c = Calendar.getInstance();
        MonthDisplayHelper mdh = new MonthDisplayHelper(year, month);
        int y = mdh.getFirstDayOfMonth();
        int n = mdh.getNumberOfDaysInMonth();
        button = (Button) convertView.findViewById(R.id.dateButton);
        if ((position+1)<y || (position+1)> (y+n-1)) {
            button.setText("");
            //button.setBackgroundResource(R.color.white);
        } else {
            button.setText(Integer.toString(position-y+2));
            button.setTag(position-y+2);
        }


        return convertView;
    }


}