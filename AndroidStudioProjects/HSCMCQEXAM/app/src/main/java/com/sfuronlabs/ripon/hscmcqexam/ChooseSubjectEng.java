package com.sfuronlabs.ripon.hscmcqexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

/**
 * Created by Ripon on 6/27/15.
 */
public class ChooseSubjectEng extends Fragment {
    Integer[] imageId;
    GridView list;
    String[] web = {"PHYSICS1","PHYSICS2","CHEMISTRY1","CHEMISTRY2","MATHEMATICS1","MATHEMATICS2"};
    public ChooseSubjectEng()
    {
        Integer[] arrayOfInteger = new Integer[6];
        arrayOfInteger[0] = Integer.valueOf(R.drawable.physics);
        arrayOfInteger[1] = Integer.valueOf(R.drawable.physics2);
        arrayOfInteger[2] = Integer.valueOf(R.drawable.chemistry);
        arrayOfInteger[3] = Integer.valueOf(R.drawable.chemistry2);
        arrayOfInteger[4] = Integer.valueOf(R.drawable.math);
        arrayOfInteger[5] = Integer.valueOf(R.drawable.math2);
        this.imageId = arrayOfInteger;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        GridAdapter gridAdapter = new GridAdapter(getActivity(),
                this.web, this.imageId, "font/Amaranth-Bold.ttf");
        View rootView = inflater.inflate(R.layout.choosesubeng, container, false);

        list = (GridView) rootView.findViewById(R.id.gridvieweng);

        list.setAdapter(gridAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position)
                {
                    default:
                        return;
                    case 0:
                        Intent intent = new Intent(
                                "android.intent.action.STARTINGPOINT");
                        intent.putExtra("name", "Physics1");
                        intent.putExtra("Standard", "MEDICAL");
                        startActivity(intent);
                        return;
                    case 1:
                        Intent intent1 = new Intent(
                                "android.intent.action.STARTINGPOINT");
                        intent1.putExtra("name", "Physics2");
                        intent1.putExtra("Standard", "MEDICAL");
                        startActivity(intent1);
                        return;
                    case 2:
                        Intent intent2 = new Intent(
                                "android.intent.action.STARTINGPOINT");
                        intent2.putExtra("name", "Chemistry1");
                        intent2.putExtra("Standard", "MEDICAL");
                        startActivity(intent2);
                        return;
                    case 3:
                        Intent intent3 = new Intent(
                                "android.intent.action.STARTINGPOINT");
                        intent3.putExtra("name", "Chemistry2");
                        intent3.putExtra("Standard", "MEDICAL");
                        startActivity(intent3);
                        return;

                    case 4:
                        Intent intent6 = new Intent(
                                "android.intent.action.STARTINGPOINT");
                        intent6.putExtra("name", "Mathematics1");
                        intent6.putExtra("Standard", "MEDICAL");
                        startActivity(intent6);
                        return;
                    case 5:
                        Intent intent7 = new Intent(
                                "android.intent.action.STARTINGPOINT");
                        intent7.putExtra("name", "Mathematics2");
                        intent7.putExtra("Standard", "MEDICAL");
                        startActivity(intent7);
                        return;
                }
            }
        });

        return rootView;
    }
}
