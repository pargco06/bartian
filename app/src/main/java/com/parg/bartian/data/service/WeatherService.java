package com.parg.bartian.data.service;

import com.parg.bartian.domain.weather.xml.Dwml;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ganga_r on 1/26/2017.
 */

public interface WeatherService {

    @GET("/xml/sample_products/browser_interface/ndfdBrowserClientByDay.php?format=24%20hourly&numDays=1")
    Observable<Dwml> execute(@Query("listLatLon") String listLatLon);
}
