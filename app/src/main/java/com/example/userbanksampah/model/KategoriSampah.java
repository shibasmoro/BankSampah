package com.example.userbanksampah.model;

import com.google.gson.annotations.SerializedName;

public class KategoriSampah {
    @SerializedName("id_kategori")
    private String id;
    @SerializedName("deskripsi")
    private String kategori;


    public String getId() {
        return id;
    }

    public String getKategori() {
        return kategori;
    }
}
