package com.example.userbanksampah;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StaticRvAdapter extends RecyclerView.Adapter<StaticRvAdapter.StaticRVViewHolder> {

    private final ArrayList<StaticRvModel> items;
    int row = -1;
    UpdateRecyclerView updateRecyclerView;
    Activity activity;
    boolean check = true;
    boolean select = true;

    public StaticRvAdapter(ArrayList<StaticRvModel> items,Activity activity, UpdateRecyclerView updateRecyclerView) {
        this.items = items;
        this.activity= activity;
        this.updateRecyclerView = updateRecyclerView;
    }

    @NonNull
    @Override
    public StaticRVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.static_rv_item,parent,false);
        StaticRVViewHolder staticRVViewHolder = new StaticRVViewHolder(view);
        return staticRVViewHolder;
    }


    public void onBindViewHolder(@NonNull StaticRVViewHolder holder, final int position) {
        StaticRvModel currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImage());
        holder.textView.setText(currentItem.getText());

        if (check) {
            ArrayList<DynamicRvModel> items = new ArrayList<DynamicRvModel>();
            items.add(new DynamicRvModel("Sampah", "Harga Sampah"));

            updateRecyclerView.callback(position, items);
            check = false;
        }

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row = position;
                notifyDataSetChanged();

                if (position == 0) {
                    ArrayList<DynamicRvModel> items = new ArrayList<DynamicRvModel>();
                    items.add(new DynamicRvModel("Majalah", "Rp. 25000"));
                    items.add(new DynamicRvModel("Buku ", "25000"));
                    items.add(new DynamicRvModel("Kertas Minyak", "25000"));

                    updateRecyclerView.callback(position, items);
                } else if (position == 1) {
                    ArrayList<DynamicRvModel> items = new ArrayList<DynamicRvModel>();
                    items.add(new DynamicRvModel("Besi Tua", "25000"));
                    items.add(new DynamicRvModel("Besi Bakar", "25000"));
                    items.add(new DynamicRvModel("Besi Peralatan", "25000"));
                    updateRecyclerView.callback(position, items);

                } else if (position == 2) {
                    ArrayList<DynamicRvModel> items = new ArrayList<DynamicRvModel>();
                    items.add(new DynamicRvModel("Logam Mulia", "25000"));
                    items.add(new DynamicRvModel("Logam Silver ", "25000"));
                    items.add(new DynamicRvModel("Tembaga Merah", "25000"));
                    items.add(new DynamicRvModel("Seng", "25000"));
                    updateRecyclerView.callback(position, items);

                } else if (position == 3) {
                    ArrayList<DynamicRvModel> items = new ArrayList<DynamicRvModel>();
                    items.add(new DynamicRvModel("Botol Beling", "25000"));
                    items.add(new DynamicRvModel("Aki", "25000"));
                    items.add(new DynamicRvModel("Minyak Jelantah", "25000"));
                    updateRecyclerView.callback(position, items);

                }

            }
        });

        if (select) {
            if (position == 0)
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected);
            select = false;
        } else {
            if (row == position) {
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_selected);
            } else {
                holder.linearLayout.setBackgroundResource(R.drawable.static_rv_bg);
            }
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class StaticRVViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView imageView;
        LinearLayout linearLayout;

        public StaticRVViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.textview);
            linearLayout = itemView.findViewById(R.id.linearLayout);
        }
    }

}
