package com.example.moec;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.All_program_Adapter;
import com.example.moec.Fragments.All_Programs_fragment;
import com.example.moec.ModulesClass.module_all_program;

import java.util.ArrayList;

public class Favorate_Activity extends AppCompatActivity {


    ArrayList<module_all_program> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorate);


        LinearLayout emptylayout = findViewById(R.id.emptylayout);
        ImageView backbutton = findViewById(R.id.backbutton);
        Button explorebutton = findViewById(R.id.nofoundbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        list = new ArrayList<>();



        RecyclerView recyclerView = findViewById(R.id.courses_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SharedPreferences preferences = getSharedPreferences("favoriteProgram", Context.MODE_PRIVATE);

        String coursename = preferences.getString("coursename",null);
        String duration = preferences.getString("duration",null);
        String countryname = preferences.getString("countryname",null);
        String fees = preferences.getString("fees",null);
        String collegename = preferences.getString("collegename",null);
        boolean likeid = preferences.getBoolean("likeid",false);
        addlistdata(coursename,duration,fees,countryname,collegename);







        if (!list.isEmpty()) {
            emptylayout.setVisibility(View.GONE);
        }

        TextView descri_no_found = findViewById(R.id.descri_no_found);
        TextView cleartext = findViewById(R.id.cleartext);
        cleartext.setVisibility(View.GONE);

        All_program_Adapter adapter = new All_program_Adapter(list,this,2);
        recyclerView.setAdapter(adapter);

        TextView toolbartitle = findViewById(R.id.toolbar_title);
        toolbartitle.setText("Shortlisted Program");
        explorebutton.setText("Explore Programs");
        descri_no_found.setText("The list of shortlisted programs will be displayed on this page");
        explorebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent intent = new  Intent(getApplicationContext(), MainActivity.class);
              intent.putExtra("fmid",1);
              startActivity(intent);
              finish();
            }
        });
    }

    void addlistdata(String coursename,String duration,String fees,String countryname,String collegename)
    {
        list.add(new module_all_program(coursename,duration,fees,countryname,collegename));

    }
}