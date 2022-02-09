package com.example.userbanksampah;

import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class NasabahImpl {
    private static  final  String Base = "https://ublmobilekmmi.web.id/";
    private static Retrofit retrofit;

    public static Nasabah login(){
        retrofit = new Retrofit.Builder()
                .baseUrl(Base)
        .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        return  retrofit.create(Nasabah.class);
    }
}
