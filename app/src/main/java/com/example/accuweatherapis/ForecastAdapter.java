package com.example.accuweatherapis;


import android.content.Context;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;

import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.AbsoluteLayout;
import java.util.ArrayList;
import android.text.method.LinkMovementMethod;
import android.webkit.WebView;
import android.webkit.WebChromeClient;

public class ForecastAdapter extends ArrayAdapter<Forecast>{
    public ForecastAdapter(Context context, ArrayList<Forecast> forecastArrayList) {
        super(context, 0, forecastArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Forecast forecast = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        TextView dateTextView = convertView.findViewById(R.id.mobDate);
        TextView nameTextView = convertView.findViewById(R.id.mobName);
        TextView valueTextView = convertView.findViewById(R.id.mobValue);
        TextView categoryTextView = convertView.findViewById(R.id.mobCategory);
        TextView categoryValueTextView = convertView.findViewById(R.id.mobCategoryValue);
        TextView textTextView = convertView.findViewById(R.id.mobText);
        WebView linkTextView = convertView.findViewById(R.id.moblink);

        dateTextView.setText(forecast.getDate());
        nameTextView.setText(forecast.getName());
        valueTextView.setText(forecast.getValue());
        categoryTextView.setText(forecast.getCategory());
        categoryValueTextView.setText(forecast.getCategoryValue());
        textTextView.setText(forecast.getText());
        if(nameTextView.getText().equals("Прогноза за мигрена")){
            linkTextView.setWebViewClient(new WebViewClient());
            linkTextView.getSettings().setJavaScriptEnabled(true);
            linkTextView.getSettings().setAppCacheEnabled(true);
            linkTextView.loadUrl("https://www.accuweather.com/mk/mk/skopje/227397/migraine-weather/227397");
            linkTextView.setWebChromeClient(new WebChromeClient());
        }
        else if(nameTextView.getText().equals("Прогноза за болки од артритис")){
            linkTextView.setWebViewClient(new WebViewClient());
            linkTextView.getSettings().setJavaScriptEnabled(true);
            linkTextView.getSettings().setAppCacheEnabled(true);
            linkTextView.loadUrl("https://www.accuweather.com/mk/mk/skopje/227397/arthritis-weather/227397");
            linkTextView.setWebChromeClient(new WebChromeClient());
        }
        else if(nameTextView.getText().equals("Прогноза за синусна главоболка")){
            linkTextView.setWebViewClient(new WebViewClient());
            linkTextView.getSettings().setJavaScriptEnabled(true);
            linkTextView.getSettings().setAppCacheEnabled(true);
            linkTextView.loadUrl("https://www.accuweather.com/mk/mk/skopje/227397/sinus-weather/227397");
            linkTextView.setWebChromeClient(new WebChromeClient());
        }
        return convertView;

    }
}
