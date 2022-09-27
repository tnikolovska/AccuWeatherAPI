package com.example.accuweatherapis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private ArrayList<Forecast> forecastArrayList = new ArrayList<>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.idListView);

        URL forecastUrl = NetworkUtils.buildUrlForForecast();
        new FetchForecastDetails().execute(forecastUrl);
        Log.i(TAG, "onCreate: forecastUrl: " + forecastUrl);

       /* TextView t2 = findViewById(R.id.moblink);
        t2.setMovementMethod(LinkMovementMethod.getInstance());*/

    }

    private class FetchForecastDetails extends AsyncTask<URL, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(URL... urls) {
            URL forecastUrl = urls[0];
            String forecastSearchResults = null;

            try {
                forecastSearchResults = NetworkUtils.getResponseFromHttpUrl(forecastUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.i(TAG, "doInBackground: forecastSearchResults: " + forecastSearchResults);
            return forecastSearchResults;
        }

        @Override
        protected void onPostExecute(String forecastSearchResults) {
            if(forecastSearchResults != null && !forecastSearchResults.equals("")) {
                forecastArrayList = parseJSON(forecastSearchResults);
                //Just for testing
                Iterator itr = forecastArrayList.iterator();
                while(itr.hasNext()) {
                    Forecast forecastInIterator = (Forecast) itr.next();
                    Log.i(TAG, "onPostExecute: Name: " + forecastInIterator.getName()+
                            " Date: " + forecastInIterator.getDate() +
                            " Value: " + forecastInIterator.getValue() +
                            " Category: " + forecastInIterator.getCategory() +
                            " Category Value : " + forecastInIterator.getCategoryValue() +
                            " Text: " + forecastInIterator.getText() +
                            " Link: " + forecastInIterator.getLink());
                }
            }
            super.onPostExecute(forecastSearchResults);
        }
    }

    private ArrayList<Forecast> parseJSON(String forecastSearchResults) {
        if(forecastArrayList != null) {
            forecastArrayList.clear();
        }

        if(forecastSearchResults != null) {
            try {
                forecastSearchResults="{DailyIndexValues:"+forecastSearchResults+"}";
                JSONObject rootObject = new JSONObject(forecastSearchResults);
                JSONArray results = rootObject.getJSONArray("DailyIndexValues");

                for (int i = 0; i < results.length(); i++) {
                    Forecast forecast = new Forecast();

                    JSONObject resultsObj = results.getJSONObject(i);

                    String date = resultsObj.getString("LocalDateTime");
                    forecast.setDate(date);

                    String name = resultsObj.getString("Name");
                    forecast.setName(name);

                    String value = resultsObj.getString("Value");
                    forecast.setValue(value);

                    String category = resultsObj.getString("Category");
                    forecast.setCategory(category);

                    String categoryValue = resultsObj.getString("CategoryValue");
                    forecast.setCategoryValue(categoryValue);

                    String text = resultsObj.getString("Text");
                    forecast.setText(text);

                    String link = resultsObj.getString("MobileLink");
                    forecast.setLink(link);


                   /* Log.i(TAG, "parseJSON: date: " + date + " " +
                            "Min: " + minTemperature + " " +
                            "Max: " + maxTemperature + " " +
                            "Link: " + link);*/

                    forecastArrayList.add(forecast);
                }

                if(forecastArrayList != null) {
                    ForecastAdapter forecastAdapter = new ForecastAdapter(this, forecastArrayList);
                    listView.setAdapter(forecastAdapter);
                }

                return forecastArrayList;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



}