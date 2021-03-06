package com.example.userbanksampah.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userbanksampah.R;
import com.example.userbanksampah.databinding.DetailItemSampahBinding;
import com.example.userbanksampah.model.DetilMutasi;
import com.example.userbanksampah.util.FormatAngka;
import com.example.userbanksampah.util.Tanggal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class DetilMutasiAdapter extends RecyclerView.Adapter<DetilMutasiAdapter.Holder> {

    private ArrayList<DetilMutasi> detilMutasi = new ArrayList<>();
    private Context context;

    public DetilMutasiAdapter(Context context, ArrayList<DetilMutasi> data) {
        this.detilMutasi.clear();
        this.detilMutasi.addAll(data);
        this.context = context;
    }

    @NonNull
    @Override
    public DetilMutasiAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DetailItemSampahBinding binding = DetailItemSampahBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new Holder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DetilMutasiAdapter.Holder holder, int position) {
        DetilMutasi detil = detilMutasi.get(position);
        holder.binding.namaSampah.setText(detil.getSampah());
        holder.binding.jumlahPengaju.setText(FormatAngka.token(FormatAngka.format(detil.getHarga())));
        if (detil.getSampah().equals("Minyak")){
            holder.binding.bobotPengajuan.setText(holder.itemView.getContext().getString(R.string.bobot_detail2,String.valueOf(detil.getTotal())));
        }else{
            holder.binding.bobotPengajuan.setText(holder.itemView.getContext().getString(R.string.bobot_detail,String.valueOf(detil.getTotal())));
        }

    }

    @Override
    public int getItemCount() {
        return detilMutasi.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        DetailItemSampahBinding binding;

        public Holder(@NonNull DetailItemSampahBinding data) {
            super(data.getRoot());
            this.binding = data;
        }
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
    public void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
