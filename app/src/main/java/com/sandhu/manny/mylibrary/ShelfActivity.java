package com.sandhu.manny.mylibrary;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ShelfActivity extends AppCompatActivity {

    DatabaseHelper mydb = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shelf);

        // get label for db querying
        Intent intent = getIntent();
        String label = intent.getStringExtra("Label");
        System.out.println("***At: " + label + " shelf***");

        addBookButtonListener();
        createBookDataViews(label);
    }

    private void createBookDataViews(String table_name){

            Cursor resultSet = mydb.getAllData(table_name);

            if(resultSet.getCount() == 0){
                showMessage("This Shelf is empty", "Add some books");
            }else{

                System.out.println("***Retrieved data from: " + table_name + " ***");
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

    private void addBookButtonListener(){
        // Alert builder
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        final CharSequence[] options = {"Book Search", "From Library"};
        input.setSingleLine(true);

        Button addBookButton = (Button)findViewById(R.id.addBookButton);
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Alert dialogue
                builder.setTitle("Add Book to Shelf");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

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
