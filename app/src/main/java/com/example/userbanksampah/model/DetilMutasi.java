package com.example.userbanksampah.model;

import com.google.gson.annotations.SerializedName;

public class DetilMutasi  {
    @SerializedName("nama_sampah")
    private String sampah;

    @SerializedName("harga_nasabah")
    private int harga_nasabah;

    @SerializedName("total")
    private float total;

    public String getSampah() {
        return sampah;
    }

    public int getHarga() {
        return harga_nasabah;
    }

    public float getTotal() {
        return total;
    }
}
