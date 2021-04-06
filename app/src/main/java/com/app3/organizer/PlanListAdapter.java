package com.app3.organizer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class PlanListAdapter extends ArrayAdapter<Plan> {
    private int  resource;
    private LayoutInflater inflater;
    private Context context;

    public PlanListAdapter(Context ctx, int resourceId, List<Plan> objects) {
        super( ctx, resourceId, objects );
        resource = resourceId;
        inflater = LayoutInflater.from( ctx );
        context = ctx;
    }

    @Override
    public View getView (int position, View convertView, ViewGroup parent ) {

        convertView = (LinearLayout) inflater.inflate( resource, null );


        Plan plan = getItem(position);

        TextView ptime = (TextView) convertView.findViewById(R.id.pt);
        ptime.setText(plan.getTime() + " ");


        TextView pname = (TextView) convertView.findViewById(R.id.pn);
        pname.setText(plan.getName());


        TextView pdetails = (TextView) convertView.findViewById(R.id.pd);
        pdetails.setText(plan.getDetails());

        return convertView;
    }


}
