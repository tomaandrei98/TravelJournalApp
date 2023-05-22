package com.example.traveljournal.single;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.traveljournal.R;
import com.example.traveljournal.api.OnGetWeatherInfoCallback;
import com.example.traveljournal.api.WeatherRepository;
import com.example.traveljournal.database.TripsActivity;
import com.example.traveljournal.utils.Utils;
import com.example.traveljournal.v2.pojo.WeatherData;

public class ShowTripActivity extends AppCompatActivity {
    private Button backButton;
    private TextView textViewTitle;
    private TextView textViewDetails;
    private WeatherRepository weatherRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_trip);
        backButton = findViewById(R.id.backButton);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewDetails = findViewById(R.id.textViewDetails);
        weatherRepository = WeatherRepository.getInstance();

        setShowTripContent();

        backButton.setOnClickListener(view -> {
            Intent intent = new Intent(ShowTripActivity.this, TripsActivity.class);
            startActivity(intent);
        });
    }

    private void setShowTripContent() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            textViewTitle.setText(bundle.getString("tripName"));
            textViewDetails.setText(setTextViewDetailsContent(bundle));
        }


    }

    private String setTextViewDetailsContent(Bundle bundle) {
        String destination = bundle.getString("destination");
        int price = Math.round(bundle.getInt("price"));

        weatherRepository.getWeatherDetails(new OnGetWeatherInfoCallback() {
            @Override
            public void onSuccess(WeatherData weatherData) {
                Log.e("weather", weatherData.toString());
            }

            @Override
            public void onError() {
                Log.e("weatherData", "error");
            }
        }, bundle.getString("destination"));

        return "Destination: " + destination + "\n" +
                "Price: " + Utils.formatPrice(String.valueOf(price)) + " eur\n" +
                "Current weather: ";
    }
}