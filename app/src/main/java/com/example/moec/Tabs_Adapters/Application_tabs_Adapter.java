package com.example.moec.Tabs_Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.moec.Fragments.Application_Active_frame;
import com.example.moec.Fragments.Archived_fragment;

public class Application_tabs_Adapter extends FragmentPagerAdapter {

    public Application_tabs_Adapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Application_Active_frame recommendedFragment = new Application_Active_frame();
                return recommendedFragment;
            case 1:
                Archived_fragment programs_fragment = new Archived_fragment();
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
            title = "Active";
        }
        else if (position == 1)
        {
            title = "Archived";
        }
        return title;
    }
}
