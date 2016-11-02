package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/12/15.
 */

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ShowPreviousQuestion extends Activity {
    TextView quesno, question, option1, option2, option3, option4, correct;
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

    DatabaseHelper dbHelper;
    PreviousYearAdapter prevAdapter;
    ArrayList<Question> questions;
    Question q;
    int i = 0;
    Typeface banglafont;
    String str;
    ListView myListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prevqueslayout);
        questions = new ArrayList<Question>();
        jsonParser = new JSONParser();



        myListView = (ListView) findViewById(R.id.listV);


        Intent intent = getIntent();
        str = intent.getExtras().getString("subname");
        setTitle(str);
        //dbHelper = new DatabaseHelper(getApplicationContext());
        //questions = dbHelper.AllQuestions(str);

        //dbHelper.close();


        new LoadQuestion().execute();

    }

    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        super.onStop();
        finish();
    }

    public class LoadQuestion extends AsyncTask<Void, Void, Boolean>
    {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(ShowPreviousQuestion.this);
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

            return null;
        }
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            pDialog.dismiss();
            prevAdapter = new PreviousYearAdapter(ShowPreviousQuestion.this, questions);
            myListView.setAdapter(prevAdapter);
        }
    }
}
