package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import java.util.ArrayList;
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

public class ModelTest extends Activity {
    int noOfQues;

    Random rand;
    CountDownTimer tmr;
    //DatabaseHelper dbHelper;
    ArrayList<Question> questions;
    ArrayList<String> answers;
    ArrayList<String> correctans;
    ArrayList<String> onlyques;
    ArrayList<Question> SelectedQuestions;
    ArrayList<Question> AllQuestions;
    int endFlag;
    int datacompleteflag;
    String str;

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
    private ProgressDialog pDialog;
    JSONParser jsonParser;


    // String[] BoardCheckList = { "0", "1", "5", "6", "7", "11", "12", "13" };
    // String[] EngCheckList = { "0", "2", "5", "8", "9", "11", "12", "14" };
    // String[] UniCheckList = { "0", "3", "6", "8", "10", "11", "13", "14" };
    // String[] MedCheckList = { "0", "4", "7", "9", "10", "12", "13", "14" };

    String[] EngCheckList = {"0", "1", "2", "4"};
    String[] UniCheckList = {"0", "2", "3", "6"};
    String[] MedCheckList = {"0", "1", "3", "5"};

    String[] SelectedCheckList = {};

    String[] physics1TableList = {"physics1chap2", "physics1chap3",
            "physics1chap4", "physics1chap5", "physics1chap6", "physics1chap7",
            "physics1chap8", "physics1chap9", "physics1chap10"};
    String[] physics2TableList = {"physics2chap1", "physics2chap2",
            "physics2chap3", "physics2chap4", "physics2chap5", "physics2chap6",
            "physics2chap8"};
    String[] physicsFullTableList = {};
    String[] Chemistry1TableList = {"chemistry1chap2", "chemistry1chap3",
            "chemistry1chap4"};
    String[] Chemistry2TableList = {"chemistry2chap1", "chemistry2chap2",
            "chemistry2chap3", "chemistry2chap4"};
    String[] ChemistryFullTableList = {};
    String[] Mathematics1TableList = {"BinnasSomabesh"};
    String[] Mathematics2TableList = {"Kinetics", "Probability"};
    String[] MathematicsFullTableList = {};
    String[] Biology1TableList = {"Cell", "CellDivision", "Tissue"};
    String[] Biology2TableList = {"Blood", "Respiretory"};
    String[] BiologyFullTableList = {};
    String[] SelectedTableList = {};

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
    int time = 0;
    Typeface banglafont;
    int category;
    int firstPaper, secondPaper;
    int fullphy1, fullphy2, fullche1, fullche2, fullbio1, fullbio2, fullmath1,
            fullmath2;
    int gk, english;

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
        AllQuestions = new ArrayList<Question>();
        endFlag = 0;
        datacompleteflag =0;
        jsonParser = new JSONParser();

        rand = new Random(System.currentTimeMillis());

        Intent intent = getIntent();
        String subject = intent.getExtras().getString("Subject");
        String standard = intent.getExtras().getString("Standard");
        if (standard.equals("MEDICAL")) {
            SelectedCheckList = MedCheckList;
            noOfQues = 50;
            firstPaper = 25;
            secondPaper = 25;
            fullphy1 = 9;
            fullphy2 = 11;
            fullche1 = 10;
            fullche2 = 15;
            fullbio1 = 15;
            fullbio2 = 15;
            english = 15;
            gk = 10;
            if (subject.equals("FULL SYLLABUS")) {
                noOfQues = 75;
                category = 6;
                time = 45;
            }
        } else if (standard.equals("ENGINEERING")) {
            SelectedCheckList = EngCheckList;
            noOfQues = 50;
            firstPaper = 25;
            secondPaper = 25;
            fullphy1 = 16;
            fullphy2 = 17;
            fullche1 = 16;
            fullche2 = 17;
            fullmath1 = 17;
            fullmath2 = 17;

            if (subject.equals("FULL SYLLABUS")) {
                noOfQues = 100;
                category = 7;
                time = 85;
            }

        } else if (standard.equals("UNIVERSITY")) {
            SelectedCheckList = UniCheckList;
            noOfQues = 50;
            firstPaper = 25;
            secondPaper = 25;
            fullphy1 = 15;
            fullphy2 = 15;
            fullche1 = 15;
            fullche2 = 15;
            fullbio1 = 15;
            fullbio2 = 15;
            fullmath1 = 15;
            fullmath2 = 15;

            if (subject.equals("FULL SYLLABUS")) {
                noOfQues = 120;
                category = 8;
                time = 90;
            }

        }
        if (subject.equals("PHYSICS 1ST PAPER")) {
            SelectedTableList = physics1TableList;
            category = 1;
            time = 50;
        } else if (subject.equals("PHYSICS 2ND PAPER")) {
            SelectedTableList = physics2TableList;
            time = 50;
            category = 1;
        } else if (subject.equals("PHYSICS FULL")) {
            SelectedTableList = physicsFullTableList;
            category = 2;
            time = 50;

        } else if (subject.equals("CHEMISTRY 1ST PAPER")) {
            SelectedTableList = Chemistry1TableList;
            category = 1;
            time = 50;
        } else if (subject.equals("CHEMISTRY 2ND PAPER")) {
            SelectedTableList = Chemistry2TableList;
            category = 1;
            time = 50;
        } else if (subject.equals("CHEMISTRY FULL")) {
            SelectedTableList = ChemistryFullTableList;
            category = 3;
            time = 50;
        } else if (subject.equals("BIOLOGY 1ST PAPER")) {
            SelectedTableList = Biology1TableList;
            category = 1;
            time = 50;
        } else if (subject.equals("BIOLOGY 2ND PAPER")) {
            SelectedTableList = Biology2TableList;
            category = 1;
            time = 50;
        } else if (subject.equals("BIOLOGY FULL")) {
            SelectedTableList = BiologyFullTableList;
            category = 4;
            time = 50;
        } else if (subject.equals("MATHEMATICS 1ST PAPER")) {
            SelectedTableList = Mathematics1TableList;
            category = 1;
            time = 50;
        } else if (subject.equals("MATHEMATICS 2ND PAPER")) {
            SelectedTableList = Mathematics2TableList;
            category = 1;
            time = 50;
        } else if (subject.equals("MATHEMATICS FULL")) {
            SelectedTableList = MathematicsFullTableList;
            category = 5;
            time = 50;
        }

        //dbHelper = new DatabaseHelper(getApplicationContext());

