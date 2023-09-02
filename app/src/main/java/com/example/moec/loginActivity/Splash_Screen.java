package com.example.moec.loginActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;


import com.example.moec.JavaClass.InternetConnection;
import com.example.moec.JavaClass.config;
import com.example.moec.MainActivity;
import com.example.moec.R;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import kotlin.text.Charsets;


public class Splash_Screen extends AppCompatActivity {


    Snackbar snackbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView image = findViewById(R.id.logo);

        Animation ani = AnimationUtils.loadAnimation(this,R.anim.imageanimation);
        image.startAnimation(ani);

        SharedPreferences preferences = getSharedPreferences("registrationform",MODE_PRIVATE);
        String emailtext = preferences.getString("email",null);
        String setpassword = preferences.getString("password",null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (emailtext!=null && setpassword!=null) {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
                else
                {
                    startActivity(new Intent(getApplicationContext(), view_Activity_before_login.class));

                }
                overridePendingTransition(R.anim.right_in_activity, R.anim.left_out_activity);
                finish();

            }
        },1000);

    }

}