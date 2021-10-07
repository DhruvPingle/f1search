package com.example.forzacarsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv_f1Search;
    private Button b_search;
    private EditText et_series;
    private EditText et_round;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_f1Search = findViewById(R.id.rv_f1Search);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_f1Search.setLayoutManager(layoutManager);

        b_search = findViewById(R.id.b_search);
        et_series = findViewById(R.id.et_series);
        et_round = findViewById(R.id.et_round);
        b_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(et_series.getText() + "");
                System.out.println(et_round.getText() + "");
                URL f1searchQuery = NetworkUtils.buildURL(et_series.getText() + "");
                System.out.println(f1searchQuery.toString());
                new f1SearchQueryTask().execute(f1searchQuery);


            }
    });
}

    public  class f1SearchQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            String response= "";
            try {
                response = NetworkUtils.getResponseFromHttpUrl(urls[0]);
                System.out.println(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
        }


    }
}