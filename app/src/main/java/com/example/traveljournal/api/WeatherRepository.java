package com.example.traveljournal.api;

import com.example.traveljournal.v2.pojo.WeatherData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherRepository {
    protected static final String BASE_WEATHER_URL =
            "https://api.openweathermap.org/data/2.5/";
    private static final String API_KEY = "d131fe9625585e29270c0aaac61a5d31";
    private static WeatherRepository weatherRepository;

    private WeatherApi weatherApi;

    public WeatherRepository(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public static WeatherRepository getInstance() {
        if (weatherRepository == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_WEATHER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            weatherRepository = new WeatherRepository(retrofit.create(WeatherApi.class));
        }

        return weatherRepository;
    }


    public void getWeatherDetails(final OnGetWeatherInfoCallback callback, String destination) {
        weatherApi.getWeatherInfo(destination, API_KEY, "metric")
                .enqueue(new Callback<WeatherData>() {
                    @Override
                    public void onResponse(Call<WeatherData> call, Response<WeatherData> response) {
                        if (response.isSuccessful()) {
                            WeatherData weather = response.body();
                            if (weather != null) {
                                callback.onSuccess(weather);
                            } else {
                                callback.onError();
                            }
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherData> call, Throwable t) {
                        callback.onError();
                    }
                });
    }
}
