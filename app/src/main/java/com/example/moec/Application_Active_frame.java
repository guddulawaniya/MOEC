package com.example.moec;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class Application_Active_frame extends Fragment {

    public Application_Active_frame() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_application__active_frame, container, false);;

        TextView descri_no_found = view.findViewById(R.id.descri_no_found);
        TextView title = view.findViewById(R.id.title);

        title.setText("No Application Found");
        ImageView imageView = view.findViewById(R.id.no_found_image_icon);
        imageView.setImageResource(R.drawable.application_icon_svg);


        Button explorebutton = view.findViewById(R.id.nofoundbutton);
        explorebutton.setText("Add Application");
        descri_no_found.setText("Woo..\n its time to add application");

        explorebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), New_Application.class));


            }
        });


        // Inflate the layout for this fragment
        return view;
    }
}