package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class login_Activity extends AppCompatActivity {
    TextInputEditText mobilenumbertext;
    TextInputLayout layoutnumber;
    CountryCodePicker codePicker;
    Timer mytimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        codePicker=findViewById(R.id.country_code);
        Button sendotpbutton = findViewById(R.id.sendotpbutton);
        mobilenumbertext = findViewById(R.id.mobilenumbertext);
        layoutnumber = findViewById(R.id.mobilenumberlayour);

        InternetConnection nt = new InternetConnection(this);
        getSupportActionBar().hide();
        mytimer = new Timer();
        mytimer.schedule(new TimerTask() {
            @Override
            public void run() {

                timermethod();

            }
        },0,1000);

        mobilenumbertext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (mobilenumbertext.getText().toString().length()>0)
                {
                    layoutnumber.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        sendotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!(mobilenumbertext.getText().toString().isEmpty()) && nt.isConnected())
                {
                    String sendotp= new DecimalFormat("0000").format(new Random().nextInt(9999));
                    final String message = "Hello ! The One Time Password to login for Staff panel is " + sendotp + " This OTP will expire in 10 minutes Regards, Meridean Overseas Edu Con Pvt Ltd";
                    String sendotpurl = "https://api.datagenit.com/sms?auth=D!~7113Zz8MHFw1mQ&senderid=MOECOE&msisdn=" + mobilenumbertext.getText().toString() + "&message=" + message;

                    sendotpnumbers sm = new sendotpnumbers(getApplicationContext());
                    sm.execute(sendotpurl);


                    Intent intent = new Intent(getApplicationContext(),verify_OTP_Activity.class);
                    intent.putExtra("otp",sendotp);
                    intent.putExtra("number",mobilenumbertext.getText().toString());
                    startActivity(intent);
                    finish();

                }
                else
                {
                    layoutnumber.setBoxStrokeErrorColor(ColorStateList.valueOf(Color.RED));
                    layoutnumber.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
                    layoutnumber.setError("Required*");
                    layoutnumber.setErrorTextColor(ColorStateList.valueOf(Color.RED));
                    mobilenumbertext.requestFocus();
                }
            }
        });







    }

    private void timermethod()
    {
        this.runOnUiThread(time_click);
    }

    Runnable time_click = new Runnable() {

        @Override
        public void run() {

//            mytimer.cancel();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    String country_code=codePicker.getSelectedCountryCode();
                    layoutnumber.setPrefixText("+"+country_code+" ");

                }
            },1000);

        }

    };

//    @Override
//    protected void onStart() {
//
//        if (mobilenumbertext!=null)
//        {
//            startActivity(new Intent(login_Activity.this, MainActivity.class));
//            Toast.makeText(this, "Welcome Back ", Toast.LENGTH_LONG).show();
//            finish();
//        }
//        super.onStart();
//    }




    void logincode(String  number)

    {
        String url = "";
        String addurl = url+"?mobilenumber="+number;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, addurl, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject obj = new JSONObject(response);
                    String status = obj.getString("status");

                    if (status.equals("True"))
                    {
                        SharedPreferences.Editor editor = getSharedPreferences("savenumber",MODE_PRIVATE).edit();
                        editor.putString("number",number);
                        editor.commit();

                        Intent intent = new Intent(getApplicationContext(), verify_OTP_Activity.class);
                        startActivity(intent);
                        finish();
                    }



                }
                catch (Exception e)
                {
                    Toast.makeText(login_Activity.this, "Error"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login_Activity.this, "Error"+error.getMessage(), Toast.LENGTH_SHORT).show();

            }


        });

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}