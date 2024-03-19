package com.example.myapplication;
import com.example.myapplication.NamazAPI.*;
import android.Manifest;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.myapplication.Recyclerviewsetup.RecyclerAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.example.myapplication.Recyclerviewsetup.*;
import com.example.myapplication.RoomDATA.Databasee;

import okhttp3.CacheControl;
import okhttp3.Request;
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

    public boolean Location_Granted,is_data_and_opened_first,permission,is_location=false;
    public SharedPreferences preffs;

    public  NetworkChangeeciver reciever;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bar = view.findViewById(R.id.progressBar);
        bar.setVisibility(View.VISIBLE);
        ConnectivityManager manager =(ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
         is_data_and_opened_first=  info != null && info.isConnected();
        preffs = getContext().getSharedPreferences("local values",Context.MODE_PRIVATE);
        reciever = new NetworkChangeeciver();
        // registering broadcast reciever
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        getContext().registerReceiver(reciever,filter);


        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
        }
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)&&is_data_and_opened_first){
                Intent location = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                location.putExtra("enabled",true);
                getContext().startActivity(location);
                Intent activity = new Intent(getActivity().getPackageName());
                getActivity().startActivity(activity);
                getActivity().finish();
        }


        if(!is_data_and_opened_first){
             latitude= load("latitude");
             longitude=load("longitude");
            Toast.makeText(getContext(),"turn on your Internet",Toast.LENGTH_SHORT).show();
           // make_api_call(latitude,longitude);
          //  cache();
        }


        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {

                 if(latitude != location.getLatitude()&& longitude != location.getLongitude()&&!Location_Granted) {
                     latitude = location.getLatitude();
                     longitude = location.getLongitude();
                     save_data("latitude",latitude);
                     save_data("longitude",longitude);
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
                Intent location = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                getContext().startActivity(location);

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
        getContext().unregisterReceiver(reciever);
    }
   public void make_api_call(double latitude,double longitude){
       Toast.makeText(getContext(),"cached",Toast.LENGTH_SHORT).show();
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
            Toast.makeText(getContext(),"error",Toast.LENGTH_SHORT).show();
            }
        });
   }
   /*void cache (){
       try {
       CacheControl cacheControl = new CacheControl.Builder()
               .onlyIfCached()
               .maxStale(7, TimeUnit.DAYS) // Accept stale cached data for up to 7 days
               .build();

       Request request = new Request.Builder()
               .url(fetch_namaz_timing.fetcher_namaz_data().getData(2024,4,latitude,longitude,2).request().url())
               .cacheControl(cacheControl)
               .build();

    okhttp3.Response dat= fetch_namaz_timing.getClient(getContext())
            .newCall(request)
            .execute();
    if(dat.isSuccessful()&&dat.body() == null){
        Toast.makeText(getContext(),"done",Toast.LENGTH_SHORT).show();
    }else{
        Toast.makeText(getContext(),"lol",Toast.LENGTH_SHORT).show();

    }
}catch (Exception e){
Toast.makeText(getContext(),e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
}
   }*/
   void save_data(String key,double data){
     SharedPreferences.Editor edit =preffs.edit();
     edit.putFloat(key,(float) data);
     edit.apply();
     edit.commit();
   }
   double load(String key){
        return  (double) preffs.getFloat(key,0F);
   }


   class NetworkChangeeciver extends BroadcastReceiver{

       @Override
       public void onReceive(Context context, Intent intent) {
           if(!get_data(context)){
               AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                      builder .setMessage("Internet is OFF");
                      builder.setCancelable(false);
                      builder.setPositiveButton("Ok",new  DialogInterface.OnClickListener(){

                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              if(get_data(context)) {
                                  Intent i = ((MainActivity) getActivity()).getIntent();
                                  getActivity().finish();
                                  getContext().startActivity(
                                          i
                                  );
                                  dialog.dismiss();
                              }else{
                                  Toast.makeText(getContext(),"turn on your internet",Toast.LENGTH_SHORT).show();
                                  builder.create().show();
                              }
                          }
                      });
                      builder.create().show();
               //getContext().startActivity(new Intent(getContext().getPackageName()));

           }
       }
       boolean get_data(Context context){
           try {
               ConnectivityManager m = (ConnectivityManager)  context.getSystemService(Context.CONNECTIVITY_SERVICE);
               return  m.getActiveNetworkInfo()!=null&&m.getActiveNetworkInfo().isConnected() ;

           }catch (Exception e){

           }
           return false;
       }
   }
}