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
import com.example.moec.R;
import com.example.moec._set_password;
import com.example.moec.greeting_Activity;
import com.example.moec.offline_Activity;

import java.text.DecimalFormat;
import java.text.NumberFormat;


public class verify_OTP_Activity extends AppCompatActivity {

    PinView pinView;
    TextView resendotp;

    String sendotp="1234";

    String url = "https://api.datagenit.com/sms?auth=D!~7113Zz8MHFw1mQ&senderid=MOECOE&msisdn=";
    boolean rdcheck = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_otp);

        pinView=findViewById(R.id.pinview);
        Button verifybutton = findViewById(R.id.verifybuttonotp);
        TextView showmessage = findViewById(R.id.textView11);
        TextView maintitle = findViewById(R.id.maintitle);
        Intent intent = getIntent();
        String number = intent.getStringExtra("number");
        int id = intent.getIntExtra("id",0);
        String email = intent.getStringExtra("email");
//        String pass = intent.getStringExtra("pass");
//        String name = intent.getStringExtra("name");
        //sendotp = intent.getStringExtra("otp");
        resendotp = findViewById(R.id.resendotp);
        Toast.makeText(verify_OTP_Activity.this, "Default OTP 1234 \n SMS service currently Stop", Toast.LENGTH_SHORT).show();

        InternetConnection nt = new InternetConnection(getApplicationContext());
        timecounter();
        resendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (rdcheck)
                {
                    final String sms = "Hello ! The One Time Password " +
                            "to login for Staff panel is "+sendotp+" This OTP will expire in 10 minutes Regards, Meridean Overseas Edu Con Pvt Ltd";

                    rdcheck=false;
//                  sendotp= new DecimalFormat("0000").format(new Random().nextInt(9999));
                    sendotp="1234";
                    String s = url + number + "&message=" + sms;
                    timecounter();
                    Toast.makeText(verify_OTP_Activity.this, "Default OTP 1234 \n SMS service currently Stop", Toast.LENGTH_SHORT).show();
//                    sendotpnumbers sm = new sendotpnumbers(getApplication());
//                    sm.execute(s);

                }




            }
        });




        verifybutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String enterotpinboxs=pinView.getText().toString();

                if (sendotp.equals(enterotpinboxs) && nt.isConnected())
                {
                    startActivity(new Intent(verify_OTP_Activity.this, _set_password.class));
                    finish();


                    Toast.makeText(verify_OTP_Activity.this, "Verified", Toast.LENGTH_SHORT).show();

                } else if (!nt.isConnected()) {
                    startActivity(new Intent(verify_OTP_Activity.this, offline_Activity.class));
                    finish();

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

            }

            @Override
            public void onFinish() {
                resendotp.setText("Resend");
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