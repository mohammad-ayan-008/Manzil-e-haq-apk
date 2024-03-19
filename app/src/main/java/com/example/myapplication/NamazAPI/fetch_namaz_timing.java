package com.example.myapplication.NamazAPI;

import android.app.Application;
import android.content.Context;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class fetch_namaz_timing {
  public String api= "http://api.aladhan.com/";
  public fetcher_namaz_data data;
  private static  long catch_size= 5*1024*1024;




  public  fetcher_namaz_data fetcher_namaz_data(){

    data = new  Retrofit.Builder().baseUrl(api)
            .addConverterFactory(GsonConverterFactory.create()).build().create(fetcher_namaz_data.class);
    return  data;
  }

}
