package com.parg.bartian.data.service;

import com.parg.bartian.domain.Elevator;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ganga_r on 10/15/2016.
 */

public interface ElevatorService {
    @GET("/api/bsa.aspx?cmd=elev&key=")
    Observable<Elevator> execute();
}
