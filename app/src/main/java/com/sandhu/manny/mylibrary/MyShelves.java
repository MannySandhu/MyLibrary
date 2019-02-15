package com.sandhu.manny.mylibrary;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyShelves extends AppCompatActivity {

    DatabaseHelper mydb = new DatabaseHelper(this);
    private ArrayList<Shelf> shelfData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shelves);

        Button addShelfButton = (Button)findViewById(R.id.newShelfButton);

//        ArrayList<String> data = new ArrayList<>();
//        data.add("Autobiography");
//        data.add("Fantasy");
//        data.add("Summer Reading");

        LinearLayout scrollableLayout = (LinearLayout)findViewById(R.id.scrollableShelfListLayout);
        int id = 0;
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams
                (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        // load db data

        for(int i=0; i<data.size(); i++){ // up to result set size

            final TextView tv = new TextView(this);
            tv.setText(data.get(i));
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


            // Alert builder
            final AlertDialog.Builder builder = new AlertDialog.Builder(this);

            rl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // inspect book, delete book, recommendations
                    System.out.println("****INSPECTING**** -->" + rl.getId());


                    // Alert dialogue
                    builder.setTitle("Delete Shelf?");
                    builder.setMessage(tv.getText());

                    builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing but close the dialog
                           // mydb.deleteData(tv.getText().toString(), false);
                            //mydb.close();
                            dialog.dismiss();
                            recreate();
                        }
                    });

                    builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Do nothing
                            dialog.dismiss();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                    // End alert dialogue --------------------
                }
            });

            //int currentViewId = rl.getId();
            //layoutParams3.addRule(RelativeLayout.BELOW, id - 1);
            scrollableLayout.addView(rl);
        }


        // Alert builder
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);

        addShelfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Alert dialogue
                builder.setTitle("New Shelf");
                builder.setMessage("Enter shelf name");
                builder.setView(input);
                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        if(input != null){
                            // add category to db and recreate
                            recreate();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        input.setText(null);
                        dialog.dismiss();
                    }
                });
                if (input.getParent() != null){
                    ((ViewGroup)input.getParent()).removeView(input);
                }
                AlertDialog alert = builder.create();
                alert.show();
                // End alert dialogue --------------------
            }
        });
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

    private void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
