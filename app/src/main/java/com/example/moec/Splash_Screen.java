package com.example.moec;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView image = findViewById(R.id.logo);

        Animation ani = AnimationUtils.loadAnimation(this,R.anim.imageanimation);
        image.startAnimation(ani);

        InternetConnection nt = new InternetConnection(getApplicationContext());
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (nt.isConnected())
                {
                    startActivity(new Intent(getApplicationContext(),view_Activity_before_login.class));

                }
                else
                {
                    startActivity(new Intent(getApplicationContext(),offline_Activity.class));

                }
                overridePendingTransition(R.anim.right_in_activity,R.anim.left_out_activity);
                finish();


            }
        },2000);


    }
}