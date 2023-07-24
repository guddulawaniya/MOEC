package com.example.moec.loginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;


import com.example.moec.R;


public class Splash_Screen extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView image = findViewById(R.id.logo);

        Animation ani = AnimationUtils.loadAnimation(this,R.anim.imageanimation);
        image.startAnimation(ani);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    startActivity(new Intent(getApplicationContext(), view_Activity_before_login.class));
                    overridePendingTransition(R.anim.right_in_activity, R.anim.left_out_activity);
                    finish();


            }
        },1000);
    }

}