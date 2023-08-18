package com.example.moec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.SharedElementCallback;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.All_program_Adapter;
import com.example.moec.JavaClass.Load_favorate_data;
import com.example.moec.JavaClass.favorategetdataAPI;
import com.example.moec.ModulesClass.module_all_program;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;

import java.util.ArrayList;

public class Favorate_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        config();
        setContentView(R.layout.activity_favorate);

        LinearLayout emptylayout = findViewById(R.id.linearLayout21);
        ImageView backbutton = findViewById(R.id.backbutton);
        Button explorebutton = findViewById(R.id.nofoundbutton);
        ProgressBar  progressBar = findViewById(R.id.progressBar);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


        ArrayList<module_all_program>  list = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.courses_recyclerview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        new Load_favorate_data(progressBar,list,this,recyclerView,emptylayout);


        TextView descri_no_found = findViewById(R.id.descri_no_found);
        TextView cleartext = findViewById(R.id.cleartext);
        cleartext.setVisibility(View.GONE);


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
        setExitSharedElementCallback(new SharedElementCallback(){});
        getWindow().setSharedElementsUseOverlay(false);
    }

}