package com.example.userbanksampah.Retrofit.Inter;

import com.example.userbanksampah.Model.ParamLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitConfig {

    @GET("login.php")
    Call<String> validate_login(@Query("id_nasabah") String id,@Query("password") String password);

}
