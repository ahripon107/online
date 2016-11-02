package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Options extends Activity {
    boolean doubleBackToExitPressedOnce = false;
    Integer[] imageId;
    ListView list;
    String[] web = {"Model Tests", "Previous Year Questions", "Syllabus",
            "Admission Notice","Forum"};

    public Options() {
        Integer[] arrayOfInteger = new Integer[5];
        arrayOfInteger[0] = Integer.valueOf(R.drawable.letter_icon);
        arrayOfInteger[1] = Integer.valueOf(R.drawable.second);
        // arrayOfInteger[2] = Integer.valueOf(R.drawable.practice);
        arrayOfInteger[2] = Integer.valueOf(R.drawable.syllabus);
        arrayOfInteger[3] = Integer.valueOf(R.drawable.guideline);
        arrayOfInteger[4] = Integer.valueOf(R.drawable.practice);
        this.imageId = arrayOfInteger;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options);

        OptionsListStyle localOptionsListStyle = new OptionsListStyle(this,
                this.web, this.imageId, "font/Amaranth-Bold.ttf");

        list = (ListView) findViewById(R.id.list);
        View localView = getLayoutInflater().inflate(R.layout.options_single,
                null);
        list.setAdapter(localOptionsListStyle);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // TODO Auto-generated method stub
                switch (position) {
                    default:
                        return;
                    case 0:
                        if (isConnectingToInternet())
                        {
                            Intent intent = new Intent(Options.this,ChooseSubjectFrag.class);

                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Please connect to internet",Toast.LENGTH_LONG).show();
                        }
                        return;
                    case 1:
                        if (isConnectingToInternet())
                        {
                            Intent intent1 = new Intent(
                                    "android.intent.action.PREVIOUSYEARQUES");
                            startActivity(intent1);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Please connect to internet",Toast.LENGTH_LONG).show();
                        }
                        return;
                    case 2:
                        Intent intent2 = new Intent(
                                "android.intent.action.SYLLABUS");
                        startActivity(intent2);
                        return;
                    case 3:
                        if (isConnectingToInternet())
                        {
                            Intent intent3 = new Intent(
                                    "android.intent.action.GUIDELINE");
                            startActivity(intent3);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Please connect to internet",Toast.LENGTH_LONG).show();
                        }
                        return;
                    case 4:
                        if (isConnectingToInternet())
                        {
                            Intent i = new Intent(Options.this,Forum.class);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Please connect to internet",Toast.LENGTH_LONG).show();
                        }
                        return;
                }

            }
        });

    }

    public void onBackPressed() {
        if (this.doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {
            public void run() {
                Options.this.doubleBackToExitPressedOnce = false;
            }
        }, 2000L);
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
    public  boolean isConnectingToInternet(){
        ConnectivityManager connectivity = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null)
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        Log.d("Network", "NETWORKnAME: " + info[i].getTypeName());
                        return true;
                    }

        }
        return false;
    }
}
