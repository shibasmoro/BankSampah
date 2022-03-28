package com.example.userbanksampah.Retrofit.Inter;

import com.example.userbanksampah.Model.DetilMutasi;
import com.example.userbanksampah.Model.Mutasi;
import com.example.userbanksampah.Model.Nasabah;
import com.example.userbanksampah.Model.Pengajuan;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitConfig {

    @GET("login.php")
    Call<Nasabah> validate_login(@Query("id_nasabah") String id, @Query("password") String password);

    @GET("get_saldo.php")
    Call<Integer> your_saldo(@Query("id_nasabah") String id);


    @GET("get_minimum.php")
    Call<Pengajuan> your_minimum(@Query("id_nasabah")String id);

    @GET("get_mutasi.php")
    Call<ArrayList<Mutasi>> mutasi(@Query("id_nasabah")String id);

    @GET("get_total.php")
    Call<DetilMutasi>your_transaction(@Query("id_nasabah") String id);

    // ini perubahan yang ada pada fitur
}
