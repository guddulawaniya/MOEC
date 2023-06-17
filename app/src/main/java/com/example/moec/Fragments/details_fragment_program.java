package com.example.moec.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.moec.R;
import com.google.android.material.chip.Chip;


public class details_fragment_program extends Fragment {




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details_program, container, false);

        LinearLayout websitelinerlayout = view.findViewById(R.id.websitelinerlayout);
        Chip visit = view.findViewById(R.id.visitwebsitebutton);
        visit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                websiteview();
            }
        });
        websitelinerlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                websiteview();
            }
        });
        // Inflate the layout for this fragment
        return view;
    }

    void websiteview()
    {
        Uri uri = Uri.parse("https://www.meridean.org/"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}