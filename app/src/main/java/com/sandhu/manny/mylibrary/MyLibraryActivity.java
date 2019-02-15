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
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MyLibraryActivity extends AppCompatActivity {

    DatabaseHelper mydb = new DatabaseHelper(this);
    private RelativeLayout mLayout;
    private RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_library);

        createDataViews();

    }

    // create views
    public void createDataViews() {

        final Cursor resultSet = mydb.getAllData();

        if(resultSet.getCount() == 0){
            showMessage("Empty Library", "There are no books in your library.");
        }else{

            int id = 1;
            while(resultSet.moveToNext()){

                final Volume volume = new Volume(resultSet.getString(0),
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5));

                layoutParams = new RelativeLayout.LayoutParams
                        (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

                mLayout = (RelativeLayout) findViewById(R.id.ListDataLayout);
                TextView view = new TextView(this);
                view.setId(id++);
                view.setLayoutParams(layoutParams);
                view.setClickable(true);
                view.setText(volume.getTitle() + "\n"
                        + volume.getAuthor() + "\n"
                        + volume.getGenre() + "\n"
                        + volume.getPages() + "\n"
                        + volume.getPublished() + "\n");
                view.setClickable(true);

                // Alert builder
                final AlertDialog.Builder builder = new AlertDialog.Builder(this);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // inspect book, delete book, recommendations
                        System.out.println("****INSPECTING**** -->" + view.getId());

                        // Alert dialogue
                        builder.setTitle("Delete Book?");
                        builder.setMessage(volume.getTitle());

                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                mydb.deleteData(volume.getIsbn(), true);
                                mydb.close();
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
                    // End on click
                });

                int currentViewId = view.getId();
                layoutParams.addRule(RelativeLayout.BELOW, currentViewId - 1);
                mLayout.addView(view, layoutParams);

            }
        }
    }


    private void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}
