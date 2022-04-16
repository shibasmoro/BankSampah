package com.example.userbanksampah.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.userbanksampah.databinding.ItemMutasiBinding;
import com.example.userbanksampah.model.DetilMutasi;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class DetilMutasiAdapter  extends RecyclerView.Adapter<DetilMutasiAdapter.Holder>{

    private final ArrayList<DetilMutasi> detilMutasi = new ArrayList<>();
    NumberFormat formatter = new DecimalFormat("#,###");

    public DetilMutasiAdapter(ArrayList<DetilMutasi> data){
        this.detilMutasi.clear();
        this.detilMutasi.addAll(data);
    }
    @NonNull
    @Override
    public DetilMutasiAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMutasiBinding binding = ItemMutasiBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetilMutasiAdapter.Holder holder, int position) {
        holder.binding.tanggal.setText(detilMutasi.get(position).getSampah());
        holder.binding.total.setText("Rp. "+formatter.format(detilMutasi.get(position).getHarga()));
        holder.binding.admin.setText(""+detilMutasi.get(position).getTotal());
    }

    @Override
    public int getItemCount() {
        return detilMutasi.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        ItemMutasiBinding binding;
        public Holder(@NonNull ItemMutasiBinding data) {
            super(data.getRoot());
            this.binding =data;
        }
    }
}
