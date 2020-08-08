package com.sandhu.manny.mylibrary.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.sandhu.manny.mylibrary.R;
import com.sandhu.manny.mylibrary.api.pojo.BookResourcePojo;
import com.sandhu.manny.mylibrary.api.GetBookService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddBookActivity extends AppCompatActivity {

    public static final String EXTRA_ISBN = "com.sandhu.manny.mylibrary.view.EXTRA_ISBN";
    public static final String EXTRA_TITLE = "com.sandhu.manny.mylibrary.view.EXTRA_TITLE";
    public static final String EXTRA_AUTHOR = "com.sandhu.manny.mylibrary.view.EXTRA_AUTHOR";
    public static final String EXTRA_GENRE = "com.sandhu.manny.mylibrary.view.EXTRA_GENRE";
    public static final String EXTRA_PAGES = "com.sandhu.manny.mylibrary.view.EXTRA_PAGES";
    public static final String EXTRA_PUBLISHED = "com.sandhu.manny.mylibrary.view.EXTRA_PUBLISHED";

    private Button addButton;
    private Button findButton;
    private EditText isbnEditText;
    private TextView titleTextView;
    private TextView authorTextView;
    private TextView genreTextView;
    private TextView pagesTextView;
    private TextView publishedTextView;
    private RequestQueue requestQueue;
    private String isbnValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        addButton = (Button) findViewById(R.id.addButton);
        findButton = (Button) findViewById(R.id.findButton);
        isbnEditText = (EditText) findViewById(R.id.isbnEditText);
        titleTextView = (TextView) findViewById(R.id.titleTextView);
        authorTextView = (TextView) findViewById(R.id.authorTextView);
        genreTextView = (TextView) findViewById(R.id.genreTextView);
        pagesTextView = (TextView) findViewById(R.id.pagesTextView);
        publishedTextView = (TextView) findViewById(R.id.publishedTextView);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Book");

        // steve jobs bio - 9781451648546
        // cracking the coding - 0984782850
        requestQueue = Volley.newRequestQueue(this);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isbnValue = isbnEditText.getText().toString(); // get the isbn on click

                if (isbnValue.length() < 10) {
                    Toast.makeText(AddBookActivity.this, "ISBN must be 10 or 13 digits", Toast.LENGTH_LONG).show();
                } else {

                    String url = "https://www.googleapis.com/books/v1/";
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(url)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    GetBookService getBookService = retrofit.create(GetBookService.class);
                    Call<BookResourcePojo> call = getBookService.getBookResource(isbnValue); //0984782850

                    call.enqueue(new Callback<BookResourcePojo>() {
                        @Override
                        public void onResponse(Call<BookResourcePojo> call, Response<BookResourcePojo> response) {

                            if(!response.isSuccessful()){
                                Toast.makeText(AddBookActivity.this, response.code(), Toast.LENGTH_SHORT).show();
                                return;
                            }

                            BookResourcePojo bookResource = (BookResourcePojo) response.body();
                            titleTextView.setText(bookResource.getItems().getBookInfo().getTitle());
                            publishedTextView.setText(bookResource.getItems().getPublishedData());


                        }

                        @Override
                        public void onFailure(Call<BookResourcePojo> call, Throwable t) {
                            Toast.makeText(AddBookActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }
        });
    }

    private void saveBook() {
        String isbn = isbnValue;
        String title = titleTextView.getText().toString();
        String author = authorTextView.getText().toString();
        String genre = genreTextView.getText().toString();
        String pages = pagesTextView.getText().toString();
        String published = publishedTextView.getText().toString();

        Intent data = new Intent();
        data.putExtra(EXTRA_ISBN, isbn);
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_AUTHOR, author);
        data.putExtra(EXTRA_GENRE, genre);
        data.putExtra(EXTRA_PAGES, pages);
        data.putExtra(EXTRA_PUBLISHED, published);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_book_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_book:
                saveBook();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
