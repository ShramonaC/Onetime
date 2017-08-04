package com.example.shramona.onetime;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Save extends AppCompatActivity {

    public String ComName, ComSec, ComSco;
    private TextView comName,comSec,comSco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        try {     /* Get values from Intent */
            Intent intent = getIntent();
            ComName = intent.getStringExtra("cn");
            ComSec = intent.getStringExtra("cs");
            ComSco = intent.getStringExtra("csc");
            comName = (TextView) findViewById(R.id.ComName);
            comName.setText(ComName);
            comSec= (TextView) findViewById(R.id.ComSec);
            comSec.setText(ComSec);
            comSco= (TextView) findViewById(R.id.ComSco);
            comSco.setText(ComSco);
        } catch (ActivityNotFoundException a) {
        }
    }
}
