package com.example.userbanksampah.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userbanksampah.databinding.ItemMutasiBinding;
import com.example.userbanksampah.databinding.ItemMutasiTansaksiBinding;
import com.example.userbanksampah.model.MutasiPenarikan;
import com.example.userbanksampah.util.FormatAngka;
import com.example.userbanksampah.util.Tanggal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MutasiPenarikanAdapter extends RecyclerView.Adapter<MutasiPenarikanAdapter.holder> {

    private final ArrayList<com.example.userbanksampah.model.MutasiPenarikan> dataMutasi = new ArrayList<>();
    private Context context;

    public MutasiPenarikanAdapter(Context context, ArrayList<MutasiPenarikan> penarikan) {
        this.context = context;
        this.dataMutasi.clear();
        this.dataMutasi.addAll(penarikan);
    }

    @NonNull
    @Override
    public MutasiPenarikanAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMutasiTansaksiBinding binding = ItemMutasiTansaksiBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MutasiPenarikanAdapter.holder holder, int position) {
        holder.showData(dataMutasi.get(position));
    }

    @Override
    public int getItemCount() {
        return dataMutasi.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        ItemMutasiTansaksiBinding binding;
        long tanggalLong;
        Date tanggalDate;


        public holder(@NonNull ItemMutasiTansaksiBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void showData(MutasiPenarikan penarikan) {
            tanggalLong = dateToMilis(penarikan.getDate());
            tanggalDate = longToDate(tanggalLong);
            binding.tvtanggal.setText(penarikan.getDate());
            binding.etHarga.setText(FormatAngka.token(FormatAngka.format(penarikan.getJumlah())));
            binding.etNamaAdmin.setText(penarikan.getNama());

        }

        private Long dateToMilis(String data) {
            Calendar calendar = Calendar.getInstance();
            try {
                Date date = Tanggal.dateFormat.parse(data);
                calendar.setTime(date);

            } catch (Exception e) {
                showToast(e.getMessage());
            }

            return calendar.getTimeInMillis();
        }

        private Date longToDate(long data) {
            Date date;
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(data);
            date = calendar.getTime();
            return date;

        }
        public void showToast(String message) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }


}

