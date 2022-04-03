package com.example.userbanksampah.adapter;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.userbanksampah.databinding.StaticRvItemBinding;
import com.example.userbanksampah.model.KategoriSampah;
import com.example.userbanksampah.model.Sampah;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class KategoriSampahAdapter extends RecyclerView.Adapter<KategoriSampahAdapter.SampahHolder> {
    private ArrayList<KategoriSampah> data = new ArrayList<>();
    int row_index;
    NumberFormat formatter = new DecimalFormat("#,###");
    public void setData(ArrayList<KategoriSampah> param){
        this.data.clear();
        this.data.addAll(param);
    }

    // objek implementasi nya
    private ItemClick itemClick;

    public void setItemClick(ItemClick itemClick){
        this.itemClick =itemClick;
    }
    @NonNull
    @Override
    public SampahHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StaticRvItemBinding binding = StaticRvItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new SampahHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SampahHolder holder, int position) {
        row_index =position;
            holder.binding.textview.setText(data.get(position).getKategori());
            holder.itemView.setOnClickListener(v->itemClick.Click(data.get(holder.getAdapterPosition()))
            );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class SampahHolder extends RecyclerView.ViewHolder {
        public final StaticRvItemBinding binding;
        public SampahHolder(@NonNull StaticRvItemBinding binding) {
            super(binding.getRoot());
            this.binding =binding;
        }
    }
    // buat interface di dalam kelas
    public interface ItemClick{
        void Click(KategoriSampah data);
    }
}
