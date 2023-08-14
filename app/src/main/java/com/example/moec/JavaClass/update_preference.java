package com.example.moec.JavaClass;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.moec.R;
import com.example.moec.preference_Actvity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class update_preference {

    Context context;

    public update_preference(Context context) {
        this.context = context;
    }

    AlertDialog.Builder builder;
    ProgressDialog progressBar;

   public void UpdatesAPI(String updatesURL) {

        progressBar = new ProgressDialog(context);
        progressBar.setCancelable(true);
        progressBar.setIcon(R.drawable.logo_symbol_colour);
        progressBar.setTitle("Update User details");
        progressBar.setMessage("Please Wait..");


        builder = new AlertDialog.Builder(context);
        builder.setTitle("Update User details");

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
                    String status = obj.getString("success");

                    if (status.equals("true")) {
                        progressBar.dismiss();

                        Toast.makeText(context, "Successfully Updated exam details" , Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(context, "failed" + obj, Toast.LENGTH_SHORT).show();
                        progressBar.dismiss();

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
        obj.execute(updatesURL);


    }
}
