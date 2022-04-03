package com.example.userbanksampah.model;

import com.google.gson.annotations.SerializedName;

public class DetilMutasi {
    @SerializedName("nama")
    private String nama_admin;

    @SerializedName("harga")
    private int harga;

    public String getNama_admin() {
        return nama_admin;
    }

    public int getHarga() {
        return harga;
    }
}
