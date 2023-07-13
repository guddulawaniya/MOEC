package com.example.moec.loginActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.moec.Check_password;
import com.example.moec.JavaClass.InternetConnection;
import com.example.moec.MainActivity;
import com.example.moec.R;
import com.example.moec.offline_Activity;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView image = findViewById(R.id.logo);

        Animation ani = AnimationUtils.loadAnimation(this,R.anim.imageanimation);
        image.startAnimation(ani);

        SharedPreferences  sharedPreferences = getSharedPreferences("logindetail", Context.MODE_PRIVATE);

        int userid = sharedPreferences.getInt("userid",0);


        InternetConnection nt = new InternetConnection(getApplicationContext());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (nt.isConnected())
                {
                    if (userid!=0)
                    {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(R.anim.right_in_activity,R.anim.left_out_activity);
                        finish();
                    }
                    else
                    {
                        startActivity(new Intent(getApplicationContext(), view_Activity_before_login.class));
                        overridePendingTransition(R.anim.right_in_activity,R.anim.left_out_activity);
                        finish();
                    }
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(), offline_Activity.class));
                    overridePendingTransition(R.anim.right_in_activity,R.anim.left_out_activity);
                    finish();

                }



            }
        },2000);
    }
}