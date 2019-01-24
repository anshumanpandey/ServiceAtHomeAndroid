package com.gvtech.serviceathome.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;

import com.gvtech.serviceathome.fragments.AboutFragment;
import com.gvtech.serviceathome.fragments.AvailabilityFragment;
import com.gvtech.serviceathome.fragments.GalleryFragment;
import com.gvtech.serviceathome.fragments.ServicesFragment;

public class TabAdapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;
    ServicesFragment servicesFragment = new ServicesFragment();
    AvailabilityFragment availabilityFragment = new AvailabilityFragment();
    GalleryFragment galleryFragment = new GalleryFragment();
    AboutFragment aboutkFragment = new AboutFragment();

    public TabAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
    }

    // this is for fragment tabs
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:

                return servicesFragment;
            case 1:

                return availabilityFragment;
            case 2:

                return galleryFragment;
            case 3:

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