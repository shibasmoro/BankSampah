package com.example.userbanksampah.retrofit;



import com.example.userbanksampah.BuildConfig;
import com.example.userbanksampah.retrofit.Inter.RetrofitConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitImpl {
    private static Retrofit retrofit;
    private static final String Base_url =BuildConfig.BASE_URL;


    public static RetrofitConfig loginrequest() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(RetrofitConfig.class);

    }

    public static RetrofitConfig show_saldo() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Base_url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        return retrofit.create(RetrofitConfig.class);
    }
}
