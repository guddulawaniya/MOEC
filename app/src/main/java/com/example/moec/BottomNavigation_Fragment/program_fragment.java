package com.example.moec.BottomNavigation_Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.SharedElementCallback;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.moec.JavaClass.InternetConnection;
import com.example.moec.R;
import com.example.moec.Tabs_Adapters.Program_tabs_Adapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.transition.platform.MaterialContainerTransform;
import com.google.android.material.transition.platform.MaterialContainerTransformSharedElementCallback;


public class program_fragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        config();
        View view = inflater.inflate(R.layout.fragment_program_fragment, container, false);

            TabLayout tabLayout = view.findViewById(R.id.programtabs);
            ViewPager viewPager = view.findViewById(R.id.programviewpager);

            viewPager.setCurrentItem(1);


            Program_tabs_Adapter adapter = new Program_tabs_Adapter(getChildFragmentManager());
            viewPager.setAdapter(adapter);
            // Setup tab layout
            tabLayout.setupWithViewPager(viewPager);


        return view;
    }
    private void config() {
        setExitSharedElementCallback(new SharedElementCallback(){});
        getActivity().getWindow().setSharedElementsUseOverlay(false);
    }


}