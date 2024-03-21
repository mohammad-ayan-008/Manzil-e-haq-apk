package com.example.myapplication.hadeedapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFetch {
    public static String link="https://hadithapi.com";
    public static apicall fetch;


    public static apicall get_data(){
        return new Retrofit.Builder().baseUrl(link)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(apicall.class);
    }

}
