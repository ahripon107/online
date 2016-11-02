package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class FirstPage extends Activity {
    public FirstPage() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstlayout);
        final Thread t;
        t = new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(2000);

                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } finally {
                    Intent intent1 = new Intent(FirstPage.this,Login.class);

                    //Intent intent = new Intent("android.intent.action.CHOOSESUBJECT");
                    startActivity(intent1);

                }
            }

        });
        t.start();
    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        finish();
    }
}
