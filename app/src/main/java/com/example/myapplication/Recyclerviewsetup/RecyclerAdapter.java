package com.example.myapplication.Recyclerviewsetup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.Holder> {
   private Context context;
   private List<holderdata> data;

    public RecyclerAdapter(Context context, List<holderdata> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.holdernamaz,parent,false);
        return new RecyclerAdapter.Holder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
         holderdata d = data.get(position);
         holder.namaz_name.setText(d.namaz_name);
         holder.namaz_time.setText(d.timing);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class Holder extends RecyclerView.ViewHolder{
        public  TextView namaz_time,namaz_name;
        public Button button;
        Holder(View v){
            super (v);
            namaz_name = v.findViewById(R.id.namaz_name);
            namaz_time = v.findViewById(R.id.namaz_Time);
            button = v.findViewById(R.id.reminder);
            button.setOnClickListener((View view)->{
                // Do main work
                Toast.makeText(context,"hey",Toast.LENGTH_SHORT).show();
            });
        }


    }
}
