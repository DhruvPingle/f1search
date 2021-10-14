package com.example.forzacarsearch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class F1SearchAdapter extends RecyclerView.Adapter<F1SearchAdapter.F1SearchAdapterViewHolder> {



    private String jsonString;
    // private ImageView imageView;
    private Context context;

    public F1SearchAdapter(String jsonString){ this.jsonString = jsonString; }

    @NonNull
    @Override
    public F1SearchAdapter.F1SearchAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        int layoutForListItem = R.layout.activity_f1_search_adapter;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachImmediately = false;

        View view = inflater.inflate(layoutForListItem, parent, shouldAttachImmediately);
        F1SearchAdapterViewHolder viewHolder = new F1SearchAdapterViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull F1SearchAdapterViewHolder holder, int position) {


        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONObject MRData = jsonObject.getJSONObject("MRData");
            System.out.println(jsonObject.toString());
            String series = MRData.getString("series");
            System.out.println(series.toString());
            JSONObject RaceTable = MRData.getJSONObject("RaceTable");
            JSONArray Races = RaceTable.getJSONArray("Races");
            String season = RaceTable.getString("season");
            System.out.println(season.toString());
            String round = RaceTable.getString("round");
            System.out.println(series);
            System.out.println(season);
            System.out.println(round);

            holder.tv_series.setText(series);
            holder.tv_season.setText(season);
            holder.tv_round.setText(round);


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }



    @Override
    public int getItemCount() {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(this.jsonString);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 1;
    }

    public class F1SearchAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView tv_series, tv_season, tv_round;


        public F1SearchAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_series = itemView.findViewById(R.id.tv_series);
            tv_season = itemView.findViewById(R.id.tv_season);
            tv_round = itemView.findViewById(R.id.tv_round);
        }
    }



}
