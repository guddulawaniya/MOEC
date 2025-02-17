package com.example.moec.Tabs_Adapters;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.moec.Fragments.All_Programs_fragment;
import com.example.moec.Fragments.Recommended_fragment;

public class Program_tabs_Adapter extends FragmentPagerAdapter {




    public Program_tabs_Adapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Recommended_fragment recommendedFragment = new Recommended_fragment();
                return recommendedFragment;
            case 1:
                All_Programs_fragment programs_fragment = new All_Programs_fragment();
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
            title = "Recommended";
        }
        else if (position == 1)
        {
            title = "All Programs";
        }
        return title;
    }
}
