package com.gvtech.serviceathome.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;

import com.gvtech.serviceathome.fragments.AboutFragment;
import com.gvtech.serviceathome.fragments.AvailabilityFragment;
import com.gvtech.serviceathome.fragments.GalleryFragment;
import com.gvtech.serviceathome.fragments.ServicesFragment;

public class MyAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public MyAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                ServicesFragment servicesFragment = new ServicesFragment();
                return servicesFragment;
            case 1:
                AvailabilityFragment availabilityFragment = new AvailabilityFragment();
                return availabilityFragment;
            case 2:
                GalleryFragment galleryFragment = new GalleryFragment();
                return galleryFragment;
            case 3:
                AboutFragment aboutkFragment = new AboutFragment();
                return aboutkFragment;
            default:
                return null;
        }
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}