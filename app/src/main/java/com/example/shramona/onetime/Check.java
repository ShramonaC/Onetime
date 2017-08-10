package com.example.shramona.onetime;

import android.app.Activity;
import android.graphics.Typeface;
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
    Typeface tf;
    private TextView ComName1, ComSec1, ComSco1;
    public Check(Activity context, String[] Cname, String[] Csector , String[] Cscore) {
        super(context, R.layout.listcheck, Cname);
        this.context = context;
        this.Cname = Cname;
        this.Csector =Csector ;
        this.Cscore = Cscore;
        String fontPath = "fonts/lato-medium.ttf";
        tf = Typeface.createFromAsset(context.getAssets(), fontPath);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.listcheck, null, true);
        TextView textViewId = (TextView) listViewItem.findViewById(R.id.compnme);
        TextView textViewName = (TextView) listViewItem.findViewById(R.id.sectr);
        TextView textViewEmail = (TextView) listViewItem.findViewById(R.id.scr);
        ComName1 = (TextView) listViewItem.findViewById(R.id.ComName1);
        ComSec1= (TextView) listViewItem.findViewById(R.id.ComSec1);
        ComSco1= (TextView) listViewItem.findViewById(R.id.ComSco1);

        textViewId.setText(Cname[position]);
        textViewName.setText(Csector[position]);
        textViewEmail.setText(Cscore[position]);

        textViewId.setTypeface(tf);
        textViewName.setTypeface(tf);
        textViewEmail.setTypeface(tf);

        ComName1.setTypeface(tf);
        ComSec1.setTypeface(tf);
        ComSco1.setTypeface(tf);


        return listViewItem;

    }
}
