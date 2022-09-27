package com.example.accuweatherapis;


import android.content.Context;
//import android.support.annotation.NonNull;
import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


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
        TextView linkTextView = convertView.findViewById(R.id.moblink);

        dateTextView.setText(forecast.getDate());
        nameTextView.setText(forecast.getName());
        valueTextView.setText(forecast.getValue());
        categoryTextView.setText(forecast.getCategory());
        categoryValueTextView.setText(forecast.getCategoryValue());
        textTextView.setText(forecast.getText());
        if(nameTextView.getText().equals("Прогноза за мигрена")){
            linkTextView.setText(R.string.hyperlink1);
        }
        else if(nameTextView.getText().equals("Прогноза за болки од артритис")){
            linkTextView.setText(R.string.hyperlink);
        }
        else if(nameTextView.getText().equals("Прогноза за синусна главоболка")){
            linkTextView.setText(R.string.hyperlink2);
        }


        return convertView;

    }
}
