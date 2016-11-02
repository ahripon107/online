package com.sfuronlabs.ripon.hscmcqexam;

/**
 * Created by Ripon on 5/22/15.
 */
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Register extends Activity implements OnClickListener{

    private EditText user, pass,nam,clgname;
    private TextView linktologin;
    private Button  mRegister;
    private Spinner stdntormentor;
    String [] whoami = {"Student","Mentor"};
    ShowChapter adpter;
    private TextView college;

    // Progress Dialog
    private ProgressDialog pDialog;

    // JSON parser class
    JSONParser jsonParser = new JSONParser();

    //php register script

    //localhost :
    //testing on your device
    //put your local ip instead,  on windows, run CMD > ipconfig
    //or in mac's terminal type ifconfig and look for the ip under en0 or en1
    // private static final String REGISTER_URL = "http://xxx.xxx.x.x:1234/webservice/register.php";

    //testing on Emulator:
    private static final String REGISTER_URL = "http://vpn.gd/webservice/register.php";

    //testing from a real server:
    //private static final String REGISTER_URL = "http://www.mybringback.com/webservice/register.php";

    //ids
    private static final String TAG_SUCCESS = "success";
    private static final String TAG_MESSAGE = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        linktologin = (TextView) findViewById(R.id.link_to_login);

        college = (TextView) findViewById(R.id.collegeName);
        user = (EditText)findViewById(R.id.username);
        pass = (EditText)findViewById(R.id.password);
        nam = (EditText) findViewById(R.id.etName);
        clgname = (EditText) findViewById(R.id.etCollegeName);
        stdntormentor = (Spinner) findViewById(R.id.spinner);

        adpter = new ShowChapter(Register.this,R.layout.chapterlayout,whoami);
        stdntormentor.setAdapter(adpter);

        stdntormentor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position==1)
                {
                    college.setText("College Name (Optional)");

                }
                if (position==0)
                {
                    college.setText("College Name ");

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        mRegister = (Button)findViewById(R.id.register);
        mRegister.setOnClickListener(this);
        linktologin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId())
        {
            case R.id.register:
                String username = user.getText().toString();
                String password = pass.getText().toString();
                String name = nam.getText().toString();
                String clg = clgname.getText().toString();
                String type = stdntormentor.getSelectedItem().toString();

                if (type.equals("Student") && ((username.equals("")) ||(password.equals("")) || (name.equals("")) || (clg.equals("")) ))
                {
                    Toast.makeText(getApplicationContext(),"Please give input correctly",Toast.LENGTH_LONG).show();
                }
                else if (type.equals("Mentor") && ((username.equals("")) ||(password.equals("")) || (name.equals("")) ))
                {
                    Toast.makeText(getApplicationContext(),"Please give input correctly",Toast.LENGTH_LONG).show();
                }
                else if (!isConnectingToInternet())
                {
                    Toast.makeText(getApplicationContext(),"Please conect to internet",Toast.LENGTH_LONG).show();
                }
                else
                {
                    new CreateUser().execute();
                }
                break;
            case R.id.link_to_login:
                finish();
                break;
            default:
                break;
        }



    }

    class CreateUser extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(Register.this);
            pDialog.setMessage("Creating User...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(true);
            pDialog.show();
        }

        @Override
        protected String doInBackground(String... args) {
            // TODO Auto-generated method stub
            // Check for success tag
            int success;
            String username = user.getText().toString();
            String password = pass.getText().toString();
            String name = nam.getText().toString();
            String clg = clgname.getText().toString();
            String type = stdntormentor.getSelectedItem().toString();
            try {
                // Building Parameters
                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("college",clg));
                params.add(new BasicNameValuePair("type",type));
                params.add(new BasicNameValuePair("name",name));
                params.add(new BasicNameValuePair("username", username));
                params.add(new BasicNameValuePair("password", password));

                Log.d("request!", "starting");

                //Posting user data to script
                JSONObject json = jsonParser.makeHttpRequest(
                        REGISTER_URL, "POST", params);

                // full json response
                Log.d("Registering attempt", json.toString());

                // json success element
                success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    Log.d("User Created!", json.toString());
                    finish();
                    return json.getString(TAG_MESSAGE);
                }else{
                    Log.d("Registering Failure!", json.getString(TAG_MESSAGE));
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
                Toast.makeText(Register.this, file_url, Toast.LENGTH_LONG).show();
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
