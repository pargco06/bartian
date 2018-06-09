package com.parg.bartian.data.retrofit;

import com.parg.bartian.data.xmlconverter.CustomXmlConverterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by ganga_r on 1/28/2017.
 */

public enum  RetrofitFactory {
    INSTANCE;
    
    public Retrofit getRetrofit(RetrofitConfig retrofitConfig) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(retrofitConfig.getBaseUrl());
        builder.client(getOkHttpClient(retrofitConfig));
        if(retrofitConfig.isJson()) {
            builder.addConverterFactory(GsonConverterFactory.create());
        } else {
            builder.addConverterFactory(CustomXmlConverterFactory.create());
            builder.addConverterFactory(SimpleXmlConverterFactory.createNonStrict());
        }
        builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        return builder.build();
    }

    private OkHttpClient getOkHttpClient(RetrofitConfig retrofitConfig) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(httpLoggingInterceptor);
        okHttpClientBuilder.writeTimeout(retrofitConfig.getWriteTimeout(), TimeUnit.MILLISECONDS);
        okHttpClientBuilder.readTimeout(retrofitConfig.getReadTimeout(), TimeUnit.MILLISECONDS);
        okHttpClientBuilder.connectTimeout(retrofitConfig.getConnectTimeout(), TimeUnit.MILLISECONDS);

        return okHttpClientBuilder.build();
    }
}
