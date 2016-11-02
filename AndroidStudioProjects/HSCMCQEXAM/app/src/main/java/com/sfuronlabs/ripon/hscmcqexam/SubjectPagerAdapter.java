package com.sfuronlabs.ripon.hscmcqexam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Ripon on 6/27/15.
 */
public class SubjectPagerAdapter extends FragmentPagerAdapter {
    public SubjectPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {

        switch (i) {
            case 0:
                // Top Rated fragment activity
                return new ChooseSubjectEng();
            case 1:
                // Games fragment activity
                return new ChooseSubjectVersity();
            case 2:
                // Movies fragment activity
                return new ChooseSubjectMed();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
