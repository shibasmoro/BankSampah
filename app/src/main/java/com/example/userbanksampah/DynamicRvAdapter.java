package com.example.userbanksampah;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DynamicRvAdapter extends RecyclerView.Adapter<DynamicRvAdapter.DynamicRvHolder> {

    public ArrayList<DynamicRvModel> dynamicRvModels;
    public DynamicRvAdapter(ArrayList<DynamicRvModel> dynamicRvModels){
        this.dynamicRvModels = dynamicRvModels;
    }

    public class DynamicRvHolder extends RecyclerView.ViewHolder {
        public TextView sampah;
        public TextView price;
        public ConstraintLayout constraintLayout;

        public DynamicRvHolder(@NonNull View itemView) {
            super(itemView);
            sampah = itemView.findViewById(R.id.sampah);
            price = itemView.findViewById(R.id.price);
            constraintLayout = itemView.findViewById(R.id.constrainlayout);
        }
    }

    @NonNull
    @Override
    public DynamicRvAdapter.DynamicRvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dynamic_rv_item,parent,false);
        DynamicRvHolder dynamicRvHolder = new DynamicRvHolder(view);
        return dynamicRvHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DynamicRvHolder holder, int position) {
        DynamicRvModel currentItem = dynamicRvModels.get(position);
        holder.sampah.setText(currentItem.getName());
        holder.price.setText(currentItem.getHarga());
    }

    @Override
    public int getItemCount() {
        return dynamicRvModels.size();
    }


}