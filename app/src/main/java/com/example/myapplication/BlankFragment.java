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

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String ARG_PARAM1 = "param1";
    public Databasee database;
    public static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    public String mParam1;
    public String mParam2;
    public  TextView Tview;
    public   QuranResponse response;
    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        Tview = view.findViewById(R.id.frame_text);
        SearchView Sview = view.findViewById(R.id.search);
        Sview.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                setAyat(Integer.parseInt(query));
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
        data.Fetchdata().getData(262).enqueue(new Callback<QuranResponse>() {
            @Override
            public void onResponse(Call<QuranResponse> call, Response<QuranResponse> response) {
                Toast.makeText(getC(),""+response.body().code,Toast.LENGTH_SHORT).show();
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
        Databasee.getDatabasee(getContext()).userdao().add(data);
    }
}