package com.example.traveljournal.api;

import com.example.traveljournal.v2.pojo.WeatherData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {
    @GET("/weather")
    Call<WeatherData> getWeatherInfo(@Query("q") String destination,
                                     @Query("appid") String appid,
                                     @Query("units") String units);
}
