package com.sandhu.manny.mylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        // wire up widgets
        Button addButton = (Button) findViewById(R.id.addButton);
        final EditText isbnEditText = (EditText) findViewById(R.id.isbnEditText);
        final EditText detailsEditText = (EditText) findViewById(R.id.detailsEditText);
        String isbnValue = "";

        // example steve jobs book
        String stevejobsbook = "9781451648546";
        final String url = "https://www.googleapis.com/books/v1/volumes?q=isbn:" + stevejobsbook;

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String isbnValue = isbnEditText.getText().toString(); // get the isbn on click

                //Do http request
                JsonObjectRequest jsObjRequest = new JsonObjectRequest(Request.Method.GET, url,
                        null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        detailsEditText.setText("Response => "+response.toString());
                        //findViewById(R.id.progressBar1).setVisibility(View.GONE);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                requestQueue.add(jsObjRequest);
            }
        });

    }
}
