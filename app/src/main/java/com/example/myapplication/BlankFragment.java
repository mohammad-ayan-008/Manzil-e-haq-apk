package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.APIREQUST.Fetchdata;
import com.example.myapplication.APIREQUST.QuranResponse;
import com.example.myapplication.Recycle_dataset_ayat.*;
import com.example.myapplication.RoomDATA.AyatData;
import com.example.myapplication.RoomDATA.Databasee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import com.example.myapplication.quran_page.*;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

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

    public RecyclerView Rview;
    private List<holder_ayat> ayats=new ArrayList<>();
    public recycleradapter_ayat adapter;
    ImageButton button1,button2;
    public int page=2;
    public ProgressBar bar;
    public TextView ayat;
    private SharedPreferences preff;

    public static  String key_page="page_saved";

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Tview = view.findViewById(R.id.frame_text);
        SearchView Sview = view.findViewById(R.id.search);
        Sview.setOnClickListener((View v)->{
            Sview.setIconified(false);
        });



        preff = getContext().getSharedPreferences("page_no",Context.MODE_PRIVATE);
        page= load(key_page);
        bar = view.findViewById(R.id.loading);
        Rview = view.findViewById(R.id.recyclerView);
        Rview.setLayoutManager(new LinearLayoutManager(getContext()));
        ayat=view.findViewById(R.id.textView3);
        adapter = new recycleradapter_ayat(getContext(),ayats);
        Rview.setAdapter(adapter);
        call_page(page);

        button1 = view.findViewById(R.id.imageButton);
        button2 = view.findViewById(R.id.imageButton2);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(page>1){
                    page --;
                    call_page(page);
                    bar.setVisibility(View.VISIBLE);
                    save(key_page,page);
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(page<604){
                    page++;
                    call_page(page);
                    bar.setVisibility(View.VISIBLE);
                    save(key_page,page);
                }

            }
        });

        adapter.notifyDataSetChanged();



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


    public void call_page(int page){
        ayats.clear();
     apirequest_page.page().getData(page).enqueue(new Callback<dataset_page>() {
         @Override
         public void onResponse(Call<dataset_page> call, Response<dataset_page> response) {
             if (response.isSuccessful()){
                // Toast.makeText(getContext(),""+response.body().getData().getAyahs().get(0).getText(),Toast.LENGTH_SHORT).show();
                 for (Ayats a:response.body().getData().getAyahs()){
                     ayats.add(new holder_ayat(a.getText()+"\n" + "("+a.surah.getEnglishName()+")"));
                 }
                 adapter.notifyDataSetChanged();
                 bar.setVisibility(View.INVISIBLE);
                 ayat.setText("page:- "+page);
             }
         }

         @Override
         public void onFailure(Call<dataset_page> call, Throwable t) {
         Toast.makeText(getContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
         }
     });
    }
    void save (String key,int data){
      SharedPreferences.Editor edit = preff.edit();
      edit.putInt(key,data);
      edit.apply();
      edit.commit();
    }
    int load(String key){
        return preff.getInt(key,1);
    }

}