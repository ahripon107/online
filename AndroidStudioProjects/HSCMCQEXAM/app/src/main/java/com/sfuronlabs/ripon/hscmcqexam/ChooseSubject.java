package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ChooseSubject extends Activity {}
/*
    List<String> ExamStandard = new ArrayList<String>();
    int endFlag;

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    TextView tv;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    int groupIndex;
    Button ResetButton, OKButton;
    String[] input = {null, null, null};
    String sel;
    Typeface tf;

    List<String> EngPractice = new ArrayList<String>();
    List<String> EngModelTest = new ArrayList<String>();
    List<String> BoardPractice_ModelTest_MedPractice = new ArrayList<String>();
    List<String> MedModelTest = new ArrayList<String>();
    List<String> UniPractice = new ArrayList<String>();
    List<String> UniModelTest = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choosesubject);
        groupIndex = 0;
        endFlag = 0;
        ResetButton = (Button) findViewById(R.id.btnReset);
        OKButton = (Button) findViewById(R.id.btnOK);
        OKButton.setEnabled(false);
        tv = (TextView) findViewById(R.id.textView1);

        tf = Typeface.createFromAsset(this.getAssets(),
                "font/Amaranth-Bold.ttf");
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        tv.setTypeface(tf);
        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(ChooseSubject.this,
                listDataHeader, listDataChild, tf);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                if (groupPosition == groupIndex)
                    return false;

                return true;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {

            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
                input[groupPosition] = listDataChild.get(
                        listDataHeader.get(groupPosition)).get(childPosition);
                Toast.makeText(getApplicationContext(), input[groupPosition],
                        Toast.LENGTH_SHORT).show();
                if (groupPosition == 0) {
                    listDataHeader.add("EXAM STANDARD");
                    listDataChild.put(listDataHeader.get(1), ExamStandard);
                } else if (groupPosition == 1)
                    listDataHeader.add("SUBJECT");
                listAdapter.notifyDataSetChanged();
                expListView.collapseGroup(groupIndex);
                if (groupIndex == 1) {

                    if (input[0] == "PRACTICE EXAM"
                            && input[1] == "ENGINEERING")
                        listDataChild.put(listDataHeader.get(2), EngPractice);
                    if (input[0] == "MODEL TEST" && input[1] == "ENGINEERING")
                        listDataChild.put(listDataHeader.get(2), EngModelTest);
                    if ((input[0] == "MODEL TEST" || input[0] == "PRACTICE EXAM")
                            && input[1] == "BOARD STANDARD")
                        listDataChild.put(listDataHeader.get(2),
                                BoardPractice_ModelTest_MedPractice);
                    if (input[0] == "PRACTICE EXAM" && input[1] == "MEDICAL")
                        listDataChild.put(listDataHeader.get(2),
                                BoardPractice_ModelTest_MedPractice);
                    if (input[0] == "MODEL TEST" && input[1] == "MEDICAL")
                        listDataChild.put(listDataHeader.get(2), MedModelTest);
                    if (input[0] == "MODEL TEST" && input[1] == "UNIVERSITY")
                        listDataChild.put(listDataHeader.get(2), UniModelTest);
                    if (input[0] == "PRACTICE EXAM" && input[1] == "UNIVERSITY")
                        listDataChild.put(listDataHeader.get(2), UniPractice);
                }
                groupIndex++;
                if (groupIndex < 3)
                    expListView.expandGroup(groupIndex, true);

                if (groupPosition == 2) OKButton.setEnabled(true);
                return false;
            }
        });
        ResetButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                if (groupIndex == 3) {
                    listDataHeader.remove(2);
                    listDataHeader.remove(1);
                    listAdapter.notifyDataSetChanged();
                    expListView.collapseGroup(groupIndex);
                    OKButton.setEnabled(false);


                } else if (groupIndex == 2) {
                    listDataHeader.remove(2);
                    listDataHeader.remove(1);
                    listAdapter.notifyDataSetChanged();
                    expListView.collapseGroup(groupIndex);
                } else if (groupIndex == 1) {
                    listDataHeader.remove(1);
                    listAdapter.notifyDataSetChanged();
                    expListView.collapseGroup(groupIndex);
                }
                groupIndex = 0;
                expListView.collapseGroup(groupIndex);
                input[0] = "";
                input[1] = "";
                input[2] = "";
            }
        });

        OKButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                endFlag = 1;
                if (input[0] == "PRACTICE EXAM") {
                    if (input[2] == "PHYSICS 1ST PAPER") {
                        Intent intent = new Intent(
                                "android.intent.action.STARTINGPOINT");
                        intent.putExtra("name", "Physics1");
                        intent.putExtra("Standard", input[1]);
                        startActivity(intent);
                    } else if (input[2] == "CHEMISTRY 1ST PAPER") {
                        Intent intent = new Intent(
                                "android.intent.action.STARTINGPOINT");
                        intent.putExtra("name", "Chemistry1");
                        intent.putExtra("Standard", input[1]);
                        startActivity(intent);
                    } else if (input[2] == "BIOLOGY 1ST PAPER"
                            && input[1] != "ENGINEERING") {
                        Intent intent = new Intent(
                                "android.intent.action.STARTINGPOINT");
                        intent.putExtra("name", "Biology1");
                        intent.putExtra("Standard", input[1]);
                        startActivity(intent);
                    } else if (input[2] == "MATHEMATICS 1ST PAPER"
                            && input[1] != "BOARD STANDARD") {
                        Intent intent = new Intent(
                                "android.intent.action.STARTINGPOINT");
                        intent.putExtra("name", "Mathematics1");
                        intent.putExtra("Standard", input[1]);
                        startActivity(intent);
                    }
                    if (input[2] == "PHYSICS 2ND PAPER") {
                        Intent intent = new Intent(
                                "android.intent.action.STARTINGPOINT");
                        intent.putExtra("name", "Physics2");
                        intent.putExtra("Standard", input[1]);
                        startActivity(intent);
                    } else if (input[2] == "CHEMISTRY 2ND PAPER") {
                        Intent intent = new Intent(
                                "android.intent.action.STARTINGPOINT");
                        intent.putExtra("name", "Chemistry2");
                        intent.putExtra("Standard", input[1]);
                        startActivity(intent);
                    } else if (input[2] == "BIOLOGY 2ND PAPER"
                            && input[1] != "ENGINEERING") {
                        Intent intent = new Intent(
                                "android.intent.action.STARTINGPOINT");
                        intent.putExtra("name", "Biology2");
                        intent.putExtra("Standard", input[1]);
                        startActivity(intent);
                    } else if (input[2] == "MATHEMATICS 2ND PAPER"
                            && input[1] != "BOARD STANDARD") {
                        Intent intent = new Intent(
                                "android.intent.action.STARTINGPOINT");
                        intent.putExtra("name", "Mathematics2");
                        intent.putExtra("Standard", input[1]);
                        startActivity(intent);
                    }
                } else if (input[0] == "PREVIOUS YEAR QUESTION") {
                    if (input[1] == "ENGINEERING") {
                        Intent intent = new Intent(
                                "android.intent.action.PREVIOUSYEARQUES");
                        intent.putExtra("name", "Engineering");
                        startActivity(intent);
                    } else if (input[1] == "UNIVERSITY") {
                        Intent intent = new Intent(
                                "android.intent.action.PREVIOUSYEARQUES");
                        intent.putExtra("name", "University");
                        startActivity(intent);
                    } else if (input[1] == "MEDICAL") {
                        Intent intent = new Intent(
                                "android.intent.action.PREVIOUSYEARQUES");
                        intent.putExtra("name", "Medical");
                        startActivity(intent);
                    }

                } else if (input[0] == "MODEL TEST") {
                    Intent intent = new Intent(
                            "android.intent.action.MODELTEST");
                    intent.putExtra("Standard", input[1]);
                    intent.putExtra("Subject", input[2]);
                    startActivity(intent);

                }

				/*
                 * } else if (input[2] == "PHYSICS 2ND PAPER") {
				 * intent.putExtra("Standard", input[1]);
				 * intent.putExtra("Subject", input[2]); startActivity(intent);
				 * } else if (input[2] == "PHYSICS FULL" && input[1] !=
				 * "BOARD STANDARD") { intent.putExtra("Standard", input[1]);
				 * intent.putExtra("Subject", input[2]); startActivity(intent);
				 *
				 * } else if (input[2] == "CHEMISTRY 1ST PAPER") {
				 * intent.putExtra("Standard", input[1]);
				 * intent.putExtra("Subject", input[2]); startActivity(intent);
				 *
				 * } else if (input[2] == "CHEMISTRY 2ND PAPER") {
				 * intent.putExtra("Standard", input[1]);
				 * intent.putExtra("Subject", input[2]); startActivity(intent);
				 *
				 * } else if (input[2] == "CHEMISTRY FULL" && input[1] !=
				 * "BOARD STANDARD") { intent.putExtra("Standard", input[1]);
				 * intent.putExtra("Subject", input[2]); startActivity(intent);
				 *
				 * } else if (input[2] == "MATHEMATICS 1ST PAPER" && input[1] !=
				 * "BOARD STANDARD" && input[1] != "MEDICAL") {
				 * intent.putExtra("Standard", input[1]);
				 * intent.putExtra("Subject", input[2]); startActivity(intent);
				 *
				 * } else if (input[2] == "MATHEMATICS 2ND PAPER" && input[1] !=
				 * "BOARD STANDARD" && input[1] != "MEDICAL") {
				 * intent.putExtra("Standard", input[1]);
				 * intent.putExtra("Subject", input[2]); startActivity(intent);
				 *
				 *
				 * } else if (input[2] == "MATHEMATICS FULL" && input[1] !=
				 * "BOARD STANDARD" && input[1] != "MEDICAL") {
				 * intent.putExtra("Standard", input[1]);
				 * intent.putExtra("Subject", input[2]); startActivity(intent);
				 *
				 *
				 * } else if (input[2] == "BIOLOGY 1ST PAPER" && input[1] !=
				 * "ENGINEERING") { intent.putExtra("Standard", input[1]);
				 * intent.putExtra("Subject", input[2]); startActivity(intent);
				 *
				 *
				 * } else if (input[2] == "BIOLOGY 2ND PAPER" && input[1] !=
				 * "ENGINEERING") { intent.putExtra("Standard", input[1]);
				 * intent.putExtra("Subject", input[2]); startActivity(intent);
				 *
				 *
				 * } else if (input[2] == "BIOLOGY FULL" && input[1] !=
				 * "BOARD STANDARD" && input[1] != "ENGINEERING") {
				 * intent.putExtra("Standard", input[1]);
				 * intent.putExtra("Subject", input[2]); startActivity(intent);
				 *
				 * }
				 *
				 * }
				 */
