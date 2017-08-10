package com.example.shramona.onetime;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Last extends AppCompatActivity {
    public String ComName, ComSec, ComSco;
    private TextView comName,comSec,comSco,ComName1, ComSec1, ComSco1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last);
        String fontPath = "fonts/lato-medium.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        Intent intent = getIntent();
        ComName = intent.getStringExtra("cnc");
        ComSec = intent.getStringExtra("csc");
        ComSco = intent.getStringExtra("cscc");
        comName = (TextView) findViewById(R.id.ComName);
        comName.setText(ComName);
        comSec= (TextView) findViewById(R.id.ComSec);
        comSec.setText(ComSec);
        comSco= (TextView) findViewById(R.id.ComSco);
        comSco.setText(ComSco);
        ComName1 = (TextView) findViewById(R.id.ComName1);
        ComSec1= (TextView) findViewById(R.id.ComSec1);
        ComSco1= (TextView) findViewById(R.id.ComSco1);


        comName.setTypeface(tf);
        comSec.setTypeface(tf);
        comSco.setTypeface(tf);
        ComName1.setTypeface(tf);
        ComSec1.setTypeface(tf);
        ComSco1.setTypeface(tf);
    }
    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
