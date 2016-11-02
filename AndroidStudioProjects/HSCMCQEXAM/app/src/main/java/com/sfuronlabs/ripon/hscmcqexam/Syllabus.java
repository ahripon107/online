package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Syllabus extends FragmentActivity implements ActionBar.TabListener{
    //ExpandableListView expListView;
    //ExpandableListAdapter listAdapter;
    //HashMap<String, List<String>> listDataChild;
    //List<String> listDataHeader;
    public static Typeface banglafont;

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "ENGINEERING", "VERSITY", "MEDICAL" };
/*
    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<>();
        listDataHeader.add("ENGINEERING");
        listDataHeader.add("UNIVERSITY");
        listDataHeader.add("MEDICAL");
        ArrayList localArrayList1 = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append("বুয়েটে পদার্থবিজ্ঞান, রসায়ন ও গণিত এই তিনটি বিষয় এর উপর পরীক্ষা হবে।  বুয়েটে প্রত্যেক বিষয় এর উপর ২০ টি করে  লিখিত প্রশ্ন থাকবে। প্রত্যেক প্রশ্নের মান ১০," +
                "মোট নম্বর ৬০০ " + " এবং পরীক্ষার মোট সময় ৩ ঘন্টা।\n\n");
        builder.append("চুয়েটে পদার্থবিজ্ঞান , রসায়ন , গণিত ও ইংরেজী এই চারটি বিষয় এর উপর পরীক্ষা হবে। পদার্থ, রসায়ন ও গণিতে ৩০ টি করে মোট ৯০ টি এবং ইংরেজীতে ১০ টি, মোট ১০০ টি MCQ প্রশ্ন থাকবে। প্রত্যেক প্রশ্নের মান ৫, মোট নম্বর ৫০০"
                + "। পরীক্ষার সময় ২ ঘন্টা ৩০ মিনিট।\n\n");
        builder.append("কুয়েটে পদার্থবিজ্ঞান , রসায়ন , গণিত ও ইংরেজী এই চারটি বিষয় এর উপর পরীক্ষা হবে। পদার্থ, রসায়ন , গণিত  এবং ইংরেজীতে ২৫ টি করে  মোট ১০০ টি MCQ প্রশ্ন থাকবে।"
                + "পদার্থ, রসায়ন ও গণিতের ৭৫ টি প্রশ্নের প্রত্যেকটির মান ৬ করে এবং ইংরেজীর ২৫ টি প্রশ্নের প্রত্যেকটির মান ২ করে। মোট ৬০০ নম্বরের পরীক্ষা। সময় ২ ঘন্টা ৩০ মিনিট।\n\n ");
        builder.append("রুয়েটে  পদার্থবিজ্ঞান , রসায়ন , গণিত ও ইংরেজী এই চারটি বিষয় এর উপর পরীক্ষা হবে।   মোট ১০০ টি MCQ প্রশ্ন থাকবে।  প্রত্যেকটি প্রশ্নের  মান ৭ । মোট ৭০০ নম্বরের পরীক্ষা। সময় ২ ঘন্টা ৩০ মিনিট। ");

        localArrayList1.add(builder.toString());

        ArrayList localArrayList2 = new ArrayList<>();
        localArrayList2.add("ঢাকা বিশ্ববিদ্যালয়ে ১২০ নম্বরের ভর্তি পরীক্ষা অনুষ্ঠিত হয়। পদার্থবিজ্ঞান, রসায়নবিজ্ঞান এবং গণিত অবশ্যই উত্তর করতে হয়। জীববিজ্ঞান, বাংলা অথবা ইংরেজীর মধ্য থেকে যেকোন একটি বিষয়ের উত্তর দিতে হয়। প্রত্যেক বিষয়ে ৩০ টি করে প্রশ্ন থাকে। প্রত্যেক প্রশ্নের মান ১। পরীক্ষার সময় ১ ঘণ্টা ৩০ মিনিট। ");
        ArrayList localArrayList3 = new ArrayList<>();
        localArrayList3.add("মেডিকেল ভর্তি পরীক্ষায় ১০০ নম্বরের ভর্তি পরীক্ষা অনুষ্ঠিত হয়। পরীক্ষা হয় পদার্থবিজ্ঞান, রসায়নবিজ্ঞান, জীববিজ্ঞান, ইংরেজী এবং সাধারণ জ্ঞান এর উপর। নম্বর বণ্টন ঃ পদার্থবিজ্ঞান – ২০, রসায়নবিজ্ঞান – ২৫, জীববিজ্ঞান – ৩০, ইংরেজী – ১৫, সাধারণ জ্ঞান – ১০। পরীক্ষার সময় ১ ঘন্টা। ");
        listDataChild.put((String) this.listDataHeader.get(0), localArrayList1);
        listDataChild.put((String) this.listDataHeader.get(1), localArrayList2);
        listDataChild.put((String) this.listDataHeader.get(2), localArrayList3);

    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.syllabus);
        banglafont = Typeface.createFromAsset(this.getAssets(),
                "font/solaimanlipi.ttf");
        setTitle("SYLLABUS");
        /*expListView = (ExpandableListView) findViewById(R.id.expandableListView1);

        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild, banglafont);
        //View localView = getLayoutInflater().inflate(R.layout.list_item, null);
        expListView.setAdapter(listAdapter);*/

        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mAdapter);
        //actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });

    }


    @Override
    public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {

    }
}
