package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class OptionsListStyle extends ArrayAdapter<String> {
    private final Activity context;
    private final Integer[] imageId;
    Typeface tf;
    private final String[] web;

    public OptionsListStyle(Activity paramActivity,
                            String[] paramArrayOfString, Integer[] paramArrayOfInteger,
                            String paramString) {
        super(paramActivity, R.layout.options_single, paramArrayOfString);
        this.context = paramActivity;
        this.web = paramArrayOfString;
        this.imageId = paramArrayOfInteger;
        this.tf = Typeface.createFromAsset(paramActivity.getAssets(),
                paramString);
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
        View localView = this.context.getLayoutInflater().inflate(R.layout.options_single, null, true);
        TextView localTextView = (TextView) localView.findViewById(R.id.txt);
        localTextView.setTypeface(this.tf, 1);
        ImageView localImageView = (ImageView) localView.findViewById(R.id.img);
        localTextView.setText(this.web[paramInt]);
        localTextView.setTextColor(Color.BLUE);
        localImageView.setImageResource(this.imageId[paramInt].intValue());
        return localView;
    }
}
