package com.parg.bartian.data.service;

import com.parg.bartian.domain.weather.json.CurrentWeather;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ganga_r on 2/2/2017.
 */

public interface CurrentWeatherService {
    @GET("/MapClick.php?FcstType=json")
    Observable<CurrentWeather> execute(@Query("lat") double lat, @Query("lon") double lon);
}
