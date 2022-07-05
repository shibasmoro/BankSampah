package com.example.userbanksampah.model;

import com.google.gson.annotations.SerializedName;

public class MutasiPenarikan {
    @SerializedName("nama")
    private String nama;

    @SerializedName("tanggal_pengajuan")
    private String date;

    @SerializedName("jumlah")
    private int jumlah;

    public String getNama() {
        return nama;
    }

    public String getDate() {
        return date;
    }

    public int getJumlah() {
        return jumlah;
    }
}
