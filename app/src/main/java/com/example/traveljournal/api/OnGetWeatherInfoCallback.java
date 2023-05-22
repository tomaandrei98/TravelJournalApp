package com.example.traveljournal.api;

import com.example.traveljournal.v2.pojo.WeatherData;

public interface OnGetWeatherInfoCallback {
    void onSuccess(WeatherData weatherData);

    void onError();
}
