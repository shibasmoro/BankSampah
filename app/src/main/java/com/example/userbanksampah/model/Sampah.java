package com.example.userbanksampah.model;

import com.google.gson.annotations.SerializedName;

public class Sampah {
    @SerializedName("nama_sampah")
    private String sampah;
    @SerializedName("harga_nasabah")
    private int harga;

    public String getSampah() {
        return sampah;
    }

    public int getHarga() {
        return harga;
    }
}
