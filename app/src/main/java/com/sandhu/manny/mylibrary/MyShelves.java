package com.sandhu.manny.mylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class MyShelves extends AppCompatActivity {

    private ArrayList<Shelf> shelfData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shelves);

        int id = 0;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        for(int i=0; i<shelfData.size(); i++){
            ProgressBar pb = new ProgressBar(this);
            pb.setId(id++);
            pb.setLayoutParams(layoutParams);
            pb.setClickable(true);

        }


    }


    private void getShelfData(){

        // dummy data
        Shelf s1 = new Shelf(null);
        Shelf s2 = new Shelf(null);
        Shelf s3 = new Shelf(null);

        shelfData.add(s1);
        shelfData.add(s2);
        shelfData.add(s3);
    }


}
