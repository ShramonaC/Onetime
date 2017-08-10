package com.example.shramona.onetime;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Login extends AppCompatActivity {
    public String phone,OTP,authkey="168079AuFfq5vsv1kE5981a2bd",password,name,otp;



    EditText txtNumber, txtPassword,txtUsername,txtOTP;
    Button btnLogin;
    int k=0;
    TextView otpT,textpass,User,mob,oo;
    AlertDialogManager alert = new AlertDialogManager();
    SessionManagement session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtNumber=(EditText)findViewById(R.id.txtPhone);
        txtPassword=(EditText)findViewById(R.id.txtPassword);
        txtUsername=(EditText)findViewById(R.id.txtUsername);
        txtOTP=(EditText)findViewById(R.id.txtOTP);
        otpT= (TextView) findViewById(R.id.OTPt);
        User=(TextView) findViewById(R.id.User);
        mob=(TextView) findViewById(R.id.mob);
        oo=(TextView) findViewById(R.id.oo);
        btnLogin=(Button) findViewById(R.id.btnLogin);

        txtOTP.setAlpha(0.0f);
        otpT.setAlpha(0.0f);
        textpass=(TextView) findViewById(R.id.textpass);
        textpass.setAlpha(1.0f);

        session= new SessionManagement(getApplicationContext());

        String fontPath = "fonts/lato-medium.ttf";
        Typeface tf = Typeface.createFromAsset(getAssets(), fontPath);
        txtNumber.setTypeface(tf);
        txtPassword.setTypeface(tf);
        txtNumber.setTypeface(tf);
        txtOTP.setTypeface(tf);
        txtUsername.setTypeface(tf);
        otpT.setTypeface(tf);
        btnLogin.setTypeface(tf);
        mob.setTypeface(tf);
        oo.setTypeface(tf);


    }
    public void otpGenerate(View v){

            txtOTP.setAlpha(1.0f);
            otpT.setAlpha(1.0f);
            textpass.setAlpha(0.0f);
            txtPassword.setAlpha(0.0f);
            txtNumber = (EditText) findViewById(R.id.txtPhone);
            phone = txtNumber.getText().toString().trim();
            int paperID = (int) Math.round(Math.random() * (999999 - 100000 + 1) + 100000);
            OTP = Integer.toString(paperID).trim();
            authkey = authkey.trim();
            otp=txtOTP.getText().toString().trim();
        if(phone.length()==10 && phone.length()!=0) {
            String username = txtUsername.getText().toString();
            session.createLoginSession(username);
            String url = "https://control.msg91.com/api/sendotp.php? authkey=" + authkey + "&mobile=" + phone + "&message=" + "Your%20otp%20is%20" + OTP + "&sender=" + "Employer" + "&otp=" + OTP;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Login.this, "Your OTP has been generated!! Please check your message box", Toast.LENGTH_LONG).show();
                            System.out.println(OTP +" OTP of generate isthis");
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Login.this, error.toString(), Toast.LENGTH_LONG).show();
                        }
                    }) {


            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
        else{
            Toast.makeText(Login.this, "Please ener valid phone number", Toast.LENGTH_LONG).show();
        }
    }
    public void login(View v){
        otp=txtOTP.getText().toString().trim();
        phone=txtNumber.getText().toString().trim();
        password=txtPassword.getText().toString().trim();
        name=txtUsername.getText().toString().trim();
        if(phone.length()==10) {
            if (name.length() > 0 && otp.length() > 0 && phone.length() > 0) {
                String url = "https://control.msg91.com/api/verifyRequestOTP.php?authkey=" + authkey + "&mobile=" + phone + "&otp=" + OTP;

                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //Toast.makeText(Login.this, response, Toast.LENGTH_LONG).show();
                                System.out.println(OTP +" OTP of login isthis");
                                System.out.println(otp +"otp isthis");
                                if(otp.equals(OTP)) {
                                    go();
                                }
                                else
                                {
                                    Toast.makeText(Login.this, "Please enter correct OTP", Toast.LENGTH_LONG).show();
                                }

                            }


                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(Login.this, error.toString(), Toast.LENGTH_LONG).show();


                            }
                        }) {


                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            } else if (name.length() > 0 && password.length() > 0 && phone.length() > 0) {
                session.createLoginSession(name);
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            } else if (name.length() == 0 && password.length() > 0 && phone.length() > 0 && otp.length() > 0) {
                Toast.makeText(Login.this, "Please enter your name", Toast.LENGTH_LONG).show();
            } else if (name.length() > 0 && password.length() == 0 && phone.length() > 0 && otp.length() == 0) {
                Toast.makeText(Login.this, "Either enter password or OTP", Toast.LENGTH_LONG).show();
            } else if (name.length() == 0 && password.length() == 0 && phone.length() == 0 && otp.length() == 0) {
                alert.showAlertDialog(Login.this, "Login failed..", "Please enter your details", false);
            }
        }
        else
        {
            alert.showAlertDialog(Login.this, "Login failed..", "Please enter valid phone number", false);
        }
    }
    public void go(){
        Toast.makeText(Login.this,"Welcome to Onetime", Toast.LENGTH_LONG).show();
        session.createLoginSession(name);
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
    public void onBackPressed()
    {
        Log.e("My Tags", "onBackPressed");
        k++;
        if(k == 1)
        {
            Toast.makeText(Login.this, "Please press again to exit activity.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
    }

}
