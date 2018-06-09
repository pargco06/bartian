package com.parg.bartian.data.service;

import com.parg.bartian.domain.ScheduleDetail;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by ganga_r on 10/23/2016.
 */

public interface ScheduleService {

    @GET("/api/sched.aspx?cmd=depart&key=&b=2&a=2&l=1&date=now&time=now")
    Observable<ScheduleDetail> getSchedule(@Query("orig") String orig, @Query("dest") String dest);
}
