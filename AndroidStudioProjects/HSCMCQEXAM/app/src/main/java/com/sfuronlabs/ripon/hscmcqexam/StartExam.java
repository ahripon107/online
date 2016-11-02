package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class StartExam extends Activity {
    Random rand;
    CountDownTimer tmr;
    DatabaseHelper dbHelper;
    ArrayList<Question> questions;
    ArrayList<String> answers;
    ArrayList<String> correctans;
    ArrayList<String> onlyques;
    ArrayList<Question> SelectedQuestions;
    int endFlag;
    private ProgressDialog pDialog;
    JSONParser jsonParser;

    private String url = "http://vpn.gd/webservice/retrievequestion.php";
    private String ID = "_id";
    private String QUESTION = "question";
    private String OPTION1 = "option1";
    private String OPTION2 = "option2";
    private String OPTION3 = "option3";
    private String OPTION4 = "option4";
    private String CORRECT = "correct";
    private String HARDNESS = "hardness";
    private JSONArray mComments = null;
    //private ArrayList<HashMap<String,String>> mCommentList;
    // String[] BoardCheckList = {"0","1","5","6","7","11","12","13"};
    // String[] EngCheckList = {"0","2","5","8","9","11","12","14"};
    // String[] UniCheckList = {"0","3","6","8","10","11","13","14"};
    // String[] MedCheckList = {"0","4","7","9","10","12","13","14"};

    String[] EngCheckList = {"0", "1", "2", "4"};
    String[] UniCheckList = {"0", "2", "3", "6"};
    String[] MedCheckList = {"0", "1", "3", "5"};

    String[] SelectedCheckList = {};

    Question q;
    TextView quesno;
    TextView ques;
    TextView Timer;
    TextView img;
    String sel;
    RadioGroup rg;
    RadioButton Option1;
    RadioButton Option2;
    RadioButton Option3;
    RadioButton Option4;
    Button Submit;
    Button Skip;
    int index = 0;
    int marks = 0;
    int datacompleteflag;
    Typeface banglafont;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ques_layout);

        banglafont = Typeface.createFromAsset(getApplicationContext()
                .getAssets(), "font/solaimanlipi.ttf");

        questions = new ArrayList<Question>();
        answers = new ArrayList<String>();
        correctans = new ArrayList<String>();
        onlyques = new ArrayList<String>();
        SelectedQuestions = new ArrayList<Question>();
        jsonParser = new JSONParser();

        setTitle("Ongoing Exam");

        endFlag = 0;
        datacompleteflag=0;

        rand = new Random(System.currentTimeMillis());
        Intent intent = getIntent();
        str = intent.getExtras().getString("subname");
        String standard = intent.getExtras().getString("standard");
        if (standard.equals("MEDICAL")) {
            SelectedCheckList = MedCheckList;
        } else if (standard.equals("ENGINEERING")) {
            SelectedCheckList = EngCheckList;
        } else if (standard.equals("UNIVERSITY")) {
            SelectedCheckList = UniCheckList;
        } else {
            // SelectedCheckList = BoardCheckList;
        }
        sel = intent.getExtras().getString("noofques");
        //dbHelper = new DatabaseHelper(getApplicationContext());
        // questions = dbHelper.AllQuestions(str,SelectedCheckList);
        //questions = dbHelper.SelectedQuestions(str, SelectedCheckList);



        //while(datacompleteflag==0)
        //{

       // }




        //dbHelper.close();
        quesno = (TextView) findViewById(R.id.tvQuesNo);
        ques = (TextView) findViewById(R.id.tvQuestion);
        Timer = (TextView) findViewById(R.id.tvTimer);
        rg = (RadioGroup) findViewById(R.id.OptionsGroup);
        Option1 = (RadioButton) findViewById(R.id.option1);
        Option2 = (RadioButton) findViewById(R.id.option2);
        Option3 = (RadioButton) findViewById(R.id.option3);
        Option4 = (RadioButton) findViewById(R.id.option4);
        Submit = (Button) findViewById(R.id.btnSubmit);
        Skip = (Button) findViewById(R.id.btnSkip);
        img = (TextView) findViewById(R.id.myImageView);
        ques.setTypeface(banglafont);
        Option1.setTypeface(banglafont);
        Option2.setTypeface(banglafont);
        Option3.setTypeface(banglafont);
        Option4.setTypeface(banglafont);



        tmr = new CountDownTimer(Integer.parseInt(sel) * 10000 * 6, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                int hour = (int) millisUntilFinished / (3600 * 1000);
                millisUntilFinished = millisUntilFinished % (3600 * 1000);
                int minute = (int) millisUntilFinished / (60 * 1000);
                int second = (int) millisUntilFinished % (60 * 1000);
                Timer.setText("Time remaining: " + hour + " : " + minute
                        + " : " + second / 1000);

            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "Time up!!!",
                        Toast.LENGTH_LONG).show();
                endFlag = 1;
                Intent intent = new Intent("android.intent.action.RESULT");
                intent.putExtra("marks", marks + "");
                intent.putExtra("total", index + "");
                intent.putStringArrayListExtra("answer", answers);
                intent.putStringArrayListExtra("correctans", correctans);
                intent.putStringArrayListExtra("onlyques", onlyques);
                startActivity(intent);
            }
        };

        Submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                RadioButton temp = (RadioButton) findViewById(rg
                        .getCheckedRadioButtonId());
                if (temp == null) {
                    Toast.makeText(getApplicationContext(),
                            "Please select an answer", Toast.LENGTH_LONG)
                            .show();
                } else {
                    String ans = temp.getText().toString();
                    // answers.add(index, ans);
                    answers.set(index, ans);
                    if (ans.equals(q.getCorrect())) {
                        marks++;

                    }
                    index++;
                }

                if (index < Integer.parseInt(sel)) {
                    q = SelectedQuestions.get(index);
                    quesno.setText("QUES NO ");
                    img.setText("  " + (index + 1) + "/" + sel);
                    ques.setText(q.getQues());
                    Option1.setText(q.getOption1());
                    Option2.setText(q.getOption2());
                    Option3.setText(q.getOption3());
                    Option4.setText(q.getOption4());
                    rg.clearCheck();

                } else {
                    tmr.cancel();
                    endFlag = 1;
                    Intent intent = new Intent("android.intent.action.RESULT");
                    intent.putExtra("marks", marks + "");
                    intent.putExtra("total", index + "");
                    intent.putStringArrayListExtra("answer", answers);
                    intent.putStringArrayListExtra("correctans", correctans);
                    intent.putStringArrayListExtra("onlyques", onlyques);
                    startActivity(intent);
                    //Toast.makeText(getApplicationContext(),
                    //	"Your Marks: " + marks, Toast.LENGTH_LONG).show();
                    // call finish activity
                }
            }
        });

        Skip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                index++;
                if (index < Integer.parseInt(sel)) {
                    q = SelectedQuestions.get(index);
                    quesno.setText("QUES NO ");
                    img.setText("  " + (index + 1) + "/" + sel);
                    ques.setText(q.getQues());
                    Option1.setText(q.getOption1());
                    Option2.setText(q.getOption2());
                    Option3.setText(q.getOption3());
                    Option4.setText(q.getOption4());
                    rg.clearCheck();

                } else {
                    tmr.cancel();
                    endFlag = 1;
                    Intent intent = new Intent("android.intent.action.RESULT");
                    intent.putExtra("marks", marks + "");
                    intent.putExtra("total", index + "");
                    intent.putStringArrayListExtra("answer", answers);
                    intent.putStringArrayListExtra("correctans", correctans);
                    intent.putStringArrayListExtra("onlyques", onlyques);
                    startActivity(intent);
                    //Toast.makeText(getApplicationContext(),
                    //	"Your Marks: " + marks, Toast.LENGTH_LONG).show();
                    // call finish activity
                }
            }
        });
        new LoadQuestion().execute();
    }
    public void conductexam()
    {

        for (int i=0;i<questions.size();i++)
        {
            String stri = questions.get(i).getHardness();
            if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                    stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
            {
                questions.remove(i);
            }
        }
        for (int x = 0; x < Integer.parseInt(sel); x++) {
            // for (int x = 0; x < questions.size(); x++) {
            // answers.add("-");
            int next = rand.nextInt(questions.size() - 1);

            SelectedQuestions.add(questions.get(next));
            correctans.add(questions.get(next).getCorrect());
            onlyques.add(questions.get(next).getQues());
            answers.add("nil");
            questions.remove(next);
        }

        q = SelectedQuestions.get(index);
        quesno.setText("QUES NO ");
        img.setText("  " + (index + 1) + "/" + sel);
        ques.setText(q.getQues());
        Option1.setText(q.getOption1());
        Option2.setText(q.getOption2());
        Option3.setText(q.getOption3());
        Option4.setText(q.getOption4());

        rg.clearCheck();

        tmr.start();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                new AlertDialog.Builder(StartExam.this)
                        .setMessage("Are you sure to quit the exam?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Perform Your Task Here--When Yes Is Pressed.
                                tmr.cancel();
                                finish();
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Perform Your Task Here--When No is pressed
                                dialog.cancel();
                            }
                        }).show();
                break;
        }

        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if (endFlag == 1) {

            finish();
        }

    }
    public class LoadQuestion extends AsyncTask<Void, Void, Boolean>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(StartExam.this);
            pDialog.setMessage("Loading Questions...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //mCommentList = new ArrayList<HashMap<String, String>>();
            List<NameValuePair> tname = new ArrayList<NameValuePair>();
            tname.add(new BasicNameValuePair("tablename", str));
            JSONObject json = jsonParser.makeHttpRequest(
                    url, "POST", tname);
            try
            {
                mComments = json.getJSONArray("questions");
                for (int i=0;i<mComments.length();i++)
                {
                    JSONObject c = mComments.getJSONObject(i);
                    int id = c.getInt(ID);
                    String quest = c.getString(QUESTION);
                    String opt1 = c.getString(OPTION1);
                    String opt2 = c.getString(OPTION2);
                    String opt3 = c.optString(OPTION3);
                    String opt4 = c.getString(OPTION4);
                    String corr = c.getString(CORRECT);
                    String hard = c.getString(HARDNESS);
                    Question q = new Question(id,quest,opt1,opt2,opt3,opt4,corr,hard);
                    questions.add(q);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            datacompleteflag=1;
            return null;
        }
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            conductexam();
        }
    }
}
