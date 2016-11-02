package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;


public class ShowQuestion extends ArrayAdapter<Question> {
    Activity con;
    ArrayList<Question> q;
    int pos;
    Typeface banglafont;

    public ShowQuestion(Context context, int resource,
                        ArrayList<Question> objects) {
        super(context, resource, objects);
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
                    R.layout.ques_layout, parent, false);

        }

        final RadioGroup rgroup = (RadioGroup) convertView
                .findViewById(R.id.OptionsGroup);
        //rgroup.clearCheck();
        TextView quest = (TextView) convertView.findViewById(R.id.tvQuestion);
        TextView quesno = (TextView) convertView.findViewById(R.id.tvQuesNo);

        final RadioButton opt1 = (RadioButton) convertView.findViewById(R.id.option1);
        final RadioButton opt2 = (RadioButton) convertView.findViewById(R.id.option2);
        final RadioButton opt3 = (RadioButton) convertView.findViewById(R.id.option3);
        final RadioButton opt4 = (RadioButton) convertView.findViewById(R.id.option4);

        quest.setTypeface(banglafont);
        opt1.setTypeface(banglafont);
        opt2.setTypeface(banglafont);
        opt3.setTypeface(banglafont);
        opt4.setTypeface(banglafont);
        Question qu = q.get(position);
        quesno.setText(qu.getId() + "");
        quest.setText(qu.getQues());
        opt1.setText(qu.getOption1());
        opt2.setText(qu.getOption2());
        opt3.setText(qu.getOption3());
        opt4.setText(qu.getOption4());


        return convertView;
        // return v;
    }
}
