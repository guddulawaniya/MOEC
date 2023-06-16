package com.example.moec;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.studenttimeline_adatper;
import com.example.moec.ModulesClass.studentline_module;

import java.util.ArrayList;
import java.util.List;

public class Student_timeline_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_timeline);

        TextView title = findViewById(R.id.toolbar_title);
        ImageView backbutton = findViewById(R.id.backbutton);
        TextView actionbutton = findViewById(R.id.cleartext);
        RecyclerView studentprocessRecyclerview = findViewById(R.id.studentprocessRecyclerview);
        actionbutton.setVisibility(View.GONE);
        title.setText("Student Timeline");
        ArrayList<studentline_module> list = new ArrayList<>();
        list.add(new studentline_module("1","Sign Up","02-05-2023",R.drawable.user_icon));
        list.add(new studentline_module("2","Initial Call","02-05-2023",R.drawable.user_icon));
        list.add(new studentline_module("3","Documenttation","02-05-2023",R.drawable.user_icon));
        list.add(new studentline_module("4","Course Finalization","02-05-2023",R.drawable.user_icon));
        list.add(new studentline_module("5","Application Process","02-05-2023",R.drawable.user_icon));
        list.add(new studentline_module("6","Visa Process","02-05-2023",R.drawable.user_icon));

        studenttimeline_adatper adatper = new studenttimeline_adatper(list);
        studentprocessRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        studentprocessRecyclerview.setAdapter(adatper);


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }

    private List<String> getlist(){
        List<String> list = new ArrayList<>();
        list.add("Sign Up");
        list.add("Initial call");
        list.add("Documentation");
        list.add("Course Finalization");
        list.add("Application Process");
        list.add("Visa Process");


        return  list;

    }
}