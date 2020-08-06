package com.sandhu.manny.mylibrary.view;

import android.content.Context;
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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.sandhu.manny.mylibrary.R;
import com.sandhu.manny.mylibrary.db.AppDatabase;
import com.sandhu.manny.mylibrary.model.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        // stevejobsbook = "9781451648546";
        requestQueue = Volley.newRequestQueue(this);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isbnValue = isbnEditText.getText().toString(); // get the isbn on click

                if (isbnValue.length() < 10) {
                    Toast.makeText(AddBookActivity.this, "ISBN must be 10 or 13 digits", Toast.LENGTH_LONG).show();
                } else {

                    String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbnValue;
                    //Do http request
                    final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                            null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            String[] result = jsonParser(response);
                            if (result[0] != null) {
                                titleTextView.setText(result[0]);
                                authorTextView.setText(result[1]);
                                genreTextView.setText(result[2]);
                                pagesTextView.setText(result[3]);
                                publishedTextView.setText(result[4]);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });
                    requestQueue.add(jsObjRequest);
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
        String published = isbnEditText.getText().toString();

//        String title = "some title";
//        String genre = "some genre";
//        String pages = "345";
//        String published = "date";

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

    public String[] jsonParser(JSONObject response) {
        String[] result = new String[5]; // volume information holder
        try {
            String totalItems = response.optString("totalItems");
            if (totalItems.equalsIgnoreCase("0")) {

                Toast.makeText(AddBookActivity.this, "Invalid ISBN", Toast.LENGTH_LONG).show();
            } else {
                JSONArray jsonArray = response.getJSONArray("items");
                for (int i = 0; i < jsonArray.length(); ++i) {
                    JSONObject items = jsonArray.getJSONObject(i);

                    // get title info
                    String title = items.getJSONObject("volumeInfo").optString("title");
                    String subtitle = items.getJSONObject("volumeInfo").optString("subtitle");
                    result[0] = title + " : " + subtitle;

                    // get author info
                    result[1] = items.getJSONObject("volumeInfo").optString("authors");

                    // get category and page count info
                    result[2] = items.getJSONObject("volumeInfo").optString("categories");
                    result[3] = items.getJSONObject("volumeInfo").optString("pageCount");

                    // get published date
                    String publishedDate = items.getJSONObject("volumeInfo").optString("publishedDate");
                    result[4] = publishedDate;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

}
