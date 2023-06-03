package com.example.moec;

import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shuhart.stepview.StepView;


public class
insights_fragment extends Fragment {



    StepView stepView;
    int stepcount=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

View view = inflater.inflate(R.layout.fragment_insights_fragment, container, false);

        LinearLayout searchfield = view.findViewById(R.id.searchfield);
        stepView = view.findViewById(R.id.step_view);


        searchfield.setVisibility(View.GONE);
        ImageView favorate = view.findViewById(R.id.favourate_icon_toolbar);
        favorate.setVisibility(View.GONE);
        TextView tooltitle = view.findViewById(R.id.toolbartitle);
        tooltitle.setText("Insights");
        progressbar(stepcount);

        searchfield.setVisibility(View.GONE);

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
                stepcount++;
                if (stepcount>6)
                {

                    stepcount=0;
                }

                progressbar(stepcount);




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
                .selectedCircleColor(ContextCompat.getColor(getContext(), R.color.secondarycolor))
                .selectedCircleRadius(50)
                .selectedStepNumberColor(ContextCompat.getColor(getContext(), R.color.white))
                .doneCircleColor(ContextCompat.getColor(getContext(),R.color.primarycolor))

                .stepsNumber(7)
                .nextStepCircleColor(ContextCompat.getColor(getContext(),R.color.iconcolor))
                .animationDuration(getResources().getInteger(android.R.integer.config_shortAnimTime))
                .stepLineWidth(10)
                .typeface(ResourcesCompat.getFont(getContext(), R.font.poppins))
                // other state methods are equal to the corresponding xml attributes
                .commit();

        stepView.go(stepcount,true);

    }
}