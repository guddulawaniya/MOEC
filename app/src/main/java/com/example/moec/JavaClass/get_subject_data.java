package com.example.moec.JavaClass;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.interest_area_Adapter;
import com.example.moec.Adapters.most_prefered_destination_Adapter;
import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.onClickInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class get_subject_data {
    ProgressBar progressBar;
    ArrayList<module_all_program> list;
    Context context;
    RecyclerView recyclerView;
    String registrationURL;
    onClickInterface onclickInterface;
    boolean check=false;


    public get_subject_data(ProgressBar progressBar, ArrayList<module_all_program> list, Context context, RecyclerView recyclerView, String registrationURL, onClickInterface onclickInterface,Boolean check) {
        this.progressBar = progressBar;
        this.list = list;
        this.context = context;
        this.recyclerView = recyclerView;
        this.registrationURL = registrationURL;
        this.onclickInterface = onclickInterface;
        this.check = check;
        Getuniversitydata();
    }
    public get_subject_data(ProgressBar progressBar, ArrayList<module_all_program> list, Context context, RecyclerView recyclerView, String registrationURL, onClickInterface onclickInterface) {
        this.progressBar = progressBar;
        this.list = list;
        this.context = context;
        this.recyclerView = recyclerView;
        this.registrationURL = registrationURL;
        this.onclickInterface = onclickInterface;
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

                        for (int i = 0; i < array.length(); ++i) {

                            JSONObject jsonObject = array.getJSONObject(i);

                            String subject = jsonObject.getString("subject");
                            String image = jsonObject.getString("image");
                            list.add(new module_all_program(subject,baseurl+image));
                        }
                    } else {
                        Toast.makeText(context, "failed" + obj, Toast.LENGTH_SHORT).show();
                    }
                    if (check)
                    {
                        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

                    }else
                    {

                        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
                    }

                    interest_area_Adapter adapter = new interest_area_Adapter(list,onclickInterface);
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
