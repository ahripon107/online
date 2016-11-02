package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class Help extends Activity {
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help);
        setTitle("HELP");
        text = (TextView) findViewById(R.id.help);
        text.setTypeface(Typeface.createFromAsset(getAssets(),
                "font/DroidSansFallback.ttf"));
        StringBuilder builder = new StringBuilder();
        builder.append("This application is totally free.There are a lot of questions based on three categories: Engineering, University and Medical.\n\n");
        builder.append("You can give exam in two categories: Practice Exam and Model Test.When you are taking preparation on a certain chapter, you can justify your preparation by giving a practice exam.You can choose a certain number of questions here.\n\n");
        builder.append("When your preparation is complete you can give exam on whole syllabus by choosing the option Model Test.Exam on a particular paper or particular subject will consist of 50 questions.For full syllabus the exam will be same standard of actual admission exam.\n\n");
        builder.append("More questions and features will be added very soon.So stay in touch.");

        text.setText(builder);
    }
}
