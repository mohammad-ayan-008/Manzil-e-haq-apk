package com.example.myapplication.hadeedbook_RecyclerData;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class recycler_holder extends RecyclerView.Adapter<recycler_holder.Holder> {

    private Context context;
    private List<books_class> data;

    private  ItemEvent event;


    public recycler_holder(Context context, List<books_class> data) {
        this.context = context;
        this.data = data;
    }
    public void onItemmClick(ItemEvent event){
        this.event=event;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new recycler_holder.Holder(LayoutInflater.from(context).inflate(R.layout.hadid_book,parent,false));
    }



    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
       books_class d = data.get(position);
       holder.Iview.setImageResource(d.getBook_id());

       holder.Iview.setOnClickListener((v)->{
           event.onClick(position);
       });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends  RecyclerView.ViewHolder{
        ImageView Iview;
        public Holder(@NonNull View itemView) {
            super(itemView);
            Iview = itemView.findViewById(R.id.hadid_book);
        }

    }
    public static interface ItemEvent{
        void onClick(int pos);
    }

}
 