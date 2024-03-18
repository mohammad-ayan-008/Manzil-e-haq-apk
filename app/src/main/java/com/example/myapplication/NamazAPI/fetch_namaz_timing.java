package com.example.myapplication.NamazAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class fetch_namaz_timing {
  public String api= "http://api.aladhan.com/";
  public fetcher_namaz_data data;

  public fetcher_namaz_data fetcher_namaz_data(){
    data = new  Retrofit.Builder().baseUrl(api).addConverterFactory(GsonConverterFactory.create()).build().create(fetcher_namaz_data.class);
    return  data;
  }

}
