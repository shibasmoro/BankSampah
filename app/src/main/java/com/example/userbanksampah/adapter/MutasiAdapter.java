package com.example.userbanksampah.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.userbanksampah.activty.DetilMutasiActivity;
import com.example.userbanksampah.model.Mutasi;
import com.example.userbanksampah.databinding.ItemMutasiBinding;
import com.example.userbanksampah.util.FormatAngka;
import com.example.userbanksampah.util.Tanggal;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MutasiAdapter extends RecyclerView.Adapter<MutasiAdapter.Holder> {
    private final Context context;
    //private SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateFormatter1 = new SimpleDateFormat("yyyy-MMM-dd");

    private final ArrayList<Mutasi> dataMutasi = new ArrayList<>();
    public MutasiAdapter( Context context,ArrayList<Mutasi> data) {
        dataMutasi.clear();
        dataMutasi.addAll(data);
        this.context =context;
    }

    @NonNull
    @Override
    public MutasiAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemMutasiBinding binding = ItemMutasiBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Holder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull MutasiAdapter.Holder holder, int position) {
        holder.showdata(dataMutasi.get(position));
        holder.itemView.setOnClickListener(v->{
           Intent detil = new Intent(context, DetilMutasiActivity.class);
           detil.putExtra("ID", dataMutasi.get(position));
          holder.itemView.getContext().startActivity(detil);
       });
        }

    @Override
    public int getItemCount() {
        return dataMutasi.size();
    }

    
    public class Holder extends RecyclerView.ViewHolder {
        ItemMutasiBinding binding;
        long tanggalLong;
        Date tanngaldate;

        public Holder(@NonNull ItemMutasiBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void showdata(Mutasi data) {
            tanggalLong = dateToMilis(data.getTanggal());
            tanngaldate = longToDate(tanggalLong);
            binding.tvtanggal.setText(Tanggal.dateFormatLocal1.format(tanngaldate));
            binding.etHarga.setText(FormatAngka.token(FormatAngka.format(data.getHarga())));
            binding.admin.setText(data.getNama_admin());
        }



        private Long dateToMilis(String data) {
            Calendar calendar  =Calendar.getInstance();
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
    }
    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
