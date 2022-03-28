package com.example.userbanksampah.Model;

import com.google.gson.annotations.SerializedName;

public class Mutasi {
    @SerializedName("nama_sampah")
    private String sampah;
    @SerializedName("total")
    private int total;
    @SerializedName("harga")
    private int harga;

    public String getSampah() {
        return sampah;
    }

    public int getTotal() {
        return total;
    }

    public int getHarga() {
        return harga;
    }
}
