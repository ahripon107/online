package com.sfuronlabs.ripon.hscmcqexam;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by Ripon on 5/23/15.
 */
public class Forum extends Activity{

    Integer[] imageId;
    ListView list;
    String[] web = {"PHYSICS", "CHEMISTRY", "BIOLOGY",
            "ICT","MATH"};

    public Forum() {
        Integer[] arrayOfInteger = new Integer[5];
        arrayOfInteger[0] = Integer.valueOf(R.drawable.physics);
        arrayOfInteger[1] = Integer.valueOf(R.drawable.chemistry);
        // arrayOfInteger[2] = Integer.valueOf(R.drawable.practice);
        arrayOfInteger[2] = Integer.valueOf(R.drawable.biology);
        arrayOfInteger[3] = Integer.valueOf(R.drawable.ict);
        arrayOfInteger[4] = Integer.valueOf(R.drawable.math);
        this.imageId = arrayOfInteger;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forum);

        OptionsListStyle localOptionsListStyle = new OptionsListStyle(this,
                this.web, this.imageId, "font/Amaranth-Bold.ttf");

        list = (ListView) findViewById(R.id.lvForum);
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
                        Intent intent = new Intent(Forum.this,ReadPosts.class);
                        intent.putExtra("selUrl","http://vpn.gd/webservice/comments.php");
                        intent.putExtra("url1","http://vpn.gd/webservice/readcommenttopost.php");
                        intent.putExtra("url2","http://vpn.gd/webservice/addcommenttopost.php");
                        intent.putExtra("url3","http://vpn.gd/webservice/addcomment.php");
                        startActivity(intent);
                        return;
                    case 1:
                        Intent intent1 = new Intent(Forum.this,ReadPosts.class);
                        intent1.putExtra("selUrl","http://vpn.gd/webservice/chemistrypost.php");
                        intent1.putExtra("url1","http://vpn.gd/webservice/readchemistrypostcomment.php");
                        intent1.putExtra("url2","http://vpn.gd/webservice/addchemistrypostcomment.php");
                        intent1.putExtra("url3","http://vpn.gd/webservice/addchemistrypost.php");
                        startActivity(intent1);
                        return;
                    case 2:
                        Intent intent2 = new Intent(Forum.this,ReadPosts.class);
                        intent2.putExtra("selUrl","http://vpn.gd/webservice/biologypost.php");
                        intent2.putExtra("url1","http://vpn.gd/webservice/readbiologypostcomment.php");
                        intent2.putExtra("url2","http://vpn.gd/webservice/addbiologypostcomment.php");
                        intent2.putExtra("url3","http://vpn.gd/webservice/addbiologypost.php");
                        startActivity(intent2);
                        return;
                    case 3:
                        Intent intent3 = new Intent(Forum.this,ReadPosts.class);
                        intent3.putExtra("selUrl","http://vpn.gd/webservice/ictpost.php");
                        intent3.putExtra("url1","http://vpn.gd/webservice/readictpostcomment.php");
                        intent3.putExtra("url2","http://vpn.gd/webservice/addictpostcomment.php");
                        intent3.putExtra("url3","http://vpn.gd/webservice/addictpost.php");
                        startActivity(intent3);
                        return;
                    case 4:
                        Intent intent4 = new Intent(Forum.this,ReadPosts.class);
                        intent4.putExtra("selUrl","http://vpn.gd/webservice/mathpost.php");
                        intent4.putExtra("url1","http://vpn.gd/webservice/readmathpostcomment.php");
                        intent4.putExtra("url2","http://vpn.gd/webservice/addmathpostcomment.php");
                        intent4.putExtra("url3","http://vpn.gd/webservice/addmathpost.php");
                        startActivity(intent4);
                        return;
                }

            }
        });
    }
}
