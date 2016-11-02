package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Guideline extends ListActivity {
    private ProgressDialog pDialog;

    TextView Guideline;
    private String READ_NOTICE_URL = "http://vpn.gd/webservice/readnotice.php";
    private static final String TAG_VERSITY = "versity";
    private static final String TAG_NOTICETITLE = "title";
    private static final String TAG_NOTICE_ID = "noticeid";
    private static final String TAG_NOTICEDETAILS = "details";
    private static final String TAG_NOTICEDATE = "date";

    private static final String TAG_POST = "posts";

    private JSONArray mComments = null;
    // manages all of our comments in a list.
    private ArrayList<HashMap<String, String>> mCommentList;

    //private static final String TAG_MESSAGE = "question";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guideline);
        setTitle("Admission Notice Board");
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        // loading the comments via AsyncTask
        new LoadNotices().execute();
    }

    public void updateJSONdata() {

        // Instantiate the arraylist to contain all the JSON data.
        // we are going to use a bunch of key-value pairs, referring
        // to the json element name, and the content, for example,
        // message it the tag, and "I'm awesome" as the content..

        mCommentList = new ArrayList<HashMap<String, String>>();

        // Bro, it's time to power up the J parser
        JSONParser jParser = new JSONParser();
        // Feed the beast our comments url, and it spits us
        // back a JSON object. Boo-yeah Jerome.
        JSONObject json = jParser.getJSONFromUrl(READ_NOTICE_URL);

        // when parsing JSON stuff, we should probably
        // try to catch any exceptions:
        try {

            // I know I said we would check if "Posts were Avail." (success==1)
            // before we tried to read the individual posts, but I lied...
            // mComments will tell us how many "posts" or comments are
            // available
            mComments = json.getJSONArray(TAG_POST);

            // looping through all posts according to the json object returned
            for (int i = 0; i < mComments.length(); i++) {
                JSONObject c = mComments.getJSONObject(i);

                // gets the content of each tag
                String title = c.getString(TAG_NOTICE_ID);
                String content = c.getString(TAG_VERSITY);
                String username = c.getString(TAG_NOTICETITLE);
                String postid = c.getString(TAG_NOTICEDETAILS);
                String date = c.getString(TAG_NOTICEDATE);

                // creating new HashMap
                HashMap<String, String> map = new HashMap<String, String>();

                map.put(TAG_NOTICE_ID, title);
                map.put(TAG_VERSITY, content);
                map.put(TAG_NOTICETITLE, username);
                map.put(TAG_NOTICEDETAILS,postid);
                map.put(TAG_NOTICEDATE,date);

                // adding HashList to ArrayList
                mCommentList.add(map);

                // annndddd, our JSON data is up to date same with our array
                // list
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts the parsed data into the listview.
     */
    private void updateList() {
        // For a ListActivity we need to set the List Adapter, and in order to do
        //that, we need to create a ListAdapter.  This SimpleAdapter,
        //will utilize our updated Hashmapped ArrayList,
        //use our single_post xml template for each item in our list,
        //and place the appropriate info from the list to the
        //correct GUI id.  Order is important here.
        ListAdapter adapter = new SimpleAdapter(this, mCommentList,
                R.layout.single_post, new String[] {"", TAG_VERSITY,
                TAG_NOTICETITLE }, new int[] {R.id.postedby, R.id.title,
                R.id.username });

        // I shouldn't have to comment on this one:
        setListAdapter(adapter);

        // Optional: when the user clicks a list item we
        //could do something.  However, we will choose
        //to do nothing...
        ListView lv = getListView();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // This method is triggered if an item is click within our
                // list. For our example we won't be using this, but
                // it is useful to know in real life applications.
                Intent i = new Intent(Guideline.this,NoticeDetails.class);
                //Toast.makeText(getApplicationContext(),mCommentList.get(position).get(TAG_POST_ID),Toast.LENGTH_SHORT).show();
                i.putExtra("versityname",mCommentList.get(position).get(TAG_VERSITY));
                i.putExtra("noticetitle",mCommentList.get(position).get(TAG_NOTICETITLE));
                i.putExtra("noticedetails",mCommentList.get(position).get(TAG_NOTICEDETAILS));
                i.putExtra("noticedate",mCommentList.get(position).get(TAG_NOTICEDATE));
                startActivity(i);

            }
        });
    }


    public class LoadNotices extends AsyncTask<Void, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Guideline.this);
            pDialog.setMessage("Loading Posts...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected Boolean doInBackground(Void... arg0) {
            updateJSONdata();
            return null;

        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            updateList();
        }
    }

}
