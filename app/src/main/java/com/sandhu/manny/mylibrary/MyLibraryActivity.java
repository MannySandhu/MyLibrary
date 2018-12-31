package com.sandhu.manny.mylibrary;

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

public class MyLibraryActivity extends AppCompatActivity {

    DatabaseHelper mydb = new DatabaseHelper(this);
    private RelativeLayout mLayout;
    private RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_library);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



    }

    // create views
    private void createDataViews() {

        Cursor resultSet = mydb.getAllData();
        int librarySize = resultSet.getCount();

        if(librarySize == 0){
            showMessage("Empty Library", "There are no books in your library.");
        }else{
            StringBuffer buffer = new StringBuffer();
            while(resultSet.moveToNext()){
                buffer.append("ISBN :" + resultSet.getString(0) +"\n" );
                buffer.append("Title :" + resultSet.getString(1) +"\n" );
                buffer.append("Author :" + resultSet.getString(2) +"\n" );
                buffer.append("Genre :" + resultSet.getString(3) +"\n" );
                buffer.append("Pages :" + resultSet.getString(4) +"\n" );
                buffer.append("Published :" + resultSet.getString(5) +"\n\n" );
            }
            showMessage("Books found", buffer.toString());
        }

        for (int i = 1; i < librarySize + 1; ++i) {
            layoutParams = new RelativeLayout.LayoutParams
                    (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            mLayout = (RelativeLayout) findViewById(R.id.ListDataLayout);

            TextView view = new TextView(this);
            view.setId(i);
            view.setText(i + " view");
            view.setLayoutParams(layoutParams);

            int id = view.getId();
            layoutParams.addRule(RelativeLayout.BELOW, id - 1);
            mLayout.addView(view, layoutParams);
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
