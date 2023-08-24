package com.example.moec.JavaClass;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.moec.Adapters.Top_country_pickup_Adapter;
import com.example.moec.Adapters.most_prefered_destination_Adapter;
import com.example.moec.ModulesClass.module_all_program;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class registration extends AsyncTask<String, String, String> {

    @Override
    protected void onPostExecute(String s) {

        try {
            JSONObject obj = new JSONObject(s);
            String status = obj.getString("success");

            if (status.equals("true")) {

                JSONArray array = obj.getJSONArray("data");


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

