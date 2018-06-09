package com.parg.bartian.data.service;

import com.parg.bartian.domain.Stations;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ganga_r on 10/15/2016.
 */

public interface AllStationsService {
    @GET("/api/stn.aspx?cmd=stns&key=")
    Observable<Stations> Observable();
}
