package com.parg.bartian.data.service;

import com.parg.bartian.data.retrofit.RetrofitConfig;
import com.parg.bartian.data.retrofit.RetrofitFactory;

import retrofit2.Retrofit;

/**
 * Created by ganga_r on 10/15/2016.
 */

public enum  ServiceLocator {
    INSTANCE;

    private static String API_BART_BASE_URL = "http://api.bart.gov";
    private static String API_WEATHER_BASE_URL = "http://graphical.weather.gov";
    private static String API_CURRENT_WEATHER_BASE_URL = "http://forecast.weather.gov";
    private static long CONNECT_TIMEOUT = 20000l;
    private static long WRITE_TIMEOUT = 20000l;
    private static long READ_TIMEOUT = 20000l;

//    private ServiceLocator(){}

    private static ServiceLocator instance;
    private static Retrofit retrofit;

    /*public static ServiceLocator getInstance() {
        if(instance == null) {
            instance = new ServiceLocator();
//            retrofit = initRetrofit();
        }
        return instance;
    }*/

    private RetrofitConfig getBartRetrofitConfig() {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        retrofitConfig.setBaseUrl(API_BART_BASE_URL);
        retrofitConfig.setReadTimeout(READ_TIMEOUT);
        retrofitConfig.setWriteTimeout(WRITE_TIMEOUT);
        retrofitConfig.setConnectTimeout(CONNECT_TIMEOUT);
        return retrofitConfig;
    }

    private RetrofitConfig getWeatherRetrofitConfig() {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        retrofitConfig.setBaseUrl(API_WEATHER_BASE_URL);
        retrofitConfig.setReadTimeout(READ_TIMEOUT);
        retrofitConfig.setWriteTimeout(WRITE_TIMEOUT);
        retrofitConfig.setConnectTimeout(CONNECT_TIMEOUT);
        return retrofitConfig;
    }

    private RetrofitConfig getCurrentWeatherRetrofitConfig() {
        RetrofitConfig retrofitConfig = new RetrofitConfig();
        retrofitConfig.setJson(true);
        retrofitConfig.setBaseUrl(API_CURRENT_WEATHER_BASE_URL);
        retrofitConfig.setReadTimeout(READ_TIMEOUT);
        retrofitConfig.setWriteTimeout(WRITE_TIMEOUT);
        retrofitConfig.setConnectTimeout(CONNECT_TIMEOUT);
        return retrofitConfig;
    }

    public ElevatorService getElevatorService() {
        return retrofit.create(ElevatorService.class);
    }

    public AllStationsService getAllStationsService() {
        return RetrofitFactory.INSTANCE
                .getRetrofit(getBartRetrofitConfig())
                .create(AllStationsService.class);
    }

    public ScheduleService getScheduleService() {
        return RetrofitFactory.INSTANCE
                .getRetrofit(getBartRetrofitConfig())
                .create(ScheduleService.class);
    }

    public WeatherService getWeatherService() {
        return RetrofitFactory.INSTANCE
                .getRetrofit(getWeatherRetrofitConfig())
                .create(WeatherService.class);
    }

    public CurrentWeatherService getCurrentWeatherService() {
        return RetrofitFactory.INSTANCE
                .getRetrofit(getCurrentWeatherRetrofitConfig())
                .create(CurrentWeatherService.class);
    }

    public RealTimeService getRealTimeService() {
        return RetrofitFactory.INSTANCE
                .getRetrofit(getBartRetrofitConfig())
                .create(RealTimeService.class);
    }

    public AdvisoryInformationService getAdvisoryService() {
        return RetrofitFactory.INSTANCE
                .getRetrofit(getBartRetrofitConfig())
                .create(AdvisoryInformationService.class);
    }
}
