package com.parg.bartian.data.service;

import com.parg.bartian.domain.RealTimeInformation;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ganga_r on 5/27/2017.
 */

public interface RealTimeService {

    @GET("/api/etd.aspx?cmd=etd&key=")
    Observable<RealTimeInformation> getRealTimeInformation(@Query("orig") String origin);
}
