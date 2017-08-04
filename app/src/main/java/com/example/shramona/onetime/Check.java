package com.example.shramona.onetime;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Shramona on 03-08-2017.
 */

public class Check extends ArrayAdapter<String>{
    private String[] Cname;
    private String[] Csector;
    private String[] Cscore;
    private Activity context;
    public Check(Activity context, String[] Cname, String[] Csector , String[] Cscore) {
        super(context, R.layout.listcheck, Cname);
        this.context = context;
        this.Cname = Cname;
        this.Csector =Csector ;
        this.Cscore = Cscore;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.listcheck, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.compnme);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.sectr);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.scr);

        textViewId.setText(Cname[position]);
        textViewName.setText(Csector[position]);
        textViewEmail.setText(Cscore[position]);

        return listViewItem;

    }
}
