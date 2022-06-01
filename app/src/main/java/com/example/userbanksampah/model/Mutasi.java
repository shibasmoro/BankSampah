package com.example.userbanksampah.model;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
public class Mutasi implements Parcelable {

    @SerializedName("harga")
    private int harga;


    @SerializedName("tgl_setor")
    private String tanggal;

    @SerializedName("id_setor")
    private String id_setor;

    @SerializedName("nama_nasabah")
    private String nama_nasabah;

    @SerializedName("nama_admin")
    private String nama_admin;


    protected Mutasi(Parcel in) {
        harga = in.readInt();
        tanggal = in.readString();
        id_setor = in.readString();
        nama_nasabah = in.readString();
        nama_admin = in.readString();
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


    public String getTanggal() {
        return tanggal;
    }

    public String getId_setor() {
        return id_setor;
    }

    public String getNama_nasabah() {
        return nama_nasabah;
    }

    public String getNama_admin() {
        return nama_admin;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(harga);

        parcel.writeString(tanggal);
        parcel.writeString(id_setor);
        parcel.writeString(nama_nasabah);
        parcel.writeString(nama_admin);
    }
}
