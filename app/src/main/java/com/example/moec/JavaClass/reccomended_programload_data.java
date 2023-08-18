package com.example.moec.JavaClass;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.All_program_Adapter;
import com.example.moec.Adapters.reccomended_program_Adapter;
import com.example.moec.ModulesClass.module_all_program;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class reccomended_programload_data {

    ProgressBar progressBar;
    ArrayList<module_all_program> list;
    Context context;
    String registrationURL;
    RecyclerView recyclerView;

    public reccomended_programload_data(ProgressBar progressBar, ArrayList<module_all_program> list, Context context, String registrationURL, RecyclerView recyclerView) {
        this.progressBar = progressBar;
        this.list = list;
        this.context = context;
        this.registrationURL = registrationURL;
        this.recyclerView = recyclerView;
        Getuniversitydata();
    }

    void Getuniversitydata() {

        progressBar.setVisibility(View.VISIBLE);
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

                    String baseurl = obj.getString("logobaseurl");

                    if (status.equals("true")) {
                        progressBar.setVisibility(View.GONE);

                        JSONArray array = obj.getJSONArray("data");

                        for (int i = 0; i < 6; ++i) {

                            JSONObject jsonObject = array.getJSONObject(i);
                            String coursename = jsonObject.getString("course");
                            String universityname = jsonObject.getString("name");
                            String logo = jsonObject.getString("logo");
                            String countryname = jsonObject.getString("country_id");
                            String fees = jsonObject.getString("fees");
                            String duration = jsonObject.getString("duration");
                            String OfficalLink = jsonObject.getString("links");
                            String intake = jsonObject.getString("intakes");
                            String criteria = jsonObject.getString("criteria");
                            String courseid = jsonObject.getString("id");
                            String favoratevalue = jsonObject.getString("favorites");

                            list.add(new module_all_program(coursename, duration, fees, countryname, universityname, baseurl + logo, intake, OfficalLink, criteria, courseid, favoratevalue));
                        }
                    } else {
                        Toast.makeText(context, "failed" + obj, Toast.LENGTH_SHORT).show();

                    }
                    reccomended_program_Adapter adapter = new reccomended_program_Adapter(context,list,1);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                    recyclerView.setAdapter(adapter);




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
