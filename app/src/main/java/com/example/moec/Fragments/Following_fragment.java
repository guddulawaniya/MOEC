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


public class Following_fragment extends Fragment {


    public Following_fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_following_fragment, container, false);

        Button nofoundbutton = view.findViewById(R.id.nofoundbutton);
        nofoundbutton.setVisibility(View.GONE);

        ImageView imageicon = view.findViewById(R.id.no_found_image_icon);
        imageicon.setImageResource(R.drawable.user_icon);

        TextView title = view.findViewById(R.id.title);
        TextView desc = view.findViewById(R.id.descri_no_found);
        title.setText("Followers...");
        desc.setText("Coming Soon, Stay Tuned");
        return view ;
    }
}