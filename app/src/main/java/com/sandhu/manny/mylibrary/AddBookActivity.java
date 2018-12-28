package com.sandhu.manny.mylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
        final EditText isbnEditText = (EditText) findViewById(R.id.isbnEditText);
        final TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        final TextView authorTextView = (TextView) findViewById(R.id.authorTextView);
        final TextView editionTextView = (TextView) findViewById(R.id.editionTextView);
        final TextView publishedTextView = (TextView) findViewById(R.id.publishedTextView);

        //final String stevejobsbook = "9781451648546";
        final RequestQueue requestQueue = Volley.newRequestQueue(this);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String isbnValue = isbnEditText.getText().toString(); // get the isbn on click
                String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + isbnValue;

                //Do http request
                final JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String[] result = jsonParser(response);
                        titleTextView.setText(result[0]);
                        authorTextView.setText(result[1]);
                        editionTextView.setText(result[2]);
                        publishedTextView.setText(result[3]);

                        //findViewById(R.id.addButton).setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

                requestQueue.add(jsObjRequest);
            }
        });
    }

    public String[] jsonParser(JSONObject response){
        String[] result = new String[4]; // volume information holder

        try {
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
                String category = items.getJSONObject("volumeInfo").optString("categories");
                String pageCount = items.getJSONObject("volumeInfo").optString("pageCount");
                result[2] = category + " : " + pageCount + "p";

                // get published date
                String publishedDate = items.getJSONObject("volumeInfo").optString("publishedDate");
                result[3] = publishedDate;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

}