/*
            }
        });
    }

    /*
     * Preparing the list data
     */
 /*   private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("EXAM TYPE");
        // listDataHeader.add("EXAM STANDARD");
        // listDataHeader.add("SUBJECT");

        // Adding child data
        List<String> ExamType = new ArrayList<String>();
        ExamType.add("PRACTICE EXAM");
        ExamType.add("MODEL TEST");
        // ExamType.add("PREVIOUS YEAR QUESTION");


        // ExamStandard.add("BOARD STANDARD");
        ExamStandard.add("ENGINEERING");
        ExamStandard.add("UNIVERSITY");
        ExamStandard.add("MEDICAL");

        UniModelTest.add("PHYSICS 1ST PAPER");
        UniModelTest.add("PHYSICS 2ND PAPER");
        UniModelTest.add("PHYSICS FULL");
        UniModelTest.add("CHEMISTRY 1ST PAPER");
        UniModelTest.add("CHEMISTRY 2ND PAPER");
        UniModelTest.add("CHEMISTRY FULL");
        UniModelTest.add("BIOLOGY 1ST PAPER");
        UniModelTest.add("BIOLOGY 2ND PAPER");
        UniModelTest.add("BIOLOGY FULL");
        //UniModelTest.add("MATHEMATICS 1ST PAPER");
        //UniModelTest.add("MATHEMATICS 2ND PAPER");
        UniModelTest.add("MATHEMATICS FULL");
        UniModelTest.add("FULL SYLLABUS");

        UniPractice.add("PHYSICS 1ST PAPER");
        UniPractice.add("PHYSICS 2ND PAPER");
        UniPractice.add("CHEMISTRY 1ST PAPER");
        UniPractice.add("CHEMISTRY 2ND PAPER");
        UniPractice.add("BIOLOGY 1ST PAPER");
        UniPractice.add("BIOLOGY 2ND PAPER");
        UniPractice.add("MATHEMATICS 1ST PAPER");
        UniPractice.add("MATHEMATICS 2ND PAPER");

        BoardPractice_ModelTest_MedPractice.add("PHYSICS 1ST PAPER");
        BoardPractice_ModelTest_MedPractice.add("PHYSICS 2ND PAPER");
        BoardPractice_ModelTest_MedPractice.add("CHEMISTRY 1ST PAPER");
        BoardPractice_ModelTest_MedPractice.add("CHEMISTRY 2ND PAPER");
        BoardPractice_ModelTest_MedPractice.add("BIOLOGY 1ST PAPER");
        BoardPractice_ModelTest_MedPractice.add("BIOLOGY 2ND PAPER");

        MedModelTest.add("PHYSICS 1ST PAPER");
        MedModelTest.add("PHYSICS 2ND PAPER");
        MedModelTest.add("PHYSICS FULL");
        MedModelTest.add("CHEMISTRY 1ST PAPER");
        MedModelTest.add("CHEMISTRY 2ND PAPER");
        MedModelTest.add("CHEMISTRY FULL");
        MedModelTest.add("BIOLOGY 1ST PAPER");
        MedModelTest.add("BIOLOGY 2ND PAPER");
        MedModelTest.add("BIOLOGY FULL");
        MedModelTest.add("FULL SYLLABUS");

        EngPractice.add("PHYSICS 1ST PAPER");
        EngPractice.add("PHYSICS 2ND PAPER");
        EngPractice.add("CHEMISTRY 1ST PAPER");
        EngPractice.add("CHEMISTRY 2ND PAPER");
        EngPractice.add("MATHEMATICS 1ST PAPER");
        EngPractice.add("MATHEMATICS 2ND PAPER");

        EngModelTest.add("PHYSICS 1ST PAPER");
        EngModelTest.add("PHYSICS 2ND PAPER");
        EngModelTest.add("PHYSICS FULL");
        EngModelTest.add("CHEMISTRY 1ST PAPER");
        EngModelTest.add("CHEMISTRY 2ND PAPER");
        EngModelTest.add("CHEMISTRY FULL");
        //EngModelTest.add("MATHEMATICS 1ST PAPER");
        //EngModelTest.add("MATHEMATICS 2ND PAPER");
        EngModelTest.add("MATHEMATICS FULL");
        EngModelTest.add("FULL SYLLABUS");

        listDataChild.put(listDataHeader.get(0), ExamType); // Header, Child
        // data
        // listDataChild.put(listDataHeader.get(1), ExamStandard);
        // listDataChild.put(listDataHeader.get(2), comingSoon);
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

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        if (endFlag == 1) {
            finish();
        }

    }

}
*/