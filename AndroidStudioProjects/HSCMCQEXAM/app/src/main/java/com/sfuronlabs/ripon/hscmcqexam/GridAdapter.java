package com.sfuronlabs.ripon.hscmcqexam;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ripon on 6/27/15.
 */
public class GridAdapter extends BaseAdapter {
    private Activity context;
    private Integer[] imageId;
    Typeface tf;
    private String[] web;
    public GridAdapter(Activity paramActivity,String[] paramArrayOfString,
                       Integer[] paramArrayOfInteger, String paramString)
    {
        //super(paramActivity, R.layout.griditem, paramArrayOfString);
        this.context = paramActivity;
        this.web = paramArrayOfString;
        this.imageId = paramArrayOfInteger;

        this.tf = Typeface.createFromAsset(paramActivity.getAssets(),
                paramString);
    }

    @Override
    public int getCount() {
        return web.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View localView = convertView;
        if (localView == null)
        {
            localView = this.context.getLayoutInflater().inflate(R.layout.griditem, null, true);

        }
        TextView localTextView = (TextView) localView.findViewById(R.id.textdescription);
        localTextView.setTypeface(this.tf, 1);
        ImageView localImageView = (ImageView) localView.findViewById(R.id.picture);
        localTextView.setText(this.web[position]);
        localTextView.setTextColor(Color.GREEN);
        localImageView.setImageResource(this.imageId[position].intValue());

        return localView;
    }
}
