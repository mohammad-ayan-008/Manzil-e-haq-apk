package com.example.myapplication.APIREQUST;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fetchdata {
    public   QuranResponse list;
    public String Url_ayat="https://api.alquran.cloud/";
    public  APICALL  call;

    public APICALL Fetchdata(){
        Retrofit builder = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Url_ayat).build();
        call = builder.create(APICALL.class);
        return call;
    }

    public QuranResponse fetch (int ayat_no){

        call.getData(ayat_no).enqueue(new Callback<QuranResponse>(){

            @Override
            public void onResponse(Call<QuranResponse> call, Response<QuranResponse> response) {
                if(response.isSuccessful()){
                    list = response.body();
                }
                Log.d("data","done");

            }

            @Override
            public void onFailure(Call<QuranResponse> call, Throwable t) {
                Log.d("data",t.getMessage());
            }
        });

        return list;
    }
}
