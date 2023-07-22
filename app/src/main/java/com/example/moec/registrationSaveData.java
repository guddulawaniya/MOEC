package com.example.moec;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.ProgressDialog;
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

public class registrationSaveData {

    Context context;

    public registrationSaveData(Context context) {
        this.context = context;
    }


    void RegistrationAPI() {
        ProgressDialog progressBar = new ProgressDialog(context);
        progressBar.setCancelable(true);
        progressBar.setTitle("Create User");
        progressBar.setMessage("Please Wait..");
        progressBar.show();





        SharedPreferences preferences = context.getSharedPreferences("registrationform", Context.MODE_PRIVATE);


        String firstname = preferences.getString("Fname",null);
        String lastname = preferences.getString("Lname",null);
        String mobilenumber = preferences.getString("number",null);
        String email = preferences.getString("email",null);
        String dob = preferences.getString("DOb",null);
        String pincode = preferences.getString("pincode",null);
        String gender = preferences.getString("g",null);
        String courselevel = preferences.getString("qualification",null);
        String password = preferences.getString("password",null);
        String country = preferences.getString("countryname",null);
        String subject = preferences.getString("interest",null);
        String exam = preferences.getString("examname",null);
        String writescore = preferences.getString("write",null);
        String readscore = preferences.getString("read",null);
        String listening = preferences.getString("listen",null);
        String speaking = preferences.getString("speak",null);
        String ovarall = preferences.getString("overall",null);


        String registrationURL ="https://android.merideanoverseas.in/registration.php?"+
                "firstname=" +firstname+
                "&lastname=" +lastname+
                "&mobilenumber=" +mobilenumber+
                "&emailid=" +email+
                "&dob=" +dob+
                "&pincode_area=" +pincode+
                "&gender=" +gender+
                "&courselevel=" +courselevel+
                "&pass=" +password+
                "&country=" +country+
                "&subject=" +subject+
                "&exam=" +exam+
                "&writescore=" +writescore+
                "&readscore=" +readscore+
                "&listening=" +listening+
                "&speaking=" +speaking+
                "&ovarall="+ovarall;


        class registration extends AsyncTask<String, String, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                progressBar.show();

                try {
                    JSONObject obj = new JSONObject(s);
                    int status = obj.getInt("status");

                    String message = obj.getString("Message");


                    if (status == 1) {

                        String userid = obj.getString("user_id");

                        Toast.makeText(context, "User Successfully Created ", Toast.LENGTH_SHORT).show();
                        SharedPreferences.Editor editor = context.getSharedPreferences("registrationform",MODE_PRIVATE).edit();
                        editor.putString("userid",userid);
                        editor.putInt("timeline",1);
                        editor.commit();

                        context.startActivity(new Intent(context, MainActivity.class));
                        ((Activity)context).finish();

                        progressBar.dismiss();

                    }
                    else
                    {
                        progressBar.dismiss();
                        Toast.makeText(context, "Error : "+message, Toast.LENGTH_SHORT).show();
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
