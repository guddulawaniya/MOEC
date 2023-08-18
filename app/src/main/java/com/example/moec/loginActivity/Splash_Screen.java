package com.example.moec.loginActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;


import com.example.moec.JavaClass.InternetConnection;
import com.example.moec.JavaClass.config;
import com.example.moec.MainActivity;
import com.example.moec.R;

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

        InternetConnection nt = new InternetConnection(this);


        if (emailtext!=null && setpassword!=null) {
            logindata(emailtext,setpassword);
        }
        else
        {
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

    void logindata(String emailtext, String setpassword) {


        String pass = null;
        try {
            pass = URLEncoder.encode(setpassword, Charsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }


        String registrationURL = config.Base_url + "loginApi_data?" + "username=" + emailtext + "&password=" + pass;


        class registration extends AsyncTask<String, String, String> {

            @Override
            protected void onPostExecute(String s) {

                try {
                    JSONObject object = new JSONObject(s);
                    String status = object.getString("success");

                    if (status.equals("true")) {

                        JSONArray array = object.getJSONArray("setpreference");
                        JSONObject userdata = array.getJSONObject(0);

                        SharedPreferences.Editor editor = getSharedPreferences("registrationform", MODE_PRIVATE).edit();

                        String first = userdata.getString("first_name");
                        String last = userdata.getString("last_name");
                        String email = userdata.getString("email");
                        String number = userdata.getString("mobile_no");
                        String DOb = userdata.getString("date_of_birth");
                        String gender = userdata.getString("gender");
                        String country = userdata.getString("mailing_country");
                        String state = userdata.getString("mailing_state");
                        String city = userdata.getString("mailing_city");
                        String address = userdata.getString("mailing_address");
                        String pincode = userdata.getString("mailing_pincode");
                        String intrest = userdata.getString("intrest");
                        String english = userdata.getString("english");
                        String subject = userdata.getString("subject");
                        String gpa = userdata.getString("gpa");
                        String intake = userdata.getString("intake");
                        String country_pref_1 = userdata.getString("country_pref_1");
                        String course_pref_2 = userdata.getString("course_pref_1");
                        String level = userdata.getString("level");
                        String nationality = userdata.getString("nationality");
                        String timeline = userdata.getString("student_status");
                        String marital_status = userdata.getString("marital_status");


                        editor.putString("Fname", first);
                        editor.putString("Lname", last);
                        editor.putString("email", email);
                        editor.putString("number", number);
                        editor.putString("DOb", DOb);
                        editor.putString("g", gender);
                        editor.putString("country", country);
                        editor.putString("state", state);
                        editor.putString("city", city);
                        editor.putString("address", address);
                        editor.putString("pincode", pincode);
                        editor.putString("interest", intrest);
                        editor.putString("examname", english);
                        editor.putString("qualification", subject+"-"+gpa);
                        editor.putString("intake", intake);
                        editor.putString("pre_country", country_pref_1);
                        editor.putString("subject", course_pref_2);
                        editor.putString("courselevel", level);
                        editor.putString("nationality", nationality);
                        editor.putString("timeline", timeline);
                        editor.putString("marital", marital_status);
                        editor.commit();

                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.right_in_activity, R.anim.left_out_activity);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "failed"+object, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                super.onPostExecute(s);
            }
            @Override
            protected String doInBackground(String... param) {

                try {
                    URL url = new URL(param[0]);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    return br.readLine();
                } catch (Exception ex) {
                    return ex.getMessage();
                }
            }
        }
        registration obj = new registration();
        obj.execute(registrationURL);
    }

}