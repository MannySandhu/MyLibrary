package com.sandhu.manny.mylibrary;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyShelves extends AppCompatActivity {

    private ArrayList<Shelf> shelfData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shelves);

        String [] data = {"Autobiographys", "Summer Reading", "School Books"};

        LinearLayout scrollableLayout = (LinearLayout)findViewById(R.id.scrollableShelfListLayout);
        int id = 0;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);


        for(int i=0; i<data.length; i++){

            TextView tv = new TextView(this);
            tv.setText(data[i]);
            ProgressBar pb = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
            pb.setProgress(i*40);
            pb.setPadding(10, 10, 10, 50 );
            //pb.setId(id++);
            //pb.setLayoutParams(layoutParams);
            //pb.setClickable(true);

            final RelativeLayout rl = new RelativeLayout(this);
            tv.setLayoutParams(layoutParams);
            pb.setLayoutParams(layoutParams2);

            rl.addView(tv);
            rl.addView(pb);
            rl.setId(id++);
            rl.setClickable(true);
            rl.setPadding(10, 10, 10, 50);

            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // inspect book, delete book, recommendations
                    System.out.println("****INSPECTING**** -->" + rl.getId());
                }
            });

            //int currentViewId = rl.getId();
            //layoutParams3.addRule(RelativeLayout.BELOW, id - 1);
            scrollableLayout.addView(rl);
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
