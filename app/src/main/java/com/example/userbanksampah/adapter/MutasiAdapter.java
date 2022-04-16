package com.example.userbanksampah.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userbanksampah.activty.DetilMutasiActivity;
import com.example.userbanksampah.model.Mutasi;
import com.example.userbanksampah.databinding.ItemMutasiBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class MutasiAdapter extends RecyclerView.Adapter<MutasiAdapter.Holder> {

    private final ArrayList<Mutasi> dataMutasi = new ArrayList<>();
    NumberFormat formatter = new DecimalFormat("#,###.##");
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
        holder.binding.tanggal.setText(dataMutasi.get(position).getTanggal());
        holder.binding.total.setText("Rp. "+formatter.format(dataMutasi.get(position).getHarga()));
        holder.binding.admin.setText(dataMutasi.get(position).getAdmin());
        holder.setdata(dataMutasi.get(position));


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

        public void setdata (Mutasi data){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detil = new Intent(itemView.getContext(),DetilMutasiActivity.class);
                    detil.putExtra("ID",data);
                    itemView.getContext().startActivity(detil);
                }
            });


        }
    }
}
