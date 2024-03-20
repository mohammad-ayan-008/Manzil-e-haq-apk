package com.example.myapplication.quran_page;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class apirequest_page {
   public static String api ="https://api.alquran.cloud/";
   public static fetchpage page;

   public static fetchpage page(){
       return new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
               .baseUrl(api)
               .build().create(fetchpage.class);
   }
}
