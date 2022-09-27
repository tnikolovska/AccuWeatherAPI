package com.example.accuweatherapis;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.io.BufferedReader;

public class NetworkUtils {
    private static final String TAG="NetworkUtils";

    private final static String WEATHERDB_BASE_URL = "http://dataservice.accuweather.com/indices/v1/daily/5day/227397/groups/2";

    private final static String API_KEY = "DjvaebcGIABJ9MQvlLu3dxn98XmzI1dJ";

    private final static String LANGUAGE = "mk";

    private final static String DETAILS = "true";


    public static URL buildUrlForForecast(){
        Uri builtUri = Uri.parse(WEATHERDB_BASE_URL).buildUpon()
                .appendQueryParameter("apikey",API_KEY)
                .appendQueryParameter("language", LANGUAGE)
                .appendQueryParameter("details", DETAILS)
                .build();

        URL url=null;
        try{
            url=new URL(builtUri.toString());

        }catch(MalformedURLException e){
            e.printStackTrace();
        }
        Log.i(TAG,"buildUrlForWeather: url: "+url);
        return url;
    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
        try{
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean  hasInput=scanner.hasNext();
            if(hasInput)
            {
                return scanner.next();

            }else{
                return null;
            }

        }finally{
            urlConnection.disconnect();

        }

    }


}
