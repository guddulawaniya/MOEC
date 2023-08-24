package com.example.moec.BottomNavigation_Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.example.moec.New_Application;
import com.example.moec.R;
import com.example.moec.Student_timeline_activity;
import com.shuhart.stepview.StepView;


public class insights_fragment extends Fragment {



    StepView stepView;
    int stepcount;
    ImageView profile_icon;
    TextView steptext,steptitle;

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_insights_fragment, container, false);

        stepView = view.findViewById(R.id.step_view);
        profile_icon = view.findViewById(R.id.profile_icon);


        SharedPreferences preferences = getContext().getSharedPreferences("registrationform", MODE_PRIVATE);

        stepcount = preferences.getInt("timeline",0);

        CardView studenttimeline = view.findViewById(R.id.studenttimelinecard);
        studenttimeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Student_timeline_activity.class));
            }
        });

        steptext = view.findViewById(R.id.steptext);
        steptitle = view.findViewById(R.id.steptitle);
        progressbar(stepcount);


        TextView descri_no_found = view.findViewById(R.id.descri_no_found);
        TextView title = view.findViewById(R.id.title);


        title.setText("No Application Found");
        ImageView imageView = view.findViewById(R.id.no_found_image_icon);
        imageView.setImageResource(R.drawable.documents);


        Button explorebutton = view.findViewById(R.id.nofoundbutton);
        explorebutton.setText("Add Application");
        descri_no_found.setText("Woo..\n its time to add application");

        explorebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), New_Application.class));



            }
        });



//        TextView descri_no_found = view.findViewById(R.id.descri_no_found);
//        Button explorebutton = view.findViewById(R.id.nofoundbutton);
//        explorebutton.setText("Add Application");
//        descri_no_found.setText("Woo..\n its time to add application");
//        TextView title = view.findViewById(R.id.title);
//        title.setText("No Application Found");

//        explorebutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        return view;
    }

    void progressbar(int stepcount)
    {
        stepView.getState()
                .animationType(StepView.ANIMATION_ALL)
                .selectedCircleColor(ContextCompat.getColor(getContext(), R.color.select_step_progress_color))
                .selectedCircleRadius(50)
                .textSize(30)
                .nextTextColor(ContextCompat.getColor(getContext(),R.color.white))
                .selectedStepNumberColor(ContextCompat.getColor(getContext(), R.color.white))
                .nextStepLineColor(ContextCompat.getColor(getContext(), R.color.nest_Step_item))
                .selectedTextColor(ContextCompat.getColor(getContext(), R.color.secondarycolor))
                .doneCircleColor(ContextCompat.getColor(getContext(),R.color.done_step_item))
                .stepsNumber(6)

                .nextStepCircleColor(ContextCompat.getColor(getContext(),R.color.nest_Step_item))
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .stepLineWidth(10)
                .typeface(ResourcesCompat.getFont(getContext(), R.font.poppins))
                // other state methods are equal to the corresponding xml attributes
                .commit();

        switch (stepcount)
        {
            case 0: steptext.setText("STEP-1");
                steptitle.setText("Sign Up");
                profile_icon.setImageResource(R.drawable.user_icon);
                break;
            case 1: steptext.setText("STEP-2");
                steptitle.setText("Initial Call");
                profile_icon.setImageResource(R.drawable.user_icon);
                break;
            case 2: steptext.setText("STEP-3");
                steptitle.setText("Documentation");
                profile_icon.setImageResource(R.drawable.documents);
                break;
            case 3:
                steptext.setText("STEP-4");
                steptitle.setText("Course Finalization");
                profile_icon.setImageResource(R.drawable.user_icon);
                break;
            case 4:
                steptext.setText("STEP-5");
                steptitle.setText("Application Process");
                profile_icon.setImageResource(R.drawable.application_icon_svg);
                break;
            case 5:
                steptext.setText("STEP-6");
                steptitle.setText("Visa Process");
                profile_icon.setImageResource(R.drawable.user_icon);
                break;
        }
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("registrationform", MODE_PRIVATE).edit();
        editor.putInt("timeline",stepcount);
        editor.commit();
        stepView.go(stepcount,true);


    }
}