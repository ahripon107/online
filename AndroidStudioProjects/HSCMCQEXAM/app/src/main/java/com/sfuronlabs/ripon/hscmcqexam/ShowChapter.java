package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ShowChapter extends ArrayAdapter<String> {
    Activity con;
    String[] chapters;
    Typeface banglafont;

    public ShowChapter(Context context, int resource, String[] objects) {
        super(context, resource, objects);
        this.con = (Activity) context;
        this.chapters = objects;
        banglafont = Typeface.createFromAsset(context.getAssets(),
                "font/solaimanlipi.ttf");
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.chapterlayout, parent, false);
        }
        TextView chapname = (TextView) convertView.findViewById(R.id.tvChapName);
        chapname.setTypeface(banglafont);
        chapname.setText(chapters[position]);
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.chapterlayout, parent, false);
        }
        TextView chapname = (TextView) convertView.findViewById(R.id.tvChapName);
        chapname.setTextColor(Color.RED);
        chapname.setTypeface(banglafont);
        chapname.setText(chapters[position]);
        return convertView;
    }
}
