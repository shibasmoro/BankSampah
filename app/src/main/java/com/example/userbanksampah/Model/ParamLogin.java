package com.example.userbanksampah.Model;

public class ParamLogin {
    private String id_nasabah;
    private String password;

    public ParamLogin(String id_nasabah, String password) {
        this.id_nasabah = id_nasabah;
        this.password = password;
    }

    public String getId_nasabah() {
        return id_nasabah;
    }

    public void setId_nasabah(String id_nasabah) {
        this.id_nasabah = id_nasabah;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
