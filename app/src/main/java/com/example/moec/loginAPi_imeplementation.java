package com.example.moec;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.moec.JavaClass.InternetConnection;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class loginAPi_imeplementation {

    Context context;

    public loginAPi_imeplementation(Context context) {
        this.context = context;
    }

    void RegistrationAPI(String email, String pass) {

//        String sendotp = new DecimalFormat("0000").format(new Random().nextInt(9999));
//        final String message = "Hello ! The One Time Password to login for Staff panel is " + sendotp + " This OTP will expire in 10 minutes Regards, Meridean Overseas Edu Con Pvt Ltd";
//
        String registrationURL = Config.Base_url+"login.php" + "?email=" + email + "&password=" + pass;
//        String sendotpurl = "https://api.datagenit.com/sms?auth=D!~7113Zz8MHFw1mQ&senderid=MOECOE&msisdn=" + mobile + "&message=" + message;
//

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
                    if (status == 0) {


//                        sendotpnumbers sm = new sendotpnumbers(getApplicationContext());
//                        sm.execute(sendotpurl);
//
//                        sendemailotp se = new sendemailotp(getApplicationContext(),email,sendotp);
//                        se.execute();



                    } else {
                        Toast.makeText(context, "Unable to retrive any data on server", Toast.LENGTH_SHORT).show();
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
