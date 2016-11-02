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
public class MedicalFragment  extends Fragment{
    Typeface banglafont;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentmedical, container, false);

        StringBuilder builder = new StringBuilder();
        builder.append("মেডিকেল ভর্তি পরীক্ষায় ১০০ নম্বরের ভর্তি পরীক্ষা অনুষ্ঠি" +
                "ত হয়। পরীক্ষা হয় পদার্থবিজ্ঞান, রসায়নবিজ্ঞান, জীববিজ্ঞান, ইংরেজী এবং" +
                " সাধারণ জ্ঞান এর উপর। নম্বর বণ্টন ঃ পদার্থবিজ্ঞান – ২০, রসায়নবিজ্ঞান – ২৫, জীববিজ্ঞান – ৩০, ইংরেজী – ১৫, সাধার" +
                "ণ জ্ঞান – ১০। পরীক্ষার সময় ১ ঘন্টা। ");
        TextView med = (TextView) rootView.findViewById(R.id.tvMedical);
        med.setTypeface(Syllabus.banglafont);
        med.setText(builder.toString());
        return rootView;
    }
}
