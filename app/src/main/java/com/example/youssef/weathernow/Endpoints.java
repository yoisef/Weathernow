package com.example.youssef.weathernow;

import android.text.Editable;

import com.example.youssef.weathernow.models.CurWeather;
import com.example.youssef.weathernow.models.ForcWeather;
import com.example.youssef.weathernow.models.Searchlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Endpoints  {


    @GET("current.json?key=ae70fd6bf0074213bf054319181901")
    Call<CurWeather> getcurentweather(@Query("q") String lanlat);

    @GET("forecast.json?key=ae70fd6bf0074213bf054319181901&days=5")
    Call<ForcWeather> getforecast(@Query("q")String city);

    @GET("search.json?key=ae70fd6bf0074213bf054319181901")
    Call<List<Searchlist>> getsearchlist(@Query("q") String string);
}
