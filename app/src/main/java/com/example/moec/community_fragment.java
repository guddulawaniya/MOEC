package com.example.moec;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.SliderView;

public class community_fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_community_fragment, container, false);

        SliderView sliderView = view.findViewById(R.id.slider);
        SliderAdapter adapterslider = new SliderAdapter();
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapterslider);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        ImageView refine = view.findViewById(R.id.refine_icon);
        ImageView like  = view.findViewById(R.id.favourate_icon_toolbar);

        FloatingActionButton floatingActionButton = view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), post_activity.class));
            }
        });

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