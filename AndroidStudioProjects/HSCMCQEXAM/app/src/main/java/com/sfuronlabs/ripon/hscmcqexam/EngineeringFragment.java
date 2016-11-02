package com.sfuronlabs.ripon.hscmcqexam;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Ripon on 6/20/15.
 */
public class EngineeringFragment extends Fragment {
    Typeface banglafont;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragmentengineering, container, false);

        StringBuilder builder = new StringBuilder();
        builder.append("বুয়েটে পদার্থবিজ্ঞান, রসায়ন ও গণিত এই তিনটি বিষয় এর উপর পরীক্ষা হবে।  বুয়েটে প্রত্যেক বিষয় এর উপর ২০ টি করে  লিখিত প্রশ্ন থাকবে। প্রত্যেক প্রশ্নের মান ১০," +
                "মোট নম্বর ৬০০ " + " এবং পরীক্ষার মোট সময় ৩ ঘন্টা।\n\n");
        builder.append("চুয়েটে পদার্থবিজ্ঞান , রসায়ন , গণিত ও ইংরেজী এই চারটি বিষয় এর উপর পরীক্ষা হবে। পদার্থ, রসায়ন ও গণিতে ৩০ টি করে মোট ৯০ টি এবং ইংরেজীতে ১০ টি, মোট ১০০ টি MCQ প্রশ্ন থাকবে। প্রত্যেক প্রশ্নের মান ৫, মোট নম্বর ৫০০"
                + "। পরীক্ষার সময় ২ ঘন্টা ৩০ মিনিট।\n\n");
        builder.append("কুয়েটে পদার্থবিজ্ঞান , রসায়ন , গণিত ও ইংরেজী এই চারটি বিষয় এর উপর পরীক্ষা হবে। পদার্থ, রসায়ন , গণিত  এবং ইংরেজীতে ২৫ টি করে  মোট ১০০ টি MCQ প্রশ্ন থাকবে।"
                + "পদার্থ, রসায়ন ও গণিতের ৭৫ টি প্রশ্নের প্রত্যেকটির মান ৬ করে এবং ইংরেজীর ২৫ টি প্রশ্নের প্রত্যেকটির মান ২ করে। মোট ৬০০ নম্বরের পরীক্ষা। সময় ২ ঘন্টা ৩০ মিনিট।\n\n ");
        builder.append("রুয়েটে  পদার্থবিজ্ঞান , রসায়ন , গণিত ও ইংরেজী এই চারটি বিষয় এর উপর পরীক্ষা হবে।   মোট ১০০ টি MCQ প্রশ্ন থাকবে।  প্রত্যেকটি প্রশ্নের  মান ৭ । মোট ৭০০ নম্বরের পরীক্ষা। সময় ২ ঘন্টা ৩০ মিনিট। ");
        TextView eng = (TextView) rootView.findViewById(R.id.tvEngineering);
        eng.setTypeface(Syllabus.banglafont);
        eng.setText(builder.toString());
        return rootView;
    }
}
