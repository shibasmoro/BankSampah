package com.example.userbanksampah;



import retrofit2.Call;

import retrofit2.http.GET;

import retrofit2.http.Query;

public interface Nasabah {

        @GET("bank_sampah/login.php")
        Call<String> validate_login(@Query("id_nasabah") String id_nasabah, @Query("password") String password);

}
