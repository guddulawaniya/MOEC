package com.example.moec.Fragments;

import static android.content.Context.MODE_PRIVATE;
import static com.example.moec.R.drawable.baseline_done_icon_24;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.moec.R;
import com.example.moec.webviewActivity;
import com.google.android.material.chip.Chip;


public class details_fragment_program extends Fragment {



    String  weblink;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_details_program, container, false);

        LinearLayout websitelinerlayout = view.findViewById(R.id.websitelinerlayout);
        Chip visit = view.findViewById(R.id.visitwebsitebutton);
//        TextView applicationfees = view.findViewById(R.id.applicationfees);
        TextView tuitionfee = view.findViewById(R.id.tution_fee);




        SharedPreferences preferences = getContext().getSharedPreferences("programdetails",MODE_PRIVATE);
        TextView setlink = view.findViewById(R.id.linkset);
        weblink =  preferences.getString("weblink",null);

        String fees = preferences.getString("fees",null);
        String intaketext = preferences.getString("intake",null);



        if (weblink.equals("null") || weblink.isEmpty())
        {
            websitelinerlayout.setVisibility(View.GONE);
        }
        else {
            websitelinerlayout.setVisibility(View.VISIBLE);
            setlink.setText(weblink);
        }





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
        Intent intent = new Intent(getContext(), webviewActivity.class);
        intent.putExtra("url",weblink);
        startActivity(intent);
    }
}