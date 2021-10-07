package com.example.forzacarsearch;


import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {
    final static String F1Search_BASE_URL = "http://ergast.com/api/" ;
    final static String PARAM_QUERY = "q";


    public static URL buildURL(String query) {
        Uri builtUri = Uri.parse(F1Search_BASE_URL)
                .buildUpon()
                .appendQueryParameter(PARAM_QUERY, query)
                .build();


        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();


            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");


            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}

//http://ergast.com/api/f1/2008/last?
