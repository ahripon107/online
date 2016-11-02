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
import android.widget.TextView;

public class PerDetails extends ArrayAdapter<DetailsAnswer> {
    Activity con;
    ArrayList<DetailsAnswer> allAnswers;
    Typeface banglafont;

    public PerDetails(Context context, ArrayList<DetailsAnswer> objects) {
        super(context, R.layout.perdetails, objects);
        this.con = (Activity) context;
        this.allAnswers = objects;
        banglafont = Typeface.createFromAsset(context.getAssets(),
                "font/solaimanlipi.ttf");

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.perdetails, parent, false);

        }

        TextView ques = (TextView) convertView.findViewById(R.id.tvQuestion);
        TextView myans = (TextView) convertView.findViewById(R.id.tvYourAns);
        TextView corans = (TextView) convertView.findViewById(R.id.tvCorrectAns);
        ques.setTypeface(banglafont);
        myans.setTypeface(banglafont);
        corans.setTypeface(banglafont);
        DetailsAnswer dans = allAnswers.get(position);

        ques.setText("Q: " + dans.getQuestion());
        myans.setText("Your ans: " + dans.getMyAns());
        corans.setText("Correct ans: " + dans.getCorrectAns());
        return convertView;
    }
}
