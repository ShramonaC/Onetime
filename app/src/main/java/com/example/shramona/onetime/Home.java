package com.example.shramona.onetime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Home extends AppCompatActivity {
    public static final String JSON_URL = "http://transolver.000webhostapp.com/store.php";
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        listView = (ListView) findViewById(R.id.lView);
        getData();
    }



    /* List view*/

    private void getData() {
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showDetails(response);
                        Toast.makeText(Home.this,"added",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Home.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showDetails(String json){
        ParseDetails pd = new ParseDetails(json);
        pd.parseDetails();

       Check cl = new Check(Home.this, ParseDetails.coname,ParseDetails.csec,ParseDetails.cscor);
        listView.setAdapter(cl);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String abc=ParseDetails.coname[position];
                Toast.makeText(Home.this,abc,Toast.LENGTH_SHORT).show();
                Toast.makeText(Home.this, ParseDetails.coname[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
/* List view ends*/
}
