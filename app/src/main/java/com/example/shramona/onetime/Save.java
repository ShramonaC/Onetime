package com.example.shramona.onetime;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class Save extends AppCompatActivity {

    public String ComName, ComSec, ComSco;
    private TextView comName,comSec,comSco,ComName1,ComSec1,ComSco1;
    public static final String JSON_URL_Add = "http://shramocse.000webhostapp.com/add.php";
    public static final String KEY_CONAME = "name";
    public static final String KEY_COSEC = "sector";
    public static final String KEY_COSCORE = "score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save);
        try {     /* Get values from Intent */
            String fontPath = "fonts/lato-medium.ttf";
            // Loading Font Face
            Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
            comName = (EditText) findViewById(R.id.ComName);
            comSec= (EditText) findViewById(R.id.ComSec);
            comSco= (EditText) findViewById(R.id.ComSco);
            ComName1 = (TextView) findViewById(R.id.ComName1);
            ComSec1= (TextView) findViewById(R.id.ComSec1);
            ComSco1= (TextView) findViewById(R.id.ComSco1);

            comName.setTypeface(tf);
            comSec.setTypeface(tf);
            comSco.setTypeface(tf);
            ComName1.setTypeface(tf);
            ComSec1.setTypeface(tf);
            ComSco1.setTypeface(tf);
        } catch (ActivityNotFoundException a) {
        }
    }

    public void ok(View v) {
        final String names = comName.getText().toString().trim();
        final String sectors = comSec.getText().toString().trim();
        final String scores = comSco.getText().toString().trim();
        if (names.length() > 0 && sectors.length()>0 && scores.length()>0 ) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL_Add,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Save.this, response, Toast.LENGTH_LONG).show();
                            Log.e(response,"  here");
                            if(response.equals("Added")){
                                Intent intent = new Intent(getApplicationContext(), Last.class);
                                intent.putExtra("cnc",names );
                                intent.putExtra("csc",sectors );
                                intent.putExtra("cscc",scores);
                                startActivity(intent);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Save.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put(KEY_CONAME, names);
                    params.put(KEY_COSEC, sectors);
                    params.put(KEY_COSCORE, scores);
                    return params;
                }

            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
        else{
            Toast.makeText(this,"Please enter all the details",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed()
    {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }
}
