package com.example.moec.JavaClass;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.example.moec.ModulesClass.module_all_program;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class updateAPIcall {



    Context context;
    String courseid,sms;

    public updateAPIcall(Context context, String courseid, String sms) {
        this.context = context;
        this.courseid = courseid;
        this.sms = sms;
        getfavoratedata();
    }

    void getfavoratedata() {

        String registrationURL = config.Base_url + "favoritesCourseUpdateApi?"+"courseid="+courseid+"&like="+sms;

        class registration extends AsyncTask<String, String, String> {

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
