package com.example.userbanksampah.Model;

import com.google.gson.annotations.SerializedName;

public class Nasabah {


    @SerializedName("id_nasabah")
    private String id_nasabah;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("nama")
    private String nama;

    public String getId_nasabah() {
        return id_nasabah;
    }

    public void setId_nasabah(String id_nasabah) {
        this.id_nasabah = id_nasabah;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }



}
