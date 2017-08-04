package com.example.shramona.onetime;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Alert Dialog Manager
   // AlertDialogManager alert = new AlertDialogManager();
    public static final String JSON_URL = "http://transolver.000webhostapp.com/store.php";
    public static final String JSON_URL_Add = "http://transolver.000webhostapp.com/add.php";
    public static final String KEY_CONAME = "name";
    public static final String KEY_COSEC = "sector";
    public static final String KEY_COSCORE = "score";
   private Button addc;
    int k=0,m=0;
    EditText ComName,ComSec,ComSco;
    private ListView listView;
    // Session Manager Class
    SessionManagement session;

   String[] listValue = new String[] {"ONE","TWO","THREE","FOUR","FIVE","SIX","SEVEN","EIGHT"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Session class instance
        session = new SessionManagement(getApplicationContext());

        TextView lblName = (TextView) findViewById(R.id.lblName);
        ComName=(EditText)findViewById(R.id.ComName);
        ComSec=(EditText)findViewById(R.id.ComSec);
        ComSco=(EditText)findViewById(R.id.ComSco);
        addc =(Button)findViewById(R.id.addc);
        listView = (ListView) findViewById(R.id.lView);
        listView.setAlpha(1.0f);
        ComName.setAlpha(0.0f);
        ComSec.setAlpha(0.0f);
        ComSco.setAlpha(0.0f);
        addc.setAlpha(0.0f);

        session.checkLogin();
        // Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();
        // get user data from session
        HashMap<String, String> user = session.getUserDetails();
        // name
        String nameL = user.get(SessionManagement.KEY_NAME);
        // displaying user data
        lblName.setText(Html.fromHtml("Name: <b>" + nameL + "</b>"));

        getData();

    }

    /* List view*/

    private void getData() {
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showDetails(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void showDetails(String json){
        ParseDetails pd = new ParseDetails(json);
        pd.parseDetails();
       // Check adapter = new Check(MainActivity.this, ParseDetails.coname,ParseDetails.csec,ParseDetails.cscor);
       // listView.setAdapter(adapter);
       ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2, android.R.id.text1, listValue);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
               String abc=ParseDetails.csec[position];
                //Toast.makeText(MainActivity.this,abc,Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, ParseDetails.coname[position], Toast.LENGTH_SHORT).show();
            }
        });
    }
/* List view ends*/

    @Override
    public void onBackPressed()
    {
        Log.e("My Tags", "onBackPressed");
        k++;
        if(k == 1)
        {
            if(m >= 1) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
            else{
                Toast.makeText(MainActivity.this, "Please press again to exit.", Toast.LENGTH_SHORT).show();
        }

        }
    else
        {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id) {
            case R.id.notify_id:
                Intent i = new Intent(getApplicationContext(), About.class);
                startActivity(i);
                return true;
            case R.id.btnLogout:
                session.logoutUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }
    @Override
    public void onClick(View v) {
        listView.setAlpha(0.0f);
        ComName.setAlpha(1.0f);
        ComSec.setAlpha(1.0f);
        ComSco.setAlpha(1.0f);
        addc.setAlpha(1.0f);

        Log.e("My Tags", "onBackPressed");
        m++;

    }

    /* Add custom Company*/
    public void Onadd(View v) {
        final String names = ComName.getText().toString().trim();
        final String sectors = ComSec.getText().toString().trim();
        final String scores = ComSco.getText().toString().trim();
        if (names.length() > 0 && sectors.length()>0 && scores.length()>0 ) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_URL_Add,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(MainActivity.this, response, Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(MainActivity.this, Save.class);
                            intent.putExtra("cn", names);
                            intent.putExtra("cs", sectors);
                            intent.putExtra("csc", scores);
                            startActivity(intent);
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
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

/* Add custom Company ends*/


}




