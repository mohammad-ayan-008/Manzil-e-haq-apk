package com.example.myapplication.Recycle_dataset_ayat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.quran_page.dataset_page;

import java.util.List;

public class recycleradapter_ayat extends RecyclerView.Adapter<recycleradapter_ayat.Holder> {
    private Context context;
    private List<holder_ayat> page;

    public recycleradapter_ayat(Context context, List<holder_ayat> page) {
        this.context = context;
        this.page = page;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.ayat_of_day,parent,false);
        return new recycleradapter_ayat.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
         holder_ayat p= page.get(position);
         holder.textView.setText(p.getAyat());
    }

    @Override
    public int getItemCount() {
        return page.size();
    }

    class Holder extends  RecyclerView.ViewHolder{
       TextView textView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.quran_pagedata);
        }
    }

}
