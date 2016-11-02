package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

public class PreviousYearQues extends Activity {
    Button Read;
    Spinner subject;
    Typeface banglafont;
    String subjectname = "";
    String sel;
    String[] NoQues = {"10", "20", "50"};
    String[] ENGINEERING = {"BUET 14-15", "BUET 13-14", "KUET 14-15",
            "KUET 13-14", "RUET 14-15", "RUET 13-14", "CUET 14-15",
            "CUET 13-14"};
    String[] UNIVERSITY = {"DU 14-15", "RU 14-15", "JU 14-15", "SUST 14-15",
            "CU 14-15"};
    String[] MEDICAL = {"MEDICAL 14-15", "MEDICAL 13-14", "MEDICAL 12-13",
            "MEDICAL 11-12", "MEDICAL 10-11", "MEDICAL 09-10"};
    String[] ALL = {"DU 12-13", "MEDICAL 13-14", "MEDICAL 09-10"};

    ShowChapter subjectAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.previousyearques);
        banglafont = Typeface.createFromAsset(getApplicationContext()
                .getAssets(), "font/solaimanlipi.ttf");

        Read = (Button) findViewById(R.id.btnRead);
        subject = (Spinner) findViewById(R.id.spnSubject);

        subjectAdapter = new ShowChapter(PreviousYearQues.this,
                R.layout.chapterlayout, ALL);
        subject.setAdapter(subjectAdapter);

        Read.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int index = subject.getSelectedItemPosition();
                if (subject.getSelectedItem().toString().equals("MEDICAL 09-10")) {
                    Intent intent = new Intent(
                            "android.intent.action.SHOWPREVIOUSQUESTION");
                    intent.putExtra("subname", "medical_09_10");

                    startActivity(intent);
                } else if (subject.getSelectedItem().toString().equals("MEDICAL 13-14")) {
                    Intent intent = new Intent(
                            "android.intent.action.SHOWPREVIOUSQUESTION");
                    intent.putExtra("subname", "medical_13_14");

                    startActivity(intent);
                } else if (subject.getSelectedItem().toString().equals("DU 12-13")) {
                    Intent intent = new Intent(
                            "android.intent.action.SHOWPREVIOUSQUESTION");
                    intent.putExtra("subname", "du_12_13");

                    startActivity(intent);
                }
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
