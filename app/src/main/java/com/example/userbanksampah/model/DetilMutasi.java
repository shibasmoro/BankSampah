package com.example.userbanksampah.model;

import com.google.gson.annotations.SerializedName;

public class DetilMutasi  {
    @SerializedName("nama_sampah")
    private String sampah;

    @SerializedName("harga")
    private int harga;

    @SerializedName("total")
    private float total;

    public String getSampah() {
        return sampah;
    }

    public int getHarga() {
        return harga;
    }

    public float getTotal() {
        return total;
    }
}
