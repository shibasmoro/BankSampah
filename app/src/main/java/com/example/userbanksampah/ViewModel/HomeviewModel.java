package com.example.userbanksampah.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userbanksampah.Retrofit.RetrofitImpl;
import com.example.userbanksampah.util.PreferencesApp;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeviewModel extends ViewModel {
    private MutableLiveData<Integer> _data = new MutableLiveData<>();
    public  LiveData<Integer> data =_data;

    public void getSaldo(String id){
        RetrofitImpl.show_saldo().your_saldo(id).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()){
                   _data.setValue(response.body());
                    Log.d("HomeviewModel", "onResponse: ");
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }



}
