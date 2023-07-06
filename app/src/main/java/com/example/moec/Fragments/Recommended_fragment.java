package com.example.moec.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.moec.R;
import com.example.moec.program_preference_Activity;


public class Recommended_fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommended_fragment, container, false);

        Button preferencesetbutton = view.findViewById(R.id.nofoundbutton);
        preferencesetbutton.setText("Update Preferences");
        TextView descri_no_found = view.findViewById(R.id.descri_no_found);
        descri_no_found.setText("No program found , Kindly update your Preferences");

        preferencesetbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), program_preference_Activity.class));
            }
        });

        return view;
    }
}