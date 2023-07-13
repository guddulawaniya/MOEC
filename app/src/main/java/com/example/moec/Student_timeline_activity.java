package com.example.moec;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.baoyachi.stepview.VerticalStepView;
import com.example.moec.Adapters.stepview_Adapter;
import com.example.moec.Adapters.studenttimeline_adatper;
import com.example.moec.ModulesClass.studentline_module;

import java.util.ArrayList;
import java.util.List;


public class Student_timeline_activity extends AppCompatActivity {


    RecyclerView verticalStepView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_timeline);

        TextView title = findViewById(R.id.toolbar_title);
        verticalStepView = findViewById(R.id.step_view);

        ImageView backbutton = findViewById(R.id.backbutton);
        TextView actionbutton = findViewById(R.id.cleartext);
        RecyclerView studentprocessRecyclerview = findViewById(R.id.studentprocessRecyclerview);
        actionbutton.setVisibility(View.GONE);
        title.setText("Student Timeline");
        verticalStepView.setNestedScrollingEnabled(false);

        stepview_Adapter stepview_adapter = new stepview_Adapter(this);
        verticalStepView.setLayoutManager(new LinearLayoutManager(this));
        verticalStepView.setAdapter(stepview_adapter);




//        stepview();
        ArrayList<studentline_module> list = new ArrayList<>();
        list.add(new studentline_module("STEP-1","Sign Up","Completed Date 02-05-2023",R.drawable.user_icon));
        list.add(new studentline_module("STEP-2","Initial Call","Completed Date 10-05-2023",R.drawable.user_icon));
        list.add(new studentline_module("STEP-3","Documentation","Completed Date 15-05-2023",R.drawable.user_icon));
        list.add(new studentline_module("STEP-4","Course Finalization","Completed Date 20-05-2023",R.drawable.user_icon));
        list.add(new studentline_module("STEP-5","Application Process","Completed Date 20-05-2023",R.drawable.user_icon));
        list.add(new studentline_module("STEP-6","Visa Process","Completed Date 22-05-2023",R.drawable.user_icon));

        studenttimeline_adatper adatper = new studenttimeline_adatper(list,this);
        studentprocessRecyclerview.setLayoutManager(new LinearLayoutManager(this));
        studentprocessRecyclerview.setAdapter(adatper);


        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();

            }
        });
    }

//    void stepview()
//    {
//
//
//        List<String> list0 = new ArrayList<>();
//        list0.add("1");
//        list0.add("2");
//        list0.add("3");
//        list0.add("4");
//        list0.add("5");
//        list0.add("6");
//        verticalStepView.setStepsViewIndicatorComplectingPosition(3)
//
//                .reverseDraw(false)
//                .setStepViewTexts(list0)
//                .setLinePaddingProportion(2.4f)
//                .setStepsViewIndicatorCompletedLineColor(ContextCompat.getColor(Student_timeline_activity.this, R.color.done_step_item))
//                .setStepsViewIndicatorCompleteIcon(ContextCompat.getDrawable(Student_timeline_activity.this, R.drawable.done_circle))
//                .setStepsViewIndicatorUnCompletedLineColor(ContextCompat.getColor(Student_timeline_activity.this, R.color.select_step_progress_color))
//                .setStepViewUnComplectedTextColor(ContextCompat.getColor(Student_timeline_activity.this, R.color.select_step_progress_color))
//                .setStepsViewIndicatorDefaultIcon(ContextCompat.getDrawable(Student_timeline_activity.this, R.drawable.done_work_backgroud_circle))
//                .setStepsViewIndicatorAttentionIcon(ContextCompat.getDrawable(Student_timeline_activity.this, R.drawable.selected_step));
//
//    }
}