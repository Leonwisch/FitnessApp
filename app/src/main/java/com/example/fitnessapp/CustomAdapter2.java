package com.example.fitnessapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.MyViewHolder> {
    private Context context;
    private ArrayList uebung_id, uebung_name, uebung_weight;

    CustomAdapter2(Context context , ArrayList uebung_id, ArrayList uebung_name, ArrayList uebung_weight){
        this.context = context;
        this.uebung_id = uebung_id;
        this.uebung_name = uebung_name;
        this.uebung_weight = uebung_weight;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row2, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.uebung_name_txt.setText(String.valueOf(uebung_name.get(position)));
        holder.uebung_weight_txt.setText(String.valueOf(uebung_weight.get(position)));
    }

    @Override
    public int getItemCount() {
        return uebung_name.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView uebung_name_txt, uebung_weight_txt;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            uebung_name_txt = itemView.findViewById(R.id.uebung_name_txt);
            uebung_weight_txt = itemView.findViewById(R.id.uebung_weight_txt);
        }
    }
}
