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

class F1SearchAdapter extends RecyclerView.Adapter<F1SearchAdapter.F1SearchAdapterViewHolder> {



    private String jsonString;
    // private ImageView imageView;
    private Context context;

    public F1SearchAdapter(String jsonString){ this.jsonString = jsonString;
    }

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

    }



    @Override
    public int getItemCount() {
        JSONArray jsonArray = null;
        try {
            jsonArray = new JSONArray(this.jsonString);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(jsonArray.length());
        return jsonArray.length();
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
