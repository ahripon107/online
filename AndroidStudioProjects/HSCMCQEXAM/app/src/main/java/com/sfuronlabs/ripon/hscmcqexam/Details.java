package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

public class Details extends Activity {
    PerDetails myadapter;
    ListView DetailsView;

    ArrayList<String> questions;
    ArrayList<String> Myanswers;
    ArrayList<String> CorrectAnswers;
    ArrayList<DetailsAnswer> objects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details);
        setTitle("DETAILS");

        questions = new ArrayList<String>();
        Myanswers = new ArrayList<String>();
        CorrectAnswers = new ArrayList<String>();
        objects = new ArrayList<DetailsAnswer>();
        DetailsView = (ListView) findViewById(R.id.lvDetails);

        Intent intent = getIntent();

        questions = intent.getExtras().getStringArrayList("onlyques");
        Myanswers = intent.getExtras().getStringArrayList("answer");
        CorrectAnswers = intent.getExtras().getStringArrayList("correctans");

        for (int index = 0; index < questions.size(); index++) {
            objects.add(new DetailsAnswer(questions.get(index), Myanswers
                    .get(index), CorrectAnswers.get(index)));
        }

        myadapter = new PerDetails(this, objects);
        DetailsView.setAdapter(myadapter);
    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        //finish();
    }
}
