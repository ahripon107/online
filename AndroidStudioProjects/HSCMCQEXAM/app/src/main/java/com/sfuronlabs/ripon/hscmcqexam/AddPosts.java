package com.sfuronlabs.ripon.hscmcqexam;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ripon on 5/22/15.
 */

public class AddPosts extends Activity implements View.OnClickListener {
    private EditText title, message;
    private Button  mSubmit;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    //php add a comment script

    //localhost :
    //testing on your device
    //put your local ip instead,  on windows, run CMD > ipconfig
    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
    // private static final String POST_COMMENT_URL = "http://xxx.xxx.x.x:1234/webservice/addcomment.php";

    //testing on Emulator:
    private  String POST_COMMENT_URL = "http://vpn.gd/webservice/addcomment.php";

    //testing from a real server:
    //private static final String POST_COMMENT_URL = "http://www.mybringback.com/webservice/addcomment.php";

    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_comment);
        Intent inte = getIntent();
        POST_COMMENT_URL = inte.getExtras().getString("url3");

        title = (EditText)findViewById(R.id.title);
        message = (EditText)findViewById(R.id.message);

        mSubmit = (Button)findViewById(R.id.submit);
        mSubmit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (title.getText().toString().equals("") || message.getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(),"Please give input correctly",Toast.LENGTH_LONG).show();
        }
        else if(isConnectingToInternet())
        {
            new PostComment().execute();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Please conect to internet",Toast.LENGTH_LONG).show();
        }

    }


    class PostComment extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AddPosts.this);
            pDialog.setMessage("Posting...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;

            String post_title = title.getText().toString();
            String post_message = message.getText().toString();

            //We need to change this:
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(AddPosts.this);
            String post_username = sp.getString("name", "anon");


            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("name", post_username));
                params.add(new BasicNameValuePair("title", post_title));
                params.add(new BasicNameValuePair("message", post_message));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        POST_COMMENT_URL, "POST", params);

                // full json response
                Log.d("Post Comment attempt", json.toString());

                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("Comment Added!", json.toString());
                    finish();
                    return json.getString(TAG_MESSAGE);
                }else{
                    Log.d("Comment Failure!", json.getString(TAG_MESSAGE));
                    return json.getString(TAG_MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            pDialog.dismiss();
            if (file_url != null){
                Toast.makeText(AddPosts.this, file_url, Toast.LENGTH_LONG).show();
            }

        }

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
                        Log.d("Network", "NETWORKnAME: "+info[i].getTypeName());
                        return true;
                    }

        }
        return false;
    }
}
