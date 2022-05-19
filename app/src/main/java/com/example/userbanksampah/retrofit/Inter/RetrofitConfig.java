package com.example.userbanksampah.retrofit.Inter;

import com.example.userbanksampah.model.DetilMutasi;
import com.example.userbanksampah.model.KategoriSampah;
import com.example.userbanksampah.model.Mutasi;
import com.example.userbanksampah.model.Nasabah;
import com.example.userbanksampah.model.Pengajuan;
import com.example.userbanksampah.model.Sampah;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitConfig {

    @GET("login.php")
    Call<Nasabah> validate_login(@Query("id_nasabah") String id, @Query("password") String password);

    @GET("get_saldo.php")
    Call<Integer> your_saldo(@Query("id_nasabah") String id);

    @GET("check_insert_pengajuan_nasabah.php")
    Call<Integer> check_ajuan (@Query("param") String id);

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

    @FormUrlEncoded
    @POST(" insert_pengajuan_nasabah.php")
    Call<String> addPengajuan(
            @Field("id") String id_ajuan,
            @Field("nasabah") String id_nasabah,
            @Field("jumlah") int jumlah,
            @Field("tanggal_ajuan") String tanggal
    );

}
