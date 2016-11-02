package com.sfuronlabs.ripon.hscmcqexam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Ripon on 6/20/15.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter{
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                // Top Rated fragment activity
                return new EngineeringFragment();
            case 1:
                // Games fragment activity
                return new VersityFragment();
            case 2:
                // Movies fragment activity
                return new MedicalFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
