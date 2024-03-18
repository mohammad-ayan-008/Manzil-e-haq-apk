package com.example.myapplication;
import com.example.myapplication.NamazAPI.*;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Recyclerviewsetup.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

import com.example.myapplication.Recyclerviewsetup.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment2#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class BlankFragment2 extends Fragment {

    public String mParam1;
    public String mParam2;
    RecyclerView Rview;
    RecyclerAdapter adapter;
    ArrayList<holderdata> data = new ArrayList<>();
    private LocationManager locationManager;
    private LocationListener locationListener;

    public double latitude,longitude;
    public ProgressBar bar;

    public boolean Location_Granted=false;


    // TODO: Rename and change types and number of parameters


    public BlankFragment2() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bar = view.findViewById(R.id.progressBar);
        bar.setVisibility(View.VISIBLE);
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

                 if(latitude != location.getLatitude()&& longitude != location.getLongitude()&&!Location_Granted) {
                     latitude = location.getLatitude();
                     longitude = location.getLongitude();
                     Log.d("data", latitude + "/" + longitude);
                     make_api_call(latitude,longitude);
                     Location_Granted=true;
                 }
            }
        };

        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED &&
           ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_FINE_LOCATION},100);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,locationListener);


        Rview = view.findViewById(R.id.recycle);
        adapter = new RecyclerAdapter(getContext(),data);
        Rview.setAdapter(adapter);
        Rview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.notifyDataSetChanged();

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank2, container, false);
    }

    @Override
    public void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onDestroy() {
        data.clear();
        Location_Granted=false;
        super.onDestroy();
    }
   public void make_api_call(double latitude,double longitude){
        fetch_namaz_timing time = new fetch_namaz_timing();

        time.fetcher_namaz_data().getData(2024,4,latitude,longitude,2).enqueue(new Callback<dataset>() {
            @Override
            public void onResponse(Call<dataset> call, Response<dataset> response) {
                data.clear();
                if (response.isSuccessful()){
                         List<namazData_holder> datas = response.body().getData();
                        time t = datas.get(0).getTimings();
                        holderdata hdf = new holderdata("Fajr",t.getFajrTime());
                        holderdata hdd = new holderdata("Dhur",t.getDhuhrTime());
                        holderdata hda = new holderdata("Asr",t.getAsrTime());
                        holderdata hdm = new holderdata("magrib",t.getMaghribTime());
                        holderdata hdi = new holderdata("Isha",t.getIshaTime());
                        data.add(hdf);
                        data.add(hdd);
                        data.add(hda);
                        data.add(hdm);
                        data.add(hdi);
                }
                bar.setVisibility(View.INVISIBLE);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<dataset> call, Throwable t) {

            }
        });
   }
}