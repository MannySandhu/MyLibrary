package com.sandhu.manny.mylibrary.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.sandhu.manny.mylibrary.R;
import com.sandhu.manny.mylibrary._viewmodel.LibraryViewModel;
import com.sandhu.manny.mylibrary.adapter.BookAdapter;
import com.sandhu.manny.mylibrary.model.Book;

import java.util.List;

public class LibraryActivity extends AppCompatActivity {

    public static final int ADD_NOTE_REQUEST = 1;
//    private RelativeLayout mLayout;
//    private RelativeLayout.LayoutParams layoutParams;
    private LibraryViewModel libraryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);

        FloatingActionButton buttonAddBook = findViewById(R.id.button_add_book);
        buttonAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LibraryActivity.this, AddBookActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final BookAdapter bookAdapter = new BookAdapter();
        recyclerView.setAdapter(bookAdapter);

        libraryViewModel = new ViewModelProvider(this, new LibraryViewModel(this.getApplication()))
                .get(LibraryViewModel.class);

        libraryViewModel.getAllBooks().observe(this, new Observer<List<Book>>() {
            @Override
            public void onChanged(List<Book> books) {
                bookAdapter.setBooks(books);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            String isbn = data.getStringExtra(AddBookActivity.EXTRA_ISBN);
            String title = data.getStringExtra(AddBookActivity.EXTRA_TITLE);
            String author = data.getStringExtra(AddBookActivity.EXTRA_AUTHOR);
            String genre = data.getStringExtra(AddBookActivity.EXTRA_GENRE);
            String pages = data.getStringExtra(AddBookActivity.EXTRA_PAGES);
            String published = data.getStringExtra(AddBookActivity.EXTRA_PUBLISHED);

            Book book = new Book(Long.parseLong(isbn), title, author,
                    genre, pages, published, "none");

            libraryViewModel.insertBook(book);

            Toast.makeText(this, "Book saved", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Book not saved", Toast.LENGTH_SHORT).show();
        }
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


//    private void showMessage(String title, String message){
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setCancelable(true);
//        builder.setTitle(title);
//        builder.setMessage(message);
//        builder.show();
//    }

}
