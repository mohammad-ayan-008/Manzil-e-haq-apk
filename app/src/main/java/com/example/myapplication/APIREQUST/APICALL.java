package com.example.myapplication.APIREQUST;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APICALL {
    @GET("v1/ayah/{no}/en.asad")
    Call<QuranResponse> getData(@Path("no") int ayat_no);

}
