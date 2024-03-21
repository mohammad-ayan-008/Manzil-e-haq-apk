package com.example.myapplication;
import com.example.myapplication.hadeedapi.ApiFetch;
import com.example.myapplication.hadeedapi.hadeed_data;
import com.example.myapplication.hadeedapi.hadees;
import com.example.myapplication.hadeedbook_RecyclerData.*;
import com.example.myapplication.recyclerHolder_hadees.hadees_data;
import com.example.myapplication.recyclerHolder_hadees.*;
import android.os.Bundle;

import androidx.annotation.NonNull;
import  com.example.myapplication.hadeedapi.*;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class community extends Fragment {
    private ViewPager2 pager;
    private recycler_holder holer;
    private List<books_class> books=new ArrayList<>();
    private  int page=1;
    public List<hadees_data> d= new ArrayList<>();
    public  haderesrecyclerView adapter;
    public RecyclerView Rview;
    public TextView textView;
    public ImageButton left,right;

    public int total_pages=100;

    public int position=0;
    private ProgressBar bar;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
         pager = view.findViewById(R.id.viewpager);
         add_books();
         textView = view.findViewById(R.id.pages);
         left = view.findViewById(R.id.hadees_left);
         right = view.findViewById(R.id.hadees_right);
         bar = view.findViewById(R.id.pb);
         bar.setVisibility(View.INVISIBLE);
         holer = new recycler_holder(getContext(),books);
         pager.setAdapter(holer);
         holer.notifyDataSetChanged();
         adapter = new haderesrecyclerView(getContext(),d);
         Rview= view.findViewById(R.id.hadeesview);
         Rview.setLayoutManager(new LinearLayoutManager(getContext()));
         Rview.setAdapter(adapter);
         adapter.notifyDataSetChanged();
         Toast.makeText(getContext(),"select a book",Toast.LENGTH_SHORT).show();
         left.setOnClickListener((k)->{
               if(page>1){
                   page--;
                   bar.setVisibility(View.VISIBLE);
                   make_call_bybook();
               }
         });

        right.setOnClickListener((j)->{
            if(page<total_pages){
                page++;
                bar.setVisibility(View.VISIBLE);
                make_call_bybook();
             }
         });

         holer.onItemmClick(new recycler_holder.ItemEvent() {
             @Override
             public void onClick(int pos) {
                 Toast.makeText(getContext(), books.get(pos).getBook_name(),Toast.LENGTH_SHORT).show();
                 bar.setVisibility(View.VISIBLE);
                 page=1;
                 position=pos;
                 make_call_bybook();

             }
         });
         super.onViewCreated(view, savedInstanceState);
    }


    void add_books(){
        books.add(new books_class(R.drawable.bukari,"sahih-bukhari"));
        books.add(new books_class(R.drawable.sahaiaye_muslim,"sahih-muslim"));
        books.add(new books_class(R.drawable.tirmadi,"al-tirmidhi"));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hadid, container, false);
    }

    void make_call_bybook(){
        d.clear();
        ApiFetch.get_data().get("$2y$10$5unbR1UXqbaU6tKRFA23SNbJF6JEDpe6gHsXAwi3j2vxtSCW",books.get(position).getBook_name(),page).enqueue(new Callback<hadeed_data>() {
            @Override
            public void onResponse(Call<hadeed_data> call, Response<hadeed_data> response) {
                if(response.isSuccessful()) {
                    hadees haded=response.body().getHadiths();

                    for (data e:haded.getData()){
                        d.add(new hadees_data(e.getHadithEnglish(),e.getHadithUrdu()));
                    }
                    page = haded.current_page;
                    textView.setText("page"+page);
                    adapter.notifyDataSetChanged();
                    bar.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onFailure(Call<hadeed_data> call, Throwable t) {

            }
        });
    }
}