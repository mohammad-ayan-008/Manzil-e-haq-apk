package com.example.myapplication.NamazAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface fetcher_namaz_data {
    @GET("v1/calendar/{year}/{month}")
   // latitude=28.84273251&longitude=79.66741517&method=2
    public Call<dataset> getData(@Path("year") int year,
                                 @Path("month") int month, @Query("latitude") double latitude,
                                 @Query("longitude") double longitude,
                                 @Query("method") int method);
    @GET("v1/calendar/2017/4?latitude=51.508515&longitude=-0.1254872&method=2 http://api.aladhan.com/v1/calendar/2019?latitude=51.508515&longitude=-0.1254872&method=2")
    public  Call<dataset> getData_2();
}
