package com.sandhu.manny.mylibrary.view;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.sandhu.manny.mylibrary.R;
import com.sandhu.manny.mylibrary._viewmodel.LibraryViewModel;
import com.sandhu.manny.mylibrary.model.Book;

import java.util.List;

public class LibraryActivity extends AppCompatActivity {

//    private RelativeLayout mLayout;
//    private RelativeLayout.LayoutParams layoutParams;
    private LibraryViewModel libraryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_library);

        libraryViewModel = new ViewModelProvider((ViewModelStoreOwner) this).get(LibraryViewModel.class);
        libraryViewModel.getAllBooks().observe((LifecycleOwner) this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                Toast.makeText(LibraryActivity.this, "MVVM!", Toast.LENGTH_SHORT).show();
            }
        });


    }

    // create views
//    public void createDataViews(String tableName) {

//        final Cursor resultSet = mydb.getAllData(tableName);
//
//        if(resultSet.getCount() == 0){
//            showMessage("Empty Library", "There are no books in your library.");
//        }else{
//
//            int id = 1;
//            while(resultSet.moveToNext()){
//
//                final Book book = new Book(Long.parseLong(resultSet.getString(0)),
//                        resultSet.getString(1),
//                        resultSet.getString(2),
//                        resultSet.getString(3),
//                        resultSet.getString(4),
//                        resultSet.getString(5),
//                        resultSet.getString(6));
//
//                layoutParams = new RelativeLayout.LayoutParams
//                        (RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//                mLayout = (RelativeLayout) findViewById(R.id.ListDataLayout);
//                TextView view = new TextView(this);
//                view.setId(id++);
//                view.setLayoutParams(layoutParams);
//                view.setClickable(true);
//                view.setText(book.getTitle() + "\n"
//                        + book.getAuthor() + "\n"
//                        + book.getGenre() + "\n"
//                        + book.getPages() + "\n"
//                        + book.getPublished() + "\n"
//                        + book.getShelfLabel() + "\n");
//                view.setClickable(true);

                // Alert builder
//                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
//
//                view.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        // inspect book, delete book, recommendations
//                        System.out.println("****INSPECTING**** -->" + view.getId());

                        // Alert dialogue
//                        builder.setTitle("Delete Book?");
//                        builder.setMessage(book.getTitle());
//
//                        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//
//                            public void onClick(DialogInterface dialog, int which) {
//                                //mydb.deleteData("book_data", book.getIsbn(), true);
//                                //mydb.close();
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
//                    }
//                    // End on click
//                });
//
//                int currentViewId = view.getId();
//                layoutParams.addRule(RelativeLayout.BELOW, currentViewId - 1);
//                mLayout.addView(view, layoutParams);
//
//            }
//            resultSet.close();
//        }
//    }


    private void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

}
