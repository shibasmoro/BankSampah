package com.example.userbanksampah.retrofit.Inter;

import com.example.userbanksampah.model.DetilMutasi;
import com.example.userbanksampah.model.KategoriSampah;
import com.example.userbanksampah.model.Mutasi;
import com.example.userbanksampah.model.Nasabah;
import com.example.userbanksampah.model.Pengajuan;
import com.example.userbanksampah.model.Sampah;

import java.util.ArrayList;

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
    Call<ArrayList<Mutasi>> mutasi(@Query("tanggal_awal")String tanggalAwal ,@Query("tanggal_akhir") String tanngalAkhir, @Query("id_nasabah") String id_nasabah  );

    @GET("get_total.php")
    Call<ArrayList<DetilMutasi>>your_transaction(@Query("id_setoran") String id);

    @GET("get_kategori.php")
    Call<ArrayList<KategoriSampah>> kategoriSampah();

    @GET("getHargaSampah.php")
    Call<ArrayList<Sampah>> detailSampah(@Query("id_kategori")String id );

}
