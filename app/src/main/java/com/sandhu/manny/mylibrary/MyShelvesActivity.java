package com.sandhu.manny.mylibrary;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyShelvesActivity extends AppCompatActivity {

    DatabaseHelper mydb = new DatabaseHelper(this);
    private ArrayList<Shelf> shelfData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shelves);

        addShelfListener();
        createShelfDataViews();
    }


    private void createShelfDataViews(){
        final Cursor resultSet = mydb.getLabelData();

        if(resultSet.getCount() == 0){
            showMessage("No Shelves Exist", "Please create new shelves.");
        }else{

            LinearLayout scrollableLayout = (LinearLayout)findViewById(R.id.scrollableShelfListLayout);
            int id = 0;
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
                    (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams
                    (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

            // load db data

            while(resultSet.moveToNext()){

                final TextView tv = new TextView(this);
                tv.setText(resultSet.getString(0));
                ProgressBar pb = new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
                pb.setProgress(40);
                pb.setPadding(10, 10, 10, 50 );

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
                final Intent loadShelfIntent = new Intent(this, ShelfActivity.class);

                rl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // inspect book, delete book, recommendations
                        System.out.println("****INSPECTING**** -->" + rl.getId());
                        System.out.println(tv.getText().toString());

                        // go to shelf activity
                        loadShelfIntent.putExtra("Label", tv.getText().toString());
                        startActivity(loadShelfIntent);

//                        // Alert dialogue
//                        builder.setTitle("Delete Shelf?");
//                        builder.setMessage(tv.getText());
//
//                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//
//                            public void onClick(DialogInterface dialog, int which) {
//                                mydb.deleteLabelData(tv.getText().toString());
//                                mydb.close();
//                                dialog.dismiss();
//                                recreate();
//                            }
//                        });
//
//                        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
//
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Do nothing
//                                dialog.dismiss();
//                            }
//                        });
//                        AlertDialog alert = builder.create();
//                        alert.show();
//                        // End alert dialogue --------------------
                    }
                });

                //int currentViewId = rl.getId();
                //layoutParams3.addRule(RelativeLayout.BELOW, id - 1);
                scrollableLayout.addView(rl);
            }
        }

    }

    private void addShelfListener(){
        // Alert builder
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        input.setSingleLine(true);

        Button addShelfButton = (Button)findViewById(R.id.newShelfButton);
        addShelfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Alert dialogue
                builder.setTitle("New Shelf");
                builder.setMessage("Enter shelf name");
                builder.setView(input);

                builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        if(!input.getText().toString().equalsIgnoreCase("")){
                            boolean isInserted = mydb.insertLabelData(
                                    input.getText().toString(), "None");

                            if(isInserted == true){
                                Toast.makeText(MyShelvesActivity.this, "Shelf created!", Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(MyShelvesActivity.this, "Shelf already exists", Toast.LENGTH_LONG).show();
                            }
                            dialog.dismiss();
                            recreate();
                        }
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing
                        input.setText("");
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

    private void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
