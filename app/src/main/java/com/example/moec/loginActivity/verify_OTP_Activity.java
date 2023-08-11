package com.example.moec.loginActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chaos.view.PinView;
import com.example.moec.JavaClass.InternetConnection;
import com.example.moec.JavaClass.sendemailotp;
import com.example.moec.JavaClass.sendotpnumbers;
import com.example.moec.R;
import com.example.moec._set_password;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;


public class verify_OTP_Activity extends AppCompatActivity {

    PinView pinView,emailpinbox;
    TextView resendotp,emailresend;

    String numberotp="1234",emailotp="1234";

    String url = "https://api.datagenit.com/sms?auth=D!~7113Zz8MHFw1mQ&senderid=MOECOE&msisdn=";
    boolean rdcheck = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        pinView=findViewById(R.id.pinview);
        emailpinbox=findViewById(R.id.emailpinbox);
        Button verifybutton = findViewById(R.id.verifybuttonotp);

        Intent intent = getIntent();
        String number = intent.getStringExtra("number");
        String email = intent.getStringExtra("email");


      /*  numberotp = intent.getStringExtra("numberotp");
        emailotp = intent.getStringExtra("emailotp");*/
        resendotp = findViewById(R.id.resendotp);
        emailresend = findViewById(R.id.emailresend);


        InternetConnection nt = new InternetConnection(getApplicationContext());
        timecounter();
        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (rdcheck)
                {
                    rdcheck=false;
                    numberotp= new DecimalFormat("0000").format(new Random().nextInt(9999));
                    final String sms = "Hello ! The One Time Password " +
                            "to login for Staff panel is "+numberotp+" This OTP will expire in 10 minutes Regards, Meridean Overseas Edu Con Pvt Ltd";

                    timecounter();
                    String s = url + number + "&message=" + sms;
                    Toast.makeText(verify_OTP_Activity.this, "number OTP : "+numberotp, Toast.LENGTH_SHORT).show();

//                    sendotpnumbers sm = new sendotpnumbers(getApplication());
//                    sm.execute(s);

                }
            }
        });
        emailresend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rdcheck)
                {
                    rdcheck=false;
                    emailotp= new DecimalFormat("0000").format(new Random().nextInt(9999));
                    final String sms = "Hello ! The One Time Password " +
                            "to login for Staff panel is "+emailotp+" This OTP will expire in 10 minutes Regards, Meridean Overseas Edu Con Pvt Ltd";

                    timecounter();
                    String s = url + number + "&message=" + sms;
                    Toast.makeText(verify_OTP_Activity.this, "number OTP : "+emailotp, Toast.LENGTH_SHORT).show();


//                    sendemailotp sm = new sendemailotp(verify_OTP_Activity.this,email,emailotp);
//                    sm.execute(s);

                }
            }
        });




        verifybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String numberinputotp=pinView.getText().toString();
                String emailinputotp=emailpinbox.getText().toString();

                if (numberotp.equals(numberinputotp) && emailotp.equals(emailinputotp) && nt.isConnected())
                {
                    startActivity(new Intent(verify_OTP_Activity.this, _set_password.class));
                    finish();


                    Toast.makeText(verify_OTP_Activity.this, "Verified", Toast.LENGTH_SHORT).show();

                } else if (!nt.isConnected()) {
                    Toast.makeText(verify_OTP_Activity.this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();

                }
                else {
                    pinView.startAnimation(AnimationUtils.loadAnimation(getApplication(),R.anim.shake_text));
                    pinView.setLineColor(Color.RED);
                    Toast toast=   Toast.makeText(verify_OTP_Activity.this, "Invalid OTP ", Toast.LENGTH_SHORT);
                    toast.show();
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            toast.cancel();

                        }
                    },1000);

                }

            }
        });



    }



    void timecounter()
    {

        new CountDownTimer(30000,1000)
        {

            @Override
            public void onTick(long l) {


                NumberFormat format = new DecimalFormat("00");
                long sec = (l/1000) % 60;
                resendotp.setText("00 : "+format.format(sec));
                emailresend.setText("00 : "+format.format(sec));

            }

            @Override
            public void onFinish() {
                resendotp.setText("Resend");
                emailresend.setText("Resend");
                rdcheck = true;


            }
        }.start();
    }

    @Override
    public void onBackPressed() {
        overridePendingTransition(R.anim.left_in,R.anim.right_out);
        super.onBackPressed();
    }


}