package com.example.moec.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.JavaClass.config;
import com.example.moec.JavaClass.getCourse_All_dataa_API;
import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.R;
import com.example.moec.program_preference_Activity;

import java.util.ArrayList;


public class Recommended_fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommended_fragment, container, false);

        Button preferencesetbutton = view.findViewById(R.id.nofoundbutton);
        LinearLayout notfoundaLayout = view.findViewById(R.id.notfoundaLayout);
        TextView descri_no_found = view.findViewById(R.id.descri_no_found);
        ProgressBar progressBar = view.findViewById(R.id.progressBar);
        RecyclerView recyclerView = view.findViewById(R.id.recommendedRecyclerview);


        // array instances and declares array list
        ArrayList<module_all_program> list = new ArrayList<>();

        if (list.isEmpty()) {
            notfoundaLayout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        } else {
            notfoundaLayout.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

        preferencesetbutton.setText("Update Preferences");
        descri_no_found.setText("No program found , Kindly update your Preferences");


        // get country in share preference
        SharedPreferences preferences = getActivity().getSharedPreferences("registrationform", MODE_PRIVATE);
        String preferenceCountry = preferences.getString("pre_country", null);


        // getdata from api and load data
        new getCourse_All_dataa_API(progressBar, list, getContext(), recyclerView, config.Base_url + "courseApiDatawithcountry?" + "countryname=" + preferenceCountry);


        // setpreference button and updates also

        preferencesetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), program_preference_Activity.class));
            }
        });

        return view;
    }
}