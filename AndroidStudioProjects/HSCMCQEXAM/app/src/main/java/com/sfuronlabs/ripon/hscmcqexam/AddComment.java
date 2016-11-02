package com.sfuronlabs.ripon.hscmcqexam;

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Ripon on 5/25/15.
 */
public class AddComment extends Activity {
    private ProgressDialog progressDialog;
    private String READ_COMMENTS_URL1 = "http://vpn.gd/webservice/readcommenttopost.php";
    private EditText commnt;
    String postTitle;
    int pid;

    TextView titl,quest;
    Button mybtn;
    JSONParser jsonParser;

    private static final String TAG_COMMENT_ID = "commentid";
    private static final String TAG_COMMENT = "comment";
    private static final String TAG_COMMENTS = "comments";
    private static final String TAG_COMMENTER = "name";

    String SUCCESS = "success";
    String MESSAGE = "message";
    private String COMMENT_URL = "http://vpn.gd/webservice/addcommenttopost.php";


    private JSONArray mComments = null;
    private ArrayList<HashMap<String, String>> mCommentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcomment);
        mybtn = (Button) findViewById(R.id.button);
        commnt = (EditText) findViewById(R.id.editText);

        titl = (TextView) findViewById(R.id.textView);
        quest = (TextView) findViewById(R.id.textView3);
        quest.setTextColor(Color.BLUE);
        titl.setTextColor(Color.RED);
        Intent intent = getIntent();
        String subject = intent.getExtras().getString("posttitle");
        String q = intent.getExtras().getString("postcontent");
        READ_COMMENTS_URL1 = intent.getExtras().getString("url1");
        COMMENT_URL = intent.getExtras().getString("url2");
        titl.setText(subject);
        quest.setText(q);
        postTitle = (subject);
        //pid = Integer.parseInt(postid);
        jsonParser = new JSONParser();

        mybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (commnt.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please give input correctly",Toast.LENGTH_LONG).show();
                }
                else if(isConnectingToInternet())
                {
                    new PostComments().execute();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please conect to internet",Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();

        // loading the comments via AsyncTask
        new LoadComments().execute();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        progressDialog.dismiss();
    }

    public void updateJSONdata()
    {
        //Toast.makeText(getApplicationContext(),"Update called",Toast.LENGTH_LONG).show();
        mCommentList = new ArrayList<HashMap<String, String>>();
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("posttitle", postTitle));
        JSONObject json = jsonParser.makeHttpRequest(
                READ_COMMENTS_URL1, "POST", params);
        //JSONParser jParser = new JSONParser();
        //JSONObject json = jParser.getJSONFromUrl(READ_COMMENTS_URL1);


        try
        {
            mComments = json.getJSONArray(TAG_COMMENTS);


            for (int i = 0; i < mComments.length(); i++)
            {

                JSONObject c = mComments.getJSONObject(i);
                String answer = c.getString(TAG_COMMENT);
                String commenter = c.getString(TAG_COMMENTER);

                HashMap<String, String> map = new HashMap<String, String>();


                map.put(TAG_COMMENT,answer);
                map.put(TAG_COMMENTER,commenter);

                mCommentList.add(map);
            }
        }

        catch (JSONException e)
        {

            e.printStackTrace();
        }
    }

    private void updateList()
    {
        ListAdapter adapter = new SimpleAdapter(this, mCommentList,
                R.layout.single_post, new String[] { TAG_COMMENT,
                TAG_COMMENTER }, new int[] { R.id.title,
                R.id.username });

        ListView lv = (ListView) findViewById(R.id.listView);

        lv.setAdapter(adapter);
    }

    public class LoadComments extends AsyncTask<Void, Void, Boolean>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = new ProgressDialog(AddComment.this);
            progressDialog.setMessage("Loading Comments...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }
        @Override
        protected Boolean doInBackground(Void... arg0) {
            updateJSONdata();
            return null;
        }
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            updateList();
        }
    }

    class PostComments extends AsyncTask<String, String, String>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(AddComment.this);
            progressDialog.setMessage("Posting Comment...");
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String post_comment = commnt.getText().toString();


            //We need to change this:
            SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(AddComment.this);
            String post_username = sp.getString("name", "anon");


            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("name", post_username));
                params.add(new BasicNameValuePair("comment", post_comment));
                params.add(new BasicNameValuePair("posttitle", postTitle));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        COMMENT_URL, "POST", params);

                // full json response
                Log.d("Post Comment attempt", json.toString());

                // json success element
                success = json.getInt(SUCCESS);
                if (success == 1) {
                    Log.d("Comment Added!", json.toString());
                    finish();
                    return json.getString(MESSAGE);
                }else{
                    Log.d("Comment Failure!", json.getString(MESSAGE));
                    return json.getString(MESSAGE);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;

        }

        protected void onPostExecute(String file_url) {
            // dismiss the dialog once product deleted
            progressDialog.dismiss();
            if (file_url != null){
                Toast.makeText(AddComment.this, file_url, Toast.LENGTH_LONG).show();
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
