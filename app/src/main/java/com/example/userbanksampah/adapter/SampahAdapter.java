package com.example.userbanksampah.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userbanksampah.databinding.DynamicRvItemBinding;
import com.example.userbanksampah.databinding.StaticRvItemBinding;
import com.example.userbanksampah.helper.NoteDiffCallback;
import com.example.userbanksampah.model.Sampah;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class SampahAdapter extends RecyclerView.Adapter<SampahAdapter.SampahHolder> {

    private ArrayList<Sampah> dataSampah = new ArrayList<>();
    NumberFormat formatter = new DecimalFormat("#,###");

    public void setDataSampah(ArrayList<Sampah> psampah){
        final NoteDiffCallback diffCallback = new NoteDiffCallback(this.dataSampah, psampah);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        this.dataSampah.clear();
        this.dataSampah.addAll(psampah);
        diffResult.dispatchUpdatesTo(this);
    }

    @NonNull
    @Override
    public SampahAdapter.SampahHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       DynamicRvItemBinding binding = DynamicRvItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SampahHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SampahAdapter.SampahHolder holder, int position) {
        holder.binding.sampah.setText(dataSampah.get(position).getSampah());
        holder.binding.price.setText(""+formatter.format(dataSampah.get(position).getHarga()));
    }

    @Override
    public int getItemCount() {
        return dataSampah.size();
    }

    public class SampahHolder extends RecyclerView.ViewHolder {
        private DynamicRvItemBinding binding;
        public SampahHolder(@NonNull DynamicRvItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
