package com.example.myapplication.hadeedapi;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface apicall {
    @GET("/api/hadiths/")
    Call<hadeed_data> get(@Query("apiKey") String key,@Query("book") String book,@Query("page") int page);

}
