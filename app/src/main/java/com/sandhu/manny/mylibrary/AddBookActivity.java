package com.sandhu.manny.mylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AddBookActivity extends AppCompatActivity {

    DatabaseHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        //create database
        mydb = new DatabaseHelper(this);

        // wire up widgets
        Button addButton = (Button) findViewById(R.id.addButton);
        Button findButton = (Button) findViewById(R.id.findButton);
        final EditText isbnEditText = (EditText) findViewById(R.id.isbnEditText);
        final TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        final TextView authorTextView = (TextView) findViewById(R.id.authorTextView);
        final TextView genreTextView = (TextView) findViewById(R.id.genreTextView);
        final TextView publishedTextView = (TextView) findViewById(R.id.publishedTextView);

        //final String stevejobsbook = "9781451648546";
        final RequestQueue requestQueue = Volley.newRequestQueue(this);



        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String isbnValue = isbnEditText.getText().toString(); // get the isbn on click

                if(isbnValue.length() < 10) {
                    Toast.makeText(AddBookActivity.this, "ISBN must be 10 or 13 digits", Toast.LENGTH_LONG).show();
                }else {
                    String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbnValue;

                    //Do http request
                    final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                            null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            String[] result = jsonParser(response);

                            if(result[0] != null){
                                titleTextView.setText(result[0]);
                                authorTextView.setText(result[1]);
                                genreTextView.setText(result[2] + " : " + result[3]);
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

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //if no book selected
                if(titleTextView.getText().toString().equalsIgnoreCase("Title:")){
                    Toast.makeText(AddBookActivity.this, "Select a book", Toast.LENGTH_LONG).show();
                }else {
                    // add book to database
                    String[] genreAndPages = (genreTextView.getText().toString()).split(":");

                    boolean isInserted = mydb.insertData(isbnEditText.getText().toString(),
                            titleTextView.getText().toString(),
                            authorTextView.getText().toString(),
                            genreAndPages[0],
                            genreAndPages[1],
                            publishedTextView.getText().toString());

                    if(isInserted == true){
                        Toast.makeText(AddBookActivity.this, "Book added", Toast.LENGTH_LONG).show();
                        titleTextView.setText("");
                    }else{
                        if(titleTextView.getText().toString() == "null")
                        {
                            Toast.makeText(AddBookActivity.this, "Enter a valid ISBN", Toast.LENGTH_LONG).show();
                        }
                        Toast.makeText(AddBookActivity.this, "Book already exists", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }


    public String[] jsonParser(JSONObject response){
        String[] result = new String[5]; // volume information holder

        try {

            String totalItems = response.optString("totalItems");
            if(totalItems.equalsIgnoreCase("0")) {

                Toast.makeText(AddBookActivity.this, "Invalid ISBN", Toast.LENGTH_LONG).show();
            }else{

                JSONArray jsonArray = response.getJSONArray("items");
                for(int i=0; i< jsonArray.length(); ++i){

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
