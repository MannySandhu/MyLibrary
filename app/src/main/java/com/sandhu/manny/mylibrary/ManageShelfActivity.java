package com.sandhu.manny.mylibrary;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
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

/*
    Class manages shelf related functions
    -add book to shelf
    -delete book from shelf
 */
public class ManageShelfActivity extends AppCompatActivity {

    DatabaseHelper mydb = new DatabaseHelper(this);
    private static final String MASTER_TABLE = "book_data";
    private String label = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);

        // get label for db querying
        Intent intent = getIntent();
        label = intent.getStringExtra("Label");
        System.out.println("***At: " + label + " shelf***");

        addBookButtonListener();
        createBookDataViews(MASTER_TABLE);
    }

    private void createBookDataViews(String tableName){

            final Cursor resultSet = mydb.getAllData(tableName);

            if(resultSet.getCount() == 0)
            {


            }else{

                System.out.println("***Retrieved data from: " + tableName + " ***");
                LinearLayout scrollableLayout = (LinearLayout)findViewById(R.id.scrollableVolumeListLayout);
                int id = 0;
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams
                        (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams
                        (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

                // load db data
                while(resultSet.moveToNext()){

                    System.out.println(resultSet.getString(6) + " AND " + label);

                    // if has shelf label in column
                    if(resultSet.getString(6) == label)
                    {
                        final TextView tv = new TextView(this);
                        tv.setText(resultSet.getString(1));
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
                        final AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                        final Intent loadShelfIntent = new Intent(this, ManageShelfActivity.class);

                        rl.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                // inspect book, delete book, recommendations
                                System.out.println("****INSPECTING**** -->" + rl.getId());
                                System.out.println(tv.getText().toString());

//                        // go to shelf activity
//                        loadShelfIntent.putExtra("Label", tv.getText().toString());
//                        startActivity(loadShelfIntent);

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
    }

    private void addBookButtonListener(){
        // Alert builder
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        final CharSequence[] volumeList;
        input.setSingleLine(true);

        Button addBookButton = (Button)findViewById(R.id.addBookButton);
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // fetch volumes if available
                final Cursor resultSet = mydb.getAllData("book_data");
                ArrayList<String> volumeList = new ArrayList<>();

                if (resultSet.getCount() == 0) {

                    // If there are no books - prompt to add them by alert dialogue
                    builder.setTitle("There are no books in your library");
                    builder.setMessage("Add some now?");
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing
                            input.setText("");
                            dialog.dismiss();
                        }
                    });
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Go to add book activity
                            navigateToAddBookActivity();
                        }
                    });

                } else {
                    // If there are books then list them for selection
                    // Get the data
                    String volumeData = "";
                    String[] list;
                    while (resultSet.moveToNext()) {
                        volumeData = resultSet.getString(1) + "\n"
                                + resultSet.getString(2) + " "
                                + resultSet.getString(5);
                        volumeList.add(volumeData);
                    }
                    volumeData = "";


                    //create checkable item list
                    list = new String[volumeList.size()];
                    for (int i = 0; i < volumeList.size(); i++) {
                        list[i] = volumeList.get(i);
                    }

                    final boolean[] state = new boolean[list.length];
                    builder.setTitle("Select the books you want to add");
                    builder.setMultiChoiceItems(list, state, new DialogInterface.OnMultiChoiceClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int which, boolean isChecked) {
                            state[which] = isChecked;
                        }
                    });

                    builder.setPositiveButton("Add", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            boolean isUpdated = false;
                            resultSet.moveToFirst();
                            int count = resultSet.getCount();
                            for(int i=0; i<count; ++i){
                                if(state[i] == true){
                                    // Then assign shelf label
                                    isUpdated = mydb.updateLabelData(MASTER_TABLE, resultSet.getString(0), label);
                                }
                                resultSet.moveToNext();
                            }


                            if (isUpdated == true) {
                                Toast.makeText(ManageShelfActivity.this, "Added to Shelf",
                                        Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(ManageShelfActivity.this, "Error",
                                        Toast.LENGTH_LONG).show();
                            }
                        }
                    });

                    builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do nothing
                            input.setText("");
                            dialog.dismiss();
                        }
                    });
                    if (input.getParent() != null) {
                        ((ViewGroup) input.getParent()).removeView(input);
                    }
                    AlertDialog alert = builder.create();
                    alert.show();
                    // End alert dialogue --------------------
                }
            }
        });
    }

    private void navigateToAddBookActivity(){
        Intent addBookIntent = new Intent(this, AddBookActivity.class);
        startActivity(addBookIntent);
    }

    private void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
