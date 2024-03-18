package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.APIREQUST.Fetchdata;
import com.example.myapplication.APIREQUST.QuranResponse;
import com.example.myapplication.RoomDATA.AyatData;
import com.example.myapplication.RoomDATA.Databasee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {

    public String mParam1;
    public String mParam2;
    public  TextView Tview;
    public   QuranResponse response;


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Tview = view.findViewById(R.id.frame_text);
        SearchView Sview = view.findViewById(R.id.search);
        Sview.setOnClickListener((View v)->{
            Sview.setIconified(false);
        });
        Sview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(Integer.parseInt(query)<=6236) {
                    setAyat(Integer.parseInt(query));
                }else{
                    Toast.makeText(getContext(),"ERROR",Toast.LENGTH_SHORT).show();
                }
                Sview.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        super.onViewCreated(view, savedInstanceState);
    }
    public void setAyat(int ayat){
        Fetchdata data = new Fetchdata();
        data.Fetchdata().getData(ayat).enqueue(new Callback<QuranResponse>() {
            @Override
            public void onResponse(Call<QuranResponse> call, Response<QuranResponse> response) {
               // Toast.makeText(getC(),""+response.body().code,Toast.LENGTH_SHORT).show();
                Tview.setText(response.body().data.text+"");
                AyatData data = new AyatData(ayat);
                add(data);

            }

            @Override
            public void onFailure(Call<QuranResponse> call, Throwable t) {

            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }
    public Context getC(){return getContext();}


    public void add(AyatData data){
        //Databasee.getDatabasee(getContext()).userdao().add(data);
    }
}