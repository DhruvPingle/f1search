package com.example.forzacarsearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv_f1Search;
    private Button b_search;
    private EditText et_series;
    private EditText et_round;
    private  EditText et_season;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv_f1Search = findViewById(R.id.rv_f1Search);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv_f1Search.setLayoutManager(layoutManager);

        b_search = findViewById(R.id.b_search);
        et_series = findViewById(R.id.et_series);
        et_season = findViewById(R.id.et_season);
        et_round = findViewById(R.id.et_round);
        b_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(et_round.getText() + "");
                System.out.println(et_season.getText() + "");
                String series = et_series.getText() + "";
                String round = et_round.getText() + "";
                String season = et_season.getText() + "";

                String url = "http://ergast.com/api";

                if (!series.equals("")){
                    url += "/" + series;
                }

                if (!season.equals("")){
                    url += "/" + season;
                }

                if (!round.equals("")){
                    url += "/" + round;
                }

                url += ".json";

                System.out.println(url);
                //URL f1searchQuery = NetworkUtils.buildURL(et_series.getText() + "");
                //System.out.println(f1searchQuery.toString());
                URL f1searchQuery = null;
                try {
                    f1searchQuery = new URL(url);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
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

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println("Hello: "+s);
            rv_f1Search.setHasFixedSize(true);
            F1SearchAdapter f1SearchAdapter = new F1SearchAdapter(s);
            rv_f1Search.setAdapter(f1SearchAdapter);

            try {
                 JSONObject jsonObject = new JSONObject(s);
                JSONObject MRData = jsonObject.getJSONObject("MRData");
                String series = MRData.getString("series");
                JSONObject RaceTable = jsonObject.getJSONObject("RaceTable");
                JSONArray Races = jsonObject.getJSONArray("Races");
                String season = Races.getString(Integer.parseInt("season"));
                String round = RaceTable.getString("round");
                System.out.println(series);
                System.out.println(season);
                System.out.println(round);
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}