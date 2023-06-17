package com.example.moec.Tabs_Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.moec.Fragments.Requirements_fragments;
import com.example.moec.Fragments.details_fragment_program;

public class program_details_tab_Adapter extends FragmentPagerAdapter {

    public program_details_tab_Adapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                details_fragment_program recommendedFragment = new details_fragment_program();
                return recommendedFragment;
            case 1:
                Requirements_fragments programs_fragment = new Requirements_fragments();
                return programs_fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "Details";
        }
        else if (position == 1)
        {
            title = "Requirements";
        }
        return title;
    }
}
