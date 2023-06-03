package com.example.moec;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class community_fragment extends Fragment {

    public community_fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_fragment, container, false);


        ImageView refine = view.findViewById(R.id.refine_icon);
        ImageView like  = view.findViewById(R.id.favourate_icon_toolbar);

        ViewPager viewPager = view.findViewById(R.id.programviewpager);
        TabLayout tabLayout = view.findViewById(R.id.programtabs);

        like.setVisibility(View.GONE);
        refine.setVisibility(View.GONE);
        TextView tooltitle = view.findViewById(R.id.toolbartitle);
        tooltitle.setText("Community");


        community_tabs_adapter adapter = new community_tabs_adapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        // Setup tab layout
        tabLayout.setupWithViewPager(viewPager);

        return  view;
    }
}