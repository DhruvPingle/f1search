package com.example.forzacarsearch;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

class F1SearchAdapter extends RecyclerView.Adapter<F1SearchAdapter.F1SearchAdapterViewHolder> {



    private String jsonString;
    // private ImageView imageView;
    private Context context;

    @NonNull
    @Override
    public F1SearchAdapter.F1SearchAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        int layoutForListItem = R.layout.activity_forza_search_adapter;
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
        TextView tv_title, tv_summary;


        public F1SearchAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_summary = itemView.findViewById(R.id.tv_summary);



    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {


        ImageView img;

        public DownloadImageTask(ImageView img) {
            this.img = img;
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            InputStream in = null;
            try {
                in = new URL(strings[0]).openStream();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Bitmap pictureImage = BitmapFactory.decodeStream(in);
            System.out.println(pictureImage);

            return pictureImage;
        }
    }
}
