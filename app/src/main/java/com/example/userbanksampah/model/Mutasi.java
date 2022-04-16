package com.example.userbanksampah.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Mutasi implements Parcelable {

    @SerializedName("harga")
    private int harga;

    @SerializedName("nama")
    private String admin;

    @SerializedName("tgl_setor")
    private String tanggal;

    @SerializedName("id_setor")
    private String id_setor;

    protected Mutasi(Parcel in) {
        harga = in.readInt();
        admin = in.readString();
        tanggal = in.readString();
        id_setor = in.readString();
    }

    public static final Creator<Mutasi> CREATOR = new Creator<Mutasi>() {
        @Override
        public Mutasi createFromParcel(Parcel in) {
            return new Mutasi(in);
        }

        @Override
        public Mutasi[] newArray(int size) {
            return new Mutasi[size];
        }
    };

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getId_setor() {
        return id_setor;
    }

    public void setId_setor(String id_setor) {
        this.id_setor = id_setor;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(harga);
        parcel.writeString(admin);
        parcel.writeString(tanggal);
        parcel.writeString(id_setor);
    }
}
