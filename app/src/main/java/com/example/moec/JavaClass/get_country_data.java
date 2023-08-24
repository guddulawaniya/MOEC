package com.example.moec.JavaClass;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.Top_country_pickup_Adapter;
import com.example.moec.Adapters.most_prefered_destination_Adapter;
import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.R;
import com.example.moec.onClickInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class get_country_data {

    ProgressBar progressBar;
    ArrayList<module_all_program> list=new ArrayList<>();
    ArrayList<module_all_program> topcountrylist=new ArrayList<>();
    Context context;
    RecyclerView recyclerView;
    String registrationURL;
    onClickInterface onclickInterface;
    int dashboard_call_check=0;



    public get_country_data(ProgressBar progressBar, Context context, RecyclerView recyclerView, String registrationURL, onClickInterface onclickInterface) {
        this.progressBar = progressBar;
        this.context = context;
        this.recyclerView = recyclerView;
        this.registrationURL = registrationURL;
        this.onclickInterface = onclickInterface;
        Getuniversitydata();
    }
    public get_country_data(ProgressBar progressBar, Context context, RecyclerView recyclerView, String registrationURL, onClickInterface onclickInterface,int dashboard_call_check) {
        this.progressBar = progressBar;
        this.context = context;
        this.recyclerView = recyclerView;
        this.registrationURL = registrationURL;
        this.onclickInterface = onclickInterface;
        this.dashboard_call_check = dashboard_call_check;
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

                        if (dashboard_call_check==1)
                        {
                            for (int i = 1; i < 8; ++i) {


                                JSONObject jsonObject = array.getJSONObject(i);

                                String countryname = jsonObject.getString("country");
                                String image = jsonObject.getString("flag");

                                topcountrylist.add(new module_all_program(countryname,baseurl+image));
                            }
                        }else
                        {
                            for (int i = 1; i < array.length(); ++i) {


                                JSONObject jsonObject = array.getJSONObject(i);

                                String countryname = jsonObject.getString("country");
                                String image = jsonObject.getString("flag");

                                list.add(new module_all_program(countryname,baseurl+image));
                            }
                        }


                    } else {
                        Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
                    }

                    if (dashboard_call_check==1)
                    {
                        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
                        Top_country_pickup_Adapter adapters = new Top_country_pickup_Adapter(context, topcountrylist);
                        recyclerView.setAdapter(adapters);
                    } else
                    {
                        recyclerView.setLayoutManager(new GridLayoutManager(context,3));
                        most_prefered_destination_Adapter adapter = new most_prefered_destination_Adapter(list, context,onclickInterface);
                        recyclerView.setAdapter(adapter);
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
