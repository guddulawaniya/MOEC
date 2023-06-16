package com.example.moec.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moec.R;


public class Followers_fragment extends Fragment {
    public Followers_fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_followers_fragment, container, false);

        ImageView imageicon = view.findViewById(R.id.no_found_image_icon);
        Button nofoundbutton = view.findViewById(R.id.nofoundbutton);
        imageicon.setImageResource(R.drawable.user_icon);

        nofoundbutton.setVisibility(View.GONE);

        TextView title = view.findViewById(R.id.title);
        TextView desc = view.findViewById(R.id.descri_no_found);
        title.setText("Followering...");
        desc.setText("Coming Soon, Stay Tuned");




        return view;
    }
}