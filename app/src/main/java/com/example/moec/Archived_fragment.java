package com.example.moec;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Archived_fragment extends Fragment {

    public Archived_fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_archived_fragment, container, false);

        TextView descri_no_found = view.findViewById(R.id.descri_no_found);
        ImageView imageView = view.findViewById(R.id.no_found_image_icon);
        imageView.setImageResource(R.drawable.documents);

        TextView title = view.findViewById(R.id.title);

        title.setText("No Archived Application");
        descri_no_found.setVisibility(View.GONE);

        Button explorebutton = view.findViewById(R.id.nofoundbutton);
        explorebutton.setVisibility(View.GONE);
        return view;
    }
}