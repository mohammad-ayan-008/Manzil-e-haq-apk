package com.example.myapplication.quran_page;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface fetchpage {
    @GET("v1/page/{no}/en.asad")
    Call<dataset_page> getData(@Path("no") int page);
}
