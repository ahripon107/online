package com.sfuronlabs.ripon.hscmcqexam;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Ripon on 6/20/15.
 */
public class VersityFragment extends Fragment {
    Typeface banglafont;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentversity, container, false);

        StringBuilder builder = new StringBuilder();
        builder.append("ঢাকা বিশ্ববিদ্যালয়ে ১২০ নম্বরের ভর্তি পরীক্ষা অনুষ্" +
                "ঠিত হয়। পদার্থবিজ্ঞান, রসায়নবিজ্ঞান এবং গণিত অবশ্যই উত্তর করতে হয়। জীববিজ্ঞান" +
                ", বাংলা অথবা ইংরেজীর মধ্য থেকে যেকোন একটি বিষয়ের উত্তর দিতে হয়। প্রত্যেক বিষয়ে ৩০ টি করে" +
                " প্রশ্ন থাকে। প্রত্যেক প্রশ্নের মান ১। পরীক্ষার সময় ১ ঘণ্টা ৩০ মিনিট। ");
        TextView ver = (TextView) rootView.findViewById(R.id.tvVersity);
        ver.setTypeface(Syllabus.banglafont);
        ver.setText(builder.toString());
        return rootView;
    }
}
