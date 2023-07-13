package com.example.moec.BottomNavigation_Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.moec.Adapters.SliderAdapter;
import com.example.moec.New_Application;
import com.example.moec.R;
import com.example.moec.Tabs_Adapters.community_tabs_adapter;
import com.example.moec.post_activity;
import com.google.android.material.tabs.TabLayout;
import com.smarteist.autoimageslider.SliderView;

public class community_fragment extends Fragment {


    int[] images = {R.drawable.slider1,R.drawable.slider2,R.drawable.slider3};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        config();
        View view = inflater.inflate(R.layout.fragment_community_fragment, container, false);

        SliderView sliderView = view.findViewById(R.id.slider);
        SliderAdapter adapterslider = new SliderAdapter(images);
        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
        sliderView.setSliderAdapter(adapterslider);
        sliderView.setScrollTimeInSec(3);
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();



        LinearLayout floatingActionButton = view.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), post_activity.class);
                Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(getActivity(),floatingActionButton,"fab").toBundle();
                startActivity(intent,bundle);
            }
        });

        ViewPager viewPager = view.findViewById(R.id.programviewpager);
        TabLayout tabLayout = view.findViewById(R.id.programtabs);



        community_tabs_adapter adapter = new community_tabs_adapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        // Setup tab layout
        tabLayout.setupWithViewPager(viewPager);

        return  view;
    }
    private void config() {
        setExitSharedElementCallback(new SharedElementCallback(){});
        getActivity().getWindow().setSharedElementsUseOverlay(false);
    }
}