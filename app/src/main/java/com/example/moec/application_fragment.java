package com.example.moec;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class application_fragment extends Fragment {

    public application_fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application_fragment, container, false);

        LinearLayout searchfield = view.findViewById(R.id.searchfield);

        searchfield.setVisibility(View.GONE);
        ImageView favorate = view.findViewById(R.id.favourate_icon_toolbar);
        favorate.setVisibility(View.GONE);
        TextView tooltitle = view.findViewById(R.id.toolbartitle);
        tooltitle.setText("Application");


        TabLayout tabLayout = view.findViewById(R.id.programtabs);
        ViewPager viewPager = view.findViewById(R.id.programviewpager);

        Application_tabs_Adapter adapter = new Application_tabs_Adapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        // Setup tab layout
        tabLayout.setupWithViewPager(viewPager);


        return view;
    }
}