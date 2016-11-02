package com.sfuronlabs.ripon.hscmcqexam;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

    Button Read;
    Spinner subject, noofques;
    Typeface banglafont, tf;
    String subjectname, standard;
    String sel;
    TextView tv1, tv2;
    String[] NoQues = {"10", "15", "20"};
    String[] SUB_PHYSICS_1 = {"ভেক্টর", "গতিবিদ্যা", "নিউটনিয়ান বলবিদ্যা",
            "কাজ, শক্তি ও ক্ষমতা", "মহাকর্ষ ও অভিকর্ষ", "পদার্থের গাঠনিক ধর্ম",
            "পর্যায়বৃত্তিক গতি", "তরঙ্গ", "আদর্শ গ্যাস ও গ্যাসের গতিতত্ত"};
    String[] SUB_CHEMISTRY_1 = {"গুণগত রসায়ন",
            "মৌলের পর্যায়বৃত্ত ধর্ম ও রাসায়নিক বন্ধন", "রাসায়নিক পরিবর্তন"};
    String[] SUB_MATH_1 = {"বিন্যাস ও সমাবেশ"};
    String[] SUB_MATH_2 = {"সম্ভাব্যতা", "গতিবিদ্যা"};
    String[] SUB_PHYSICS_2 = {"তাপগতিবিদ্যা", "স্থির তড়িৎ", "চল তড়িৎ",
            "তড়িৎ প্রবাহের চৌম্বক ক্রিয়া ও চৌম্বকত্ব",
            "তড়িৎচৌম্বকীয় আবেশ ও পরিবর্তী প্রবাহ", "জ্যামিতিক আলোকবিজ্ঞান",
            "আধুনিক পদার্থবিজ্ঞানের সূচনা"};
    String[] SUB_CHEMISTRY_2 = {"পরিবেশ রসায়ন", "জৈব রসায়ন", "পরিমাণগত রসায়ন",
            "তড়িৎ রসায়ন"};
    String[] SUB_BIOLOGY_1 = {"কোষ ও এর গঠন","কোষ বিভাজন","কোষ রসায়ন","অণুজীব","শৈবাল ও ছত্রাক","রায়োফাইটা ও টেরিডোফাইটা","নগ্নবীজী ও আবৃতবীজী উদ্ভিদ","টিস্যু ও টিস্যুতন্ত্র","উদ্ভিদ শারীরতত্ত্ব","উদ্ভিদ প্রজনন",
    "জীবপ্রযুক্তি","জীবের পরিবেশ, বিস্তার ও সংরক্ষণ"};
    String[] SUB_BIOLOGY_2 = {"প্রাণীর বিভিন্নতা ও শ্রেণিবিন্যাস", "প্রাণীর পরিচিতি","মানব শারীরতত্ত্ব: পরিপাক ও শোষণ",
    "মানব শারীরতত্ত্ব: রক্ত ও সংবহন","মানব শারীরতত্ত্ব: শ্বসন ও শ্বাসক্রিয়া","মানব শারীরতত্ত্ব: বর্জ্য ও নিষ্কাশন",
    "মানব শারীরতত্ত্ব: চলন ও অঙ্গচালনা","মানব শারীরতত্ত্ব: সমন্বয় ও নিয়ন্ত্রণ","মানব জীবনের ধারাবাহিকতা","মানবদেহের প্রতিরক্ষা (ইমিউনিটি)",
    "জীনতত্ত্ব ও বিবর্তন","প্রাণীর আচরণ"};

    ShowChapter subjectAdapter, NoOfQuesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        banglafont = Typeface.createFromAsset(getApplicationContext()
                .getAssets(), "font/solaimanlipi.ttf");
        tf = Typeface.createFromAsset(this.getAssets(),
                "font/Amaranth-Bold.ttf");
        Read = (Button) findViewById(R.id.btnRead);
        tv1 = (TextView) findViewById(R.id.textView1);
        tv1.setTextColor(Color.BLUE);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv2.setTextColor(Color.BLUE);
        tv1.setTypeface(tf);
        tv2.setTypeface(tf);
        subject = (Spinner) findViewById(R.id.spnSubject);
        noofques = (Spinner) findViewById(R.id.spnNoOfQues);

        Intent intent = getIntent();
        subjectname = intent.getExtras().getString("name");
        standard = intent.getExtras().getString("Standard");

        if (subjectname.equals("Physics1")) {
            subjectAdapter = new ShowChapter(MainActivity.this,
                    R.layout.chapterlayout, SUB_PHYSICS_1);
            subject.setAdapter(subjectAdapter);
        } else if (subjectname.equals("Chemistry1")) {
            subjectAdapter = new ShowChapter(MainActivity.this,
                    R.layout.chapterlayout, SUB_CHEMISTRY_1);
            subject.setAdapter(subjectAdapter);
        } else if (subjectname.equals("Mathematics1")) {
            subjectAdapter = new ShowChapter(MainActivity.this,
                    R.layout.chapterlayout, SUB_MATH_1);
            subject.setAdapter(subjectAdapter);
        } else if (subjectname.equals("Biology1")) {
            subjectAdapter = new ShowChapter(MainActivity.this,
                    R.layout.chapterlayout, SUB_BIOLOGY_1);
            subject.setAdapter(subjectAdapter);
        } else if (subjectname.equals("Physics2")) {
            subjectAdapter = new ShowChapter(MainActivity.this,
                    R.layout.chapterlayout, SUB_PHYSICS_2);
            subject.setAdapter(subjectAdapter);
        } else if (subjectname.equals("Chemistry2")) {
            subjectAdapter = new ShowChapter(MainActivity.this,
                    R.layout.chapterlayout, SUB_CHEMISTRY_2);
            subject.setAdapter(subjectAdapter);
        } else if (subjectname.equals("Mathematics2")) {
            subjectAdapter = new ShowChapter(MainActivity.this,
                    R.layout.chapterlayout, SUB_MATH_2);
            subject.setAdapter(subjectAdapter);
        } else if (subjectname.equals("Biology2")) {
            subjectAdapter = new ShowChapter(MainActivity.this,
                    R.layout.chapterlayout, SUB_BIOLOGY_2);
            subject.setAdapter(subjectAdapter);
        }

        NoOfQuesAdapter = new ShowChapter(MainActivity.this,
                R.layout.chapterlayout, NoQues);
        noofques.setAdapter(NoOfQuesAdapter);
        setTitle("Chapter Selection");

        Read.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                sel = (String) noofques.getSelectedItem();

                int index = subject.getSelectedItemPosition();
                if (subjectname.equals("Physics1")) {
                    if (index == 0) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics1chap2");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 1) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics1chap3");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);

                        startActivity(intent);
                    } else if (index == 2) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics1chap4");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 3) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics1chap5");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 4) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics1chap6");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 5) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics1chap7");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 6) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics1chap8");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 7) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics1chap9");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 8) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics1chap10");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    }

                } else if (subjectname.equals("Physics2")) {
                    if (index == 0) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics2chap1");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 1) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics2chap2");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 2) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics2chap3");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 3) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics2chap4");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 4) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics2chap5");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 5) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics2chap6");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 6) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "physics2chap8");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    }
                } else if (subjectname.equals("Chemistry1")) {
                    if (index == 0) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "chemistry1chap2");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 1) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "chemistry1chap3");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 2) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "chemistry1chap4");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 3) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "chemistry1chap5");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    }
                } else if (subjectname.equals("Chemistry2")) {
                    if (index == 0) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "chemistry2chap1");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 1) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "chemistry2chap2");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 2) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "chemistry2chap3");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 3) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "chemistry2chap4");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (index == 4) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "chemistry2chap5");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    }
                } else if (subjectname.equals("Mathematics1")) {
                    if (subject.getSelectedItem().toString()
                            .equals("বিন্যাস ও সমাবেশ")) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "BinnasSomabesh");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    }

                } else if (subjectname.equals("Mathematics2")) {
                    if (subject.getSelectedItem().toString()
                            .equals("সম্ভাব্যতা")) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "Probability");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    } else if (subject.getSelectedItem().toString()
                            .equals("গতিবিদ্যা")) {
                        Intent intent = new Intent(
                                "android.intent.action.STARTPHYSICSEXAM");
                        intent.putExtra("subname", "Kinetics");
                        intent.putExtra("standard", standard);
                        intent.putExtra("noofques", sel);
                        startActivity(intent);
                    }

                } else if (subjectname.equals("Biology1")) {
                    switch (index)
                    {
                        default:
                            return;
                        case 0:
                            Intent i = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i.putExtra("subname", "Cell");
                            i.putExtra("standard", standard);
                            i.putExtra("noofques", sel);
                            startActivity(i);
                            return;
                        case 1:
                            Intent i1 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i1.putExtra("subname", "CellDivision");
                            i1.putExtra("standard", standard);
                            i1.putExtra("noofques", sel);
                            startActivity(i1);
                            return;
                        case 2:
                            Intent i2 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i2.putExtra("subname", "CellChemistry");
                            i2.putExtra("standard", standard);
                            i2.putExtra("noofques", sel);
                            startActivity(i2);
                            return;
                        case 3:
                            Intent i3 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i3.putExtra("subname", "Onujib");
                            i3.putExtra("standard", standard);
                            i3.putExtra("noofques", sel);
                            startActivity(i3);
                            return;
                        case 4:
                            Intent i4 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i4.putExtra("subname", "Shoibal");
                            i4.putExtra("standard", standard);
                            i4.putExtra("noofques", sel);
                            startActivity(i4);
                            return;
                        case 5:
                            Intent i5 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i5.putExtra("subname", "Briophyta");
                            i5.putExtra("standard", standard);
                            i5.putExtra("noofques", sel);
                            startActivity(i5);
                            return;
                        case 6:
                            Intent i6 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i6.putExtra("subname", "Nognobiji");
                            i6.putExtra("standard", standard);
                            i6.putExtra("noofques", sel);
                            startActivity(i6);
                            return;
                        case 7:
                            Intent i7 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i7.putExtra("subname", "Tissue");
                            i7.putExtra("standard", standard);
                            i7.putExtra("noofques", sel);
                            startActivity(i7);
                            return;
                        case 8:
                            Intent i8 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i8.putExtra("subname", "Sharirtotto");
                            i8.putExtra("standard", standard);
                            i8.putExtra("noofques", sel);
                            startActivity(i8);
                            return;
                        case 9:
                            Intent i9 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i9.putExtra("subname", "Projonon");
                            i9.putExtra("standard", standard);
                            i9.putExtra("noofques", sel);
                            startActivity(i9);
                            return;
                        case 10:
                            Intent i10 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i10.putExtra("subname", "Jibprojukti");
                            i10.putExtra("standard", standard);
                            i10.putExtra("noofques", sel);
                            startActivity(i10);
                            return;
                        case 11:
                            Intent i11 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i11.putExtra("subname", "Shongrokkhon");
                            i11.putExtra("standard", standard);
                            i11.putExtra("noofques", sel);
                            startActivity(i11);
                            return;
                    }
                } else if (subjectname.equals("Biology2")) {
                    switch (index)
                    {
                        default:
                            return;
                        case 0:
                            Intent i = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i.putExtra("subname", "Srenibinnas");
                            i.putExtra("standard", standard);
                            i.putExtra("noofques", sel);
                            startActivity(i);
                            return;
                        case 1:
                            Intent i1 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i1.putExtra("subname", "Porichiti");
                            i1.putExtra("standard", standard);
                            i1.putExtra("noofques", sel);
                            startActivity(i1);
                            return;
                        case 2:
                            Intent i2 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i2.putExtra("subname", "Digestion");
                            i2.putExtra("standard", standard);
                            i2.putExtra("noofques", sel);
                            startActivity(i2);
                            return;
                        case 3:
                            Intent i3 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i3.putExtra("subname", "Blood");
                            i3.putExtra("standard", standard);
                            i3.putExtra("noofques", sel);
                            startActivity(i3);
                            return;
                        case 4:
                            Intent i4 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i4.putExtra("subname", "Respiretory");
                            i4.putExtra("standard", standard);
                            i4.putExtra("noofques", sel);
                            startActivity(i4);
                            return;
                        case 5:
                            Intent i5 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i5.putExtra("subname", "Borjo");
                            i5.putExtra("standard", standard);
                            i5.putExtra("noofques", sel);
                            startActivity(i5);
                            return;
                        case 6:
                            Intent i6 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i6.putExtra("subname", "Movement");
                            i6.putExtra("standard", standard);
                            i6.putExtra("noofques", sel);
                            startActivity(i6);
                            return;
                        case 7:
                            Intent i7 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i7.putExtra("subname", "Somonnoy");
                            i7.putExtra("standard", standard);
                            i7.putExtra("noofques", sel);
                            startActivity(i7);
                            return;
                        case 8:
                            Intent i8 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i8.putExtra("subname", "Dharabahikota");
                            i8.putExtra("standard", standard);
                            i8.putExtra("noofques", sel);
                            startActivity(i8);
                            return;
                        case 9:
                            Intent i9 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i9.putExtra("subname", "Protirokkha");
                            i9.putExtra("standard", standard);
                            i9.putExtra("noofques", sel);
                            startActivity(i9);
                            return;
                        case 10:
                            Intent i10 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i10.putExtra("subname", "Genetics");
                            i10.putExtra("standard", standard);
                            i10.putExtra("noofques", sel);
                            startActivity(i10);
                            return;
                        case 11:
                            Intent i11 = new Intent("android.intent.action.STARTPHYSICSEXAM");
                            i11.putExtra("subname", "Achoron");
                            i11.putExtra("standard", standard);
                            i11.putExtra("noofques", sel);
                            startActivity(i11);
                            return;
                    }

                }

            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    public boolean onCreateOptionsMenu(Menu paramMenu) {
        getMenuInflater().inflate(R.menu.menu_main, paramMenu);
        return super.onCreateOptionsMenu(paramMenu);
    }

    public boolean onOptionsItemSelected(MenuItem paramMenuItem) {
        switch (paramMenuItem.getItemId()) {
            default:
                return super.onOptionsItemSelected(paramMenuItem);
            case R.id.item1:
                startActivity(new Intent(this, Help.class));
                return true;
            case R.id.item2:
                Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.previousresult);
                dialog.setTitle("PREVIOUS RESULTS");
                TextView testMark = (TextView) dialog.findViewById(R.id.tvTestMark);
                String[] loadedArray = loadArray("name", this);
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < loadedArray.length; i++) {
                    builder.append(loadedArray[i] + "\n");

                }
                testMark.setText(builder.toString());
                dialog.show();
                return true;

        }
        // startActivity(new Intent(this, About.class));
        // return true;
    }

    public String[] loadArray(String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences(
                "com.idealapps.hscmcqexam", 0);
        int size = prefs.getInt(arrayName + "_size", 0);
        String array[] = new String[size];
        for (int i = 0; i < size; i++)
            array[i] = prefs.getString(arrayName + "_" + i, null);
        return array;
    }
}
