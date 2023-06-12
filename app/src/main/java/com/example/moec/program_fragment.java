package com.example.moec;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;


public class program_fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view  =        inflater.inflate(R.layout.fragment_program_fragment, container, false);
        TabLayout tabLayout = view.findViewById(R.id.programtabs);
        ViewPager viewPager = view.findViewById(R.id.programviewpager);




        Program_tabs_Adapter adapter = new Program_tabs_Adapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        // Setup tab layout
        tabLayout.setupWithViewPager(viewPager);




        return view;
    }

}