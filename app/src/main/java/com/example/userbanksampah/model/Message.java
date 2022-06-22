package com.example.userbanksampah.model;

import com.google.gson.annotations.SerializedName;

public class Message {
    @SerializedName("message")
    private String pesan;

    public String getPesan() {
        return pesan;
    }
}
