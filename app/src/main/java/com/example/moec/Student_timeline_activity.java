package com.example.moec;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class Student_timeline_activity extends AppCompatActivity {





    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_timeline);
        getSupportActionBar().hide();
        TextView title = findViewById(R.id.toolbar_title);
        ImageView backbutton = findViewById(R.id.backbutton);
        TextView actionbutton = findViewById(R.id.cleartext);
        actionbutton.setVisibility(View.GONE);


        title.setText("Student Timeline");

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