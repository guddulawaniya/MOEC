package com.example.moec.BottomNavigation_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.moec.R;
import com.example.moec.Tabs_Adapters.Application_tabs_Adapter;
import com.google.android.material.tabs.TabLayout;


public class application_fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_application_fragment, container, false);






        TabLayout tabLayout = view.findViewById(R.id.programtabs);
        ViewPager viewPager = view.findViewById(R.id.programviewpager);

        Application_tabs_Adapter adapter = new Application_tabs_Adapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        // Setup tab layout
        tabLayout.setupWithViewPager(viewPager);


        return view;
    }
}