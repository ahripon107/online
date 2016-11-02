package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PreviousYearAdapter extends ArrayAdapter<Question> {
    Activity con;
    ArrayList<Question> q;
    int pos;
    Typeface banglafont;

    public PreviousYearAdapter(Context context,
                               ArrayList<Question> objects) {
        super(context, R.layout.previousquestion, objects);
        this.con = (Activity) context;
        this.q = objects;
        banglafont = Typeface.createFromAsset(context.getAssets(),
                "font/solaimanlipi.ttf");

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // View v = null;
        pos = position;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.previousquestion, parent, false);

        }


        //rgroup.clearCheck();
        TextView quesno = (TextView) convertView.findViewById(R.id.tvQuesNo);
        TextView quest = (TextView) convertView.findViewById(R.id.tvQuestion);
        TextView opt1 = (TextView) convertView.findViewById(R.id.tvOption1);
        TextView opt2 = (TextView) convertView.findViewById(R.id.tvOption2);
        TextView opt3 = (TextView) convertView.findViewById(R.id.tvOption3);
        TextView opt4 = (TextView) convertView.findViewById(R.id.tvOption4);
        TextView corr = (TextView) convertView.findViewById(R.id.tvCorrect);
        quest.setTypeface(banglafont);
        opt1.setTypeface(banglafont);
        opt2.setTypeface(banglafont);
        opt3.setTypeface(banglafont);
        opt4.setTypeface(banglafont);
        corr.setTypeface(banglafont);
        corr.setTextColor(Color.BLUE);

        Question q1 = q.get(position);
        quesno.setText("Ques no: " + q1.getId());
        quest.setText(q1.getQues());
        opt1.setText("A: " + q1.getOption1());
        opt2.setText("B: " + q1.getOption2());
        opt3.setText("C: " + q1.getOption3());
        opt4.setText("D: " + q1.getOption4());
        corr.setText("Correct ans: " + q1.getCorrect());


        return convertView;
        // return v;
    }


}
