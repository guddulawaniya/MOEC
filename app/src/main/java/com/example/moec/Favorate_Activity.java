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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.All_program_Adapter;
import com.example.moec.ModulesClass.module_all_program;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import java.util.ArrayList;

public class Favorate_Activity extends AppCompatActivity {


    ArrayList<module_all_program> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        config();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorate);


        LinearLayout emptylayout = findViewById(R.id.linearLayout21);
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
//        list.add(new module_all_program(coursename,duration,fees,countryname,collegename));



        if (list.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptylayout.setVisibility(View.VISIBLE);

        }
        else
        { recyclerView.setVisibility(View.VISIBLE);
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


    private void config() {
        findViewById(android.R.id.content).setTransitionName("search");

        setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        MaterialContainerTransform transform = new MaterialContainerTransform();
        transform.addTarget(android.R.id.content);
        transform.setDuration(500);

        getWindow().setSharedElementEnterTransition(transform);
        getWindow().setSharedElementReturnTransition(transform);



    }
}