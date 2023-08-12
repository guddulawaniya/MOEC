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
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moec.Adapters.All_program_Adapter;
import com.example.moec.ModulesClass.module_all_program;
import com.example.moec.R;
import com.example.moec.program_preference_Activity;

import java.util.ArrayList;


public class Recommended_fragment extends Fragment {


    ArrayList<module_all_program> programArrayList;

    int duration=48;
    int fees=48000;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommended_fragment, container, false);

        Button preferencesetbutton = view.findViewById(R.id.nofoundbutton);
        LinearLayout notfoundaLayout = view.findViewById(R.id.notfoundaLayout);
        preferencesetbutton.setText("Update Preferences");
        TextView descri_no_found = view.findViewById(R.id.descri_no_found);
        descri_no_found.setText("No program found , Kindly update your Preferences");

        programArrayList = new ArrayList<>();
        RecyclerView recommandRecyclerview = view.findViewById(R.id.recommendedRecyclerview);

        recommandRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        All_program_Adapter program_adapter = new All_program_Adapter(programArrayList,getContext(),1);
        recommandRecyclerview.setAdapter(program_adapter);

//        programArrayList.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","Northumbria Univerisity"));
//        programArrayList.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","University of Worcester, UK"));
//        programArrayList.add(new module_all_program("Geography BA (Hons) with Pacement",duration+" Months","GBP £"+fees,"United kingdom","Birmingham City University, UK"));



        SharedPreferences preferences = getActivity().getSharedPreferences("registrationform",MODE_PRIVATE);

        String preferenceCountry = preferences.getString("countryname",null);
        String interest = preferences.getString("interest",null);
        String education = preferences.getString("qualification",null);
        String examname = preferences.getString("examname",null);



        if (preferenceCountry!=null && interest!=null && education!=null && examname!=null)
        {

            notfoundaLayout.setVisibility(View.GONE);
            recommandRecyclerview.setVisibility(View.VISIBLE);

        }
        else
        {
            notfoundaLayout.setVisibility(View.VISIBLE);
            recommandRecyclerview.setVisibility(View.GONE);
        }


        preferencesetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), program_preference_Activity.class));
            }
        });

        return view;
    }
}