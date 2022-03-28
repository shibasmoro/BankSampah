package com.example.userbanksampah.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userbanksampah.Model.Mutasi;
import com.example.userbanksampah.databinding.ItemMutasiBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MutasiAdapter extends RecyclerView.Adapter<MutasiAdapter.Holder> {
    private ArrayList<Mutasi> dataMutasi = new ArrayList<>();
    NumberFormat formatter = new DecimalFormat("#,###");
    public MutasiAdapter(ArrayList<Mutasi> data){
        dataMutasi.clear();
        dataMutasi.addAll(data);


    }
    @NonNull
    @Override
    public MutasiAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMutasiBinding binding = ItemMutasiBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MutasiAdapter.Holder holder, int position) {
        holder.binding.namaSampah.setText(dataMutasi.get(position).getSampah());
        holder.binding.biayaSampah.setText("Rp."+formatter.format(dataMutasi.get(position).getHarga()));
        holder.binding.totalSampah.setText(dataMutasi.get(position).getTotal()+"KG");

    }

    @Override
    public int getItemCount() {
        return dataMutasi.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ItemMutasiBinding binding;
        public Holder(@NonNull ItemMutasiBinding binding) {
            super(binding.getRoot());
            this.binding =binding;
        }
    }
}
