package com.example.shramona.onetime;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

import static com.example.shramona.onetime.ParseDetails.coname;
import static com.example.shramona.onetime.ParseDetails.cscor;
import static com.example.shramona.onetime.ParseDetails.csec;

public class MainActivity extends AppCompatActivity {
    public static final String JSON_URL = "http://shramocse.000webhostapp.com/store.php";
   private Button addc;
    int k=0,m=0;
    EditText ComName,ComSec,ComSco;
    private ListView listView;
    // Session Manager Class
    SessionManagement session;
    ProgressDialog pd;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Session class instance
        session = new SessionManagement(getApplicationContext());

        TextView lblName = (TextView) findViewById(R.id.lblName);
       /* TextView textViewId = (TextView) findViewById(R.id.compnme);
        TextView textViewName = (TextView) findViewById(R.id.sectr);
        TextView textViewEmail = (TextView) findViewById(R.id.scr);
        TextView compnme1 = (TextView) findViewById(R.id.compnme1);
        TextView sectr1 = (TextView) findViewById(R.id.sectr1);
        TextView scr1 = (TextView) findViewById(R.id.scr1);*/
        pd = ProgressDialog.show(MainActivity.this, "", "Loading, please wait!");

        listView = (ListView) findViewById(R.id.lView);
        listView.setAlpha(1.0f);

        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        String nameL = user.get(SessionManagement.KEY_NAME);
        // displaying user data
        lblName.setText(Html.fromHtml("Name: <b>" + nameL + "</b>"));

        getData();

        String fontPath = "fonts/lato-medium.ttf";
        // Loading Font Face
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        lblName.setTypeface(tf);
       /* textViewId.setTypeface(tf);
        textViewName.setTypeface(tf);
        textViewEmail.setTypeface(tf);
        compnme1.setTypeface(tf);
        sectr1.setTypeface(tf);
        scr1.setTypeface(tf);*/
    }

    /* List view*/

    private void getData() {
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        pd.dismiss();
                        showDetails(response);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        pd.dismiss();
                        Toast.makeText(MainActivity.this,"Please connect to the internet",Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showDetails(String json){
        ParseDetails pd = new ParseDetails(json);
        pd.parseDetails();
        Check adapter = new Check(MainActivity.this, coname,ParseDetails.csec,ParseDetails.cscor);
       listView.setAdapter(adapter);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(MainActivity.this, Apply.class);
                intent.putExtra("cnc", coname[position]);
                intent.putExtra("csc", csec[position]);
                intent.putExtra("cscc", cscor[position]);
                startActivity(intent);

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
            Toast.makeText(MainActivity.this, "Please press again to exit.", Toast.LENGTH_SHORT).show();

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
            case R.id.addcmpy:
                Intent intent = new Intent(getApplicationContext(), Save.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public class FontChangeCrawler
    {
        private Typeface typeface;

        public FontChangeCrawler(Typeface typeface)
        {
            this.typeface = typeface;
        }

        public FontChangeCrawler(AssetManager assets, String assetsFontFileName)
        {
            typeface = Typeface.createFromAsset(assets, assetsFontFileName);
        }

        public void replaceFonts(ViewGroup viewTree)
        {
            View child;
            for(int i = 0; i < viewTree.getChildCount(); ++i)
            {
                child = viewTree.getChildAt(i);
                if(child instanceof ViewGroup)
                {
                    // recursive call
                    replaceFonts((ViewGroup)child);
                }
                else if(child instanceof TextView)
                {
                    // base case
                    ((TextView) child).setTypeface(typeface);
                }
            }
        }
    }

    @Override
    public void setContentView(View view)
    {
        super.setContentView(view);

        FontChangeCrawler fontChanger = new FontChangeCrawler(getAssets(), "lato-medium.otf");
        fontChanger.replaceFonts((ViewGroup)this.findViewById(android.R.id.content));

    }

}




