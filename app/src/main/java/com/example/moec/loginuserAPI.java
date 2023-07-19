package com.example.moec;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.moec.loginActivity.login_Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class loginuserAPI {

    Context context;

    void RegistrationAPI(String email, String pass) {

        String registrationURL = Config.Base_url+"login.php" + "?email=" + email + "&password=" + pass;


        class registration extends AsyncTask<String, String, String> {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {

                try {
                    JSONObject obj = new JSONObject(s);
                    int status = obj.getInt("status");

                    String message = obj.getString("Message");
                    int userid = obj.getInt("user_id");

                    if (status == 1) {

                        Toast.makeText(context, ""+message, Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = context.getSharedPreferences("logindetail",MODE_PRIVATE).edit();
                        editor.putInt("userid",userid);
                        editor.commit();


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
