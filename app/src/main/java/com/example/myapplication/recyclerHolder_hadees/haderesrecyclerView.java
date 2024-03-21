package com.example.myapplication.recyclerHolder_hadees;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class haderesrecyclerView extends RecyclerView.Adapter<haderesrecyclerView.Holder> {
   public Context context;
   public List<hadees_data> data;

    public haderesrecyclerView(Context context, List<hadees_data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new haderesrecyclerView.Holder(LayoutInflater.from(context).inflate(R.layout.hadess_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
         hadees_data d = data.get(position);
         holder.text.setText(d.getEnglish_hadees()+"\n"+d.getUrdu_hadees());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView text;
        public Holder(@NonNull View itemView) {
            super(itemView);
            text= itemView.findViewById(R.id.hadees_pagedata);
        }
    }
}
