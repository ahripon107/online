package com.sfuronlabs.ripon.hscmcqexam;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Ripon on 6/20/15.
 */
public class NoticeDetails extends Activity {
    TextView vername,notititle,notidescription,notidate;
    Typeface banglafont;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noticedetails);
        banglafont = Typeface.createFromAsset(getApplicationContext()
                .getAssets(), "font/solaimanlipi.ttf");

        vername = (TextView) findViewById(R.id.versityname);
        notititle = (TextView) findViewById(R.id.noticetitle);
        notidescription = (TextView) findViewById(R.id.noticedetails);
        notidate = (TextView) findViewById(R.id.noticedate);
        Intent i = getIntent();
        String versityname=i.getExtras().getString("versityname");
        String noticetitle = i.getExtras().getString("noticetitle");
        String noticedetails = i.getExtras().getString("noticedetails");
        String noticedate = i.getExtras().getString("noticedate");
        vername.setTypeface(banglafont);
        notititle.setTypeface(banglafont);
        notidescription.setTypeface(banglafont);
        notidate.setTypeface(banglafont);
        vername.setTextColor(Color.MAGENTA);
        notititle.setTextColor(Color.RED);
        notidate.setTextColor(Color.BLUE);
        vername.setText(versityname);
        notititle.setText(noticetitle);
        notidescription.setText(noticedetails);
        notidate.setText("তারিখ ঃ"+noticedate);
    }
}
