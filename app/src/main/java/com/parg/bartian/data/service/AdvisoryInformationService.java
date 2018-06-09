package com.parg.bartian.data.service;

import com.parg.bartian.domain.Advisory;

import retrofit2.http.GET;
import rx.Observable;

public interface AdvisoryInformationService {

    @GET("/api/bsa.aspx?cmd=bsa&key=")
    Observable<Advisory> Observable();
}
