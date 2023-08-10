package com.example.moec;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.moec.JavaClass.config;
import com.example.moec.loginActivity.login_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class registrationSaveData {

    Context context;
    ProgressDialog progressBar;

    public registrationSaveData(Context context) {
        this.context = context;
    }



    public void RegistrationAPI(String URL,String title,int id) {
        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(true);
        progressBar.setTitle(title);
        progressBar.setMessage("Please Wait..");
        progressBar.show();



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

                    if (status == 1 &&  id ==1) {


                        SharedPreferences.Editor editor = context.getSharedPreferences("registrationform",MODE_PRIVATE).edit();
                        editor.putString("userid",obj.getString("user_id"));
                        editor.putInt("timeline",1);
                        editor.commit();
                        progressBar.dismiss();
                        moveactivity();


                    }
                    else if (status==0 && id==1) {
                        String message = obj.getString("Message");
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        progressBar.dismiss();

                    }
                    else if (status==1 && id==0) {

                        getdataAPI(obj.getString("user_id"));

                    }
                    else
                    {
                        progressBar.dismiss();
                        String message = obj.getString("Message");
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
        obj.execute(URL);
    }

    void moveactivity()
    {
        context.startActivity(new Intent(context, MainActivity.class));
        ((Activity)context).finish();

    }


    void getdataAPI(String User_id) {


        String registrationURL = config.Base_url+"logingetdata.php?" + "user_id="+User_id;


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

                    if (status == 1) {
                        JSONObject userdata = obj.getJSONObject("userdata");


                        SharedPreferences.Editor editor = context.getSharedPreferences("registrationform",MODE_PRIVATE).edit();
                        editor.putString("Fname",userdata.getString("firstname"));
                        editor.putString("Lname",userdata.getString("lastname"));
                        editor.putString("number",userdata.getString("number"));
                        editor.putString("email",userdata.getString("email"));
                        editor.putString("DOb",userdata.getString("dob"));
                        editor.putString("g",userdata.getString("gender"));
                        editor.putString("pincode",userdata.getString("pincode"));
                        editor.putString("qualification",userdata.getString("course_level"));
                        editor.putString("pre_country",userdata.getString("pre_country"));
                        editor.putString("country",userdata.getString("country"));
                        editor.putString("state",userdata.getString("state"));
                        editor.putString("city",userdata.getString("city"));
                        editor.putString("interest",userdata.getString("insterest_area"));
                        editor.putString("examname",userdata.getString("english_exam"));
                        editor.putString("write",userdata.getString("write_marks"));
                        editor.putString("read",userdata.getString("read_marks"));
                        editor.putString("listen",userdata.getString("listening_marks"));
                        editor.putString("speak",userdata.getString("speaking_marks"));
                        editor.putString("overall",userdata.getString("ovarall_marks"));
                        editor.putString("userid",User_id);
                        editor.commit();


                        Toast.makeText(context, "Welcome Back "+userdata.getString("firstname"), Toast.LENGTH_SHORT).show();
                        progressBar.dismiss();
                        moveactivity();


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
