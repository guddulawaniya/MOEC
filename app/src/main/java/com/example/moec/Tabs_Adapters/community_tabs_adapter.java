package com.example.moec.Tabs_Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.moec.Fragments.Followers_fragment;
import com.example.moec.Fragments.Following_fragment;
import com.example.moec.Fragments.all_feeds_fragment;

public class community_tabs_adapter extends FragmentPagerAdapter {
    public community_tabs_adapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                all_feeds_fragment recommendedFragment = new all_feeds_fragment();
                return recommendedFragment;
            case 1:
                Following_fragment programs_fragment = new Following_fragment();
                return programs_fragment;
            case 2:
                Followers_fragment followers_fragment = new Followers_fragment();
                return followers_fragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "All Feeds";
        }
        else if (position == 1)
        {
            title = "Following";
        }
        else if (position == 2)
        {
            title = "Followers";
        }
        return title;
    }
}