        if (category == 1) {
            for (int p = 0; p < SelectedTableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(SelectedTableList[p],
                  //      SelectedCheckList);
                str = SelectedTableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < noOfQues; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
        } else if (category == 2) {
            for (int p = 0; p < physics1TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(physics1TableList[p],
                  //      SelectedCheckList);
                str = physics1TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < firstPaper; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < physics2TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(physics2TableList[p],
                 //       SelectedCheckList);
                str = physics2TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < secondPaper; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
        } else if (category == 3) {
            for (int p = 0; p < Chemistry1TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Chemistry1TableList[p],
                    //    SelectedCheckList);
                str = Chemistry1TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < firstPaper; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Chemistry2TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Chemistry2TableList[p],
                    //    SelectedCheckList);
                str = Chemistry2TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < secondPaper; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
        } else if (category == 4) {
            for (int p = 0; p < Biology1TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Biology1TableList[p],
                 //       SelectedCheckList);
                str = Biology1TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < firstPaper; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Biology2TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Biology2TableList[p],
                    //    SelectedCheckList);
                str = Biology2TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < secondPaper; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
        } else if (category == 5) {
            for (int p = 0; p < Mathematics1TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(
                  //      Mathematics1TableList[p], SelectedCheckList);
                str = Mathematics1TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < firstPaper; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Mathematics2TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(
                 //       Mathematics2TableList[p], SelectedCheckList);
                str = Mathematics2TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < secondPaper; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
        } else if (category == 6) {
            for (int p = 0; p < physics1TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(physics1TableList[p],
                   //     SelectedCheckList);
                str = physics1TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullphy1; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < physics2TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(physics2TableList[p],
                   //     SelectedCheckList);
                str = physics2TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullphy2; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Chemistry1TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Chemistry1TableList[p],
                    //    SelectedCheckList);
                str = Chemistry1TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullche1; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Chemistry2TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Chemistry2TableList[p],
                  //      SelectedCheckList);
                str = Chemistry2TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullche2; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Biology1TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Biology1TableList[p],
                   //     SelectedCheckList);
                str = Biology1TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullbio1; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Biology2TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Biology2TableList[p],
                    //    SelectedCheckList);
                str = Biology2TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullbio2; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
        } else if (category == 8) {
            for (int p = 0; p < physics1TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(physics1TableList[p],
                  //      SelectedCheckList);
                str = physics1TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullphy1; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < physics2TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(physics2TableList[p],
                   //     SelectedCheckList);
                str = physics2TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullphy2; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Chemistry1TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Chemistry1TableList[p],
                    //    SelectedCheckList);
                str = Chemistry1TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullche1; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Chemistry2TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Chemistry2TableList[p],
                 //       SelectedCheckList);
                str = Chemistry2TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullche2; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Biology1TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Biology1TableList[p],
                   //     SelectedCheckList);
                str = Biology1TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullbio1; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Biology2TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Biology2TableList[p],
                   //     SelectedCheckList);
                str = Biology2TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullbio2; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Mathematics1TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Mathematics1TableList[p],
                    //    SelectedCheckList);
                str = Mathematics1TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullmath1; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Mathematics2TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Mathematics2TableList[p],
                  //      SelectedCheckList);
                str = Mathematics2TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullmath2; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }

        } else if (category == 7) {
            for (int p = 0; p < physics1TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(physics1TableList[p],
                  //      SelectedCheckList);
                str = physics1TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullphy1; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < physics2TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(physics2TableList[p],
                    //    SelectedCheckList);
                str = physics2TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullphy2; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Chemistry1TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Chemistry1TableList[p],
                 //       SelectedCheckList);
                str = Chemistry1TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullche1; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Chemistry2TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Chemistry2TableList[p],
                  //      SelectedCheckList);
                str = Chemistry2TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullche2; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();


            for (int p = 0; p < Mathematics1TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Mathematics1TableList[p],
                    //    SelectedCheckList);
                str = Mathematics1TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullmath1; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }
            AllQuestions.clear();

            for (int p = 0; p < Mathematics2TableList.length; p++) {
                //questions = dbHelper.SelectedQuestions(Mathematics2TableList[p],
                   //     SelectedCheckList);
                str = Mathematics2TableList[p];
                new LoadQuestion().execute();
                while(datacompleteflag==0)
                {

                }
                datacompleteflag=0;
                for (int i=0;i<questions.size();i++)
                {
                    String stri = questions.get(i).getHardness();
                    if (!(stri.equals(SelectedCheckList[0]) || stri.equals(SelectedCheckList[1]) ||
                            stri.equals(SelectedCheckList[2]) || stri.equals(SelectedCheckList[3])))
                    {
                        questions.remove(i);
                    }
                }
                for (int x = 0; x < questions.size(); x++) {
                    AllQuestions.add(questions.get(x));
                }
            }
            for (int y = 0; y < fullmath2; y++) {
                int next = rand.nextInt(AllQuestions.size() - 1);

                SelectedQuestions.add(AllQuestions.get(next));
                correctans.add(AllQuestions.get(next).getCorrect());
                onlyques.add(AllQuestions.get(next).getQues());
                answers.add("nil");
                AllQuestions.remove(next);
            }

        }


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
        q = SelectedQuestions.get(index);
        quesno.setText("QUES NO: ");
        img.setText(" " + (index + 1) + "/" + noOfQues);

        ques.setText(q.getQues());
        Option1.setText(q.getOption1());
        Option2.setText(q.getOption2());
        Option3.setText(q.getOption3());
        Option4.setText(q.getOption4());
        rg.clearCheck();
        tmr = new CountDownTimer(time * 1000 * 60, 1000) {

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
        tmr.start();
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

                if (index < noOfQues) {
                    q = SelectedQuestions.get(index);
                    quesno.setText("QUES NO: ");
                    img.setText(" " + (index + 1) + "/" + noOfQues);
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
                    // Toast.makeText(getApplicationContext(),
                    // "Your Marks: " + marks, Toast.LENGTH_LONG).show();
                    // call finish activity
                }
            }
        });
        Skip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                index++;
                if (index < noOfQues) {
                    q = SelectedQuestions.get(index);
                    quesno.setText("QUES NO: ");
                    img.setText(" " + (index + 1) + "/" + noOfQues);
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
                    // Toast.makeText(getApplicationContext(),
                    // "Your Marks: " + marks, Toast.LENGTH_LONG).show();
                    // call finish activity
                }
            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                new AlertDialog.Builder(ModelTest.this)
                        .setMessage("Are you sure to quit the exam?")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // Perform Your Task Here--When Yes Is
                                        // Pressed.
                                        tmr.cancel();
                                        finish();
                                        dialog.cancel();
                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        // Perform Your Task Here--When No is
                                        // pressed
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
       /* @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ModelTest.this);
            pDialog.setMessage("Loading Posts...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }*/

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
       /* @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            pDialog.dismiss();

        }*/
    }
}
