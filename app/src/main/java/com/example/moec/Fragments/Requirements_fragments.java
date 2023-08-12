package com.example.moec.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.moec.R;


public class Requirements_fragments extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_requirements_fragments, container, false);
        TextView requirements = view.findViewById(R.id.requirements);
        TextView title = view.findViewById(R.id.title);
        TextView descri_no_found = view.findViewById(R.id.descri_no_found);
        SharedPreferences preferences = getContext().getSharedPreferences("programdetails", Context.MODE_PRIVATE);

        Button preferenceButton =  view.findViewById(R.id.nofoundbutton);
        preferenceButton.setText("Visit");
       preferenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                websiteview(preferences.getString("weblink",null));
            }
        });
        descri_no_found.setText("Please Visit the Website");
        title.setText("No Found Details");


        String req = preferences.getString("criteria","");


        if (req.equals("null"))
        {
            requirements.setText(" ");
        }
        else
        {requirements.setText(req);

        }


        return view;
    }
    void websiteview(String weblink)
    {
        Uri uri = Uri.parse(weblink); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}