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

        int id=1;
        final Cursor resultSet = mydb.getAllData();

        if(resultSet.getCount() == 0){
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

                layoutParams = new RelativeLayout.LayoutParams
                        (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                mLayout = (RelativeLayout) findViewById(R.id.ListDataLayout);

                TextView view = new TextView(this);
                view.setId(id);
                view.setLayoutParams(layoutParams);
                view.setClickable(true);
                view.setText(buffer.toString());
                view.setClickable(true);

                String title = resultSet.getString(1);
                String author = resultSet.getString(2);
                String genre = resultSet.getString(3);
                String pages = resultSet.getString(4);
                String published = resultSet.getString(5);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // inspect book, delete book, recommendations
                    }
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
