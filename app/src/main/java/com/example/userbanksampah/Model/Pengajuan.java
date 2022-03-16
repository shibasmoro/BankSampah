package com.example.userbanksampah.Model;

import com.google.gson.annotations.SerializedName;

public class Pengajuan
{
    @SerializedName("data")
    private int persen;
    @SerializedName("saldo")
    private int saldo;
    @SerializedName("min_saldo")
    private int min_saldo;

    public int getSaldo() {
        return saldo;
    }

    public int getPersen() {
        return persen;
    }

    public int getMin_saldo() {
        return min_saldo;
    }


}
