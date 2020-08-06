package com.sandhu.manny.mylibrary.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.sandhu.manny.mylibrary.R;

/*
    main activity
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addBookButton = (Button)findViewById(R.id.AddBookButton);
        Button myLibraryButton = (Button)findViewById(R.id.MyLibraryButton);
        Button myReadingListsButton = (Button)findViewById(R.id.ReadingListsButton);

        final Intent addBookIntent = new Intent(this, AddBookActivity.class);
        final Intent myLibraryIntent = new Intent(this, LibraryActivity.class);
//        final Intent myShelvesIntent = new Intent(this, ManageShelvesActivity.class);

        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(addBookIntent);
            }
        });

        myLibraryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(myLibraryIntent);
            }
        });

        myReadingListsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(myShelvesIntent);
            }
        });
    }
}
