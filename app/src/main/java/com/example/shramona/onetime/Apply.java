package com.example.shramona.onetime;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Apply extends AppCompatActivity {

    public String ComName, ComSec, ComSco;
    private TextView comName,comSec,comSco,ComName1, ComSec1, ComSco1;
    String KEY_FLAG="flag";
    Button apply;
    public static final String KEY_COSECE = "sector";
    public static final String JSON_URL_Apply="http://shramocse.000webhostapp.com/apply.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);
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
        apply=(Button) findViewById(R.id.apply);

        comName.setTypeface(tf);
        comSec.setTypeface(tf);
        comSco.setTypeface(tf);
        ComName1.setTypeface(tf);
        ComSec1.setTypeface(tf);
        ComSco1.setTypeface(tf);
        apply.setTypeface(tf);
    }
    public void apply(View v){
        final String count="Yes";
        final String sectors = comSec.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL_Apply,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    Toast.makeText(Apply.this,response,Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Apply.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
               // params.put(KEY_FLAG,count);
               // Log.i("hello",sectors);
                params.put(KEY_COSECE, sectors);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
