package com.example.userbanksampah.model;

import com.google.gson.annotations.SerializedName;

public class Nasabah {
    @SerializedName("id_nasabah")
    private String id_nasabah;
    @SerializedName("alamat")
    private String alamat;
    @SerializedName("nama")
    private String nama;
    @SerializedName("no_telepon")
    private String no_telepon;
    @SerializedName("message")
    private String pesan ="";

    @SerializedName("password")
    private String password ="";

    public String getPassword() {
        return password;
    }

    public String getPesan() {
        return pesan;
    }

    public String getNo_telepon() {
        return no_telepon;
    }

    public String getId_nasabah() {

        return id_nasabah;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

}
