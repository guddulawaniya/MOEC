package com.example.moec.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.All_program_Adapter;
import com.example.moec.Adapters.Univerity_Course_Adapter;
import com.example.moec.JavaClass.config;
import com.example.moec.MainActivity;
import com.example.moec.ModulesClass.Univerity_Course_Module;
import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.R;
import com.example.moec.loginActivity.login_Activity;
import com.example.moec.onClickInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class All_Programs_fragment extends Fragment {


    ArrayList<module_all_program> list;
    AlertDialog.Builder builder;
    ProgressDialog progressBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all__programs_fragment, container, false);
        progressBar = new ProgressDialog(getContext());
        progressBar.setCancelable(true);
        progressBar.setIcon(R.drawable.logo_symbol_colour);
        progressBar.setTitle("Loading");
        progressBar.setMessage("Please Wait..");
        builder = new AlertDialog.Builder(getContext());


        list = new ArrayList<>();
        Getuniversitydata();

        return view;
    }
    void Getuniversitydata() {

        progressBar.isShowing();
        String registrationURL = config.Base_url +"courseprogrameApiData";


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
                        progressBar.dismiss();

                        JSONArray array = obj.getJSONArray("data");
                        for (int i=0;i<=100;++i)
                        {

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



                         list.add(new module_all_program(coursename,duration,fees,countryname,universityname,
                                 baseurl+logo,intake,OfficalLink,criteria));
                        }
                    } else {
                        Toast.makeText(getContext(), "failed" + obj, Toast.LENGTH_SHORT).show();

                    }
                    RecyclerView recyclerView = getView().findViewById(R.id.courses_recyclerview);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

                    All_program_Adapter adapter = new All_program_Adapter(list,getContext(),1);
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