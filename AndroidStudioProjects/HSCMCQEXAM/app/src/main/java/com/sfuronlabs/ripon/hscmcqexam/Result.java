package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends Activity {
    TextView myScore;
    TextView TotalQues;
    TextView CorrectAns;
    TextView CorrectMark;
    TextView WrongAns;
    TextView WrongMark;
    TextView skippedTotal;
    Button Answers;
    Button ok;
    int correct, skipped, wrong;

    String marks;
    ArrayList<String> questions;
    ArrayList<String> Myanswers;
    ArrayList<String> CorrectAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);
        setTitle("RESULT");
        questions = new ArrayList<String>();
        Myanswers = new ArrayList<String>();
        CorrectAnswers = new ArrayList<String>();

        Intent intent = getIntent();
        marks = intent.getExtras().getString("marks");


        questions = intent.getExtras().getStringArrayList("onlyques");
        Myanswers = intent.getExtras().getStringArrayList("answer");
        CorrectAnswers = intent.getExtras().getStringArrayList("correctans");

        skipped = 0;
        correct = Integer.parseInt(marks);


        for (int i = 0; i < Myanswers.size(); i++) {
            if (Myanswers.get(i).toString().equals("nil")) {
                skipped++;
            }
        }
        wrong = questions.size() - (correct + skipped);

        myScore = (TextView) findViewById(R.id.score);
        TotalQues = (TextView) findViewById(R.id.totalques);
        CorrectAns = (TextView) findViewById(R.id.correctans);
        CorrectMark = (TextView) findViewById(R.id.correctmark);
        WrongAns = (TextView) findViewById(R.id.wrongans);
        WrongMark = (TextView) findViewById(R.id.wrongmark);
        skippedTotal = (TextView) findViewById(R.id.skipped);

        Answers = (Button) findViewById(R.id.answersheet);
        ok = (Button) findViewById(R.id.testagain);

        myScore.setText("" + (correct - wrong * 0.25));

        //myScore.setText(""+(Integer.parseInt(marks)-(questions.size()-Integer.parseInt(marks))*0.25));
        TotalQues.setText("Total Questions: " + questions.size());
        CorrectAns.setText(marks);
        CorrectMark.setText(marks);
        WrongAns.setText("" + wrong);
        //WrongAns.setText(""+ (questions.size()-Integer.parseInt(marks)));
        WrongMark.setText("" + (wrong * 0.25));
        //WrongMark.setText("-"+ (questions.size()-Integer.parseInt(marks))*0.25);
        skippedTotal.setText(skipped + "");

        Answers.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                Intent intent1 = new Intent("android.intent.action.DETAILS");

                intent1.putStringArrayListExtra("onlyques", questions);
                intent1.putStringArrayListExtra("answer", Myanswers);
                intent1.putStringArrayListExtra("correctans",
                        CorrectAnswers);

                startActivity(intent1);

            }
        });

        ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });

        String[] prev = loadArray("name", this);

        int x = prev.length;
        if (x <= 5) {
            String[] newStr = new String[prev.length + 1];
            for (int i = 0; i < x; i++) {
                newStr[i] = prev[i];
            }
            newStr[x] = "  " + (correct - wrong * 0.25) + " OUT OF " + questions.size();
            saveArray(newStr, "name", this);
        } else {
            String[] newStr = new String[prev.length];
            for (int i = 0; i < x - 1; i++) {
                newStr[i] = prev[i + 1];
            }
            newStr[x - 1] = "  " + (correct - wrong * 0.25) + " OUT OF " + questions.size();
            saveArray(newStr, "name", this);
        }


    }

    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        finish();
    }

    public boolean saveArray(String[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("com.idealapps.hscmcqexam", 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName + "_size", array.length);
        for (int i = 0; i < array.length; i++)
            editor.putString(arrayName + "_" + i, array[i]);
        return editor.commit();
    }

    public String[] loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences("com.idealapps.hscmcqexam", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];
        for (int i = 0; i < size; i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }
}
