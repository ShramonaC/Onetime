package com.example.shramona.onetime;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class About extends AppCompatActivity {

    TextView f1,f2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        f1= (TextView) findViewById(R.id.f1);
        f2= (TextView) findViewById(R.id.f2);
        String fontPath = "fonts/lato-medium.ttf";
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        f1.setTypeface(tf);
        f2.setTypeface(tf);
    }
}
