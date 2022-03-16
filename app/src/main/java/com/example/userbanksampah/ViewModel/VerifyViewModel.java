package com.example.userbanksampah.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userbanksampah.Model.Pengajuan;
import com.example.userbanksampah.Retrofit.RetrofitImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyViewModel extends ViewModel {
    private static final String TAG = "VerifyViewModel";

    private Pengajuan pengajuan = new Pengajuan();

    private MutableLiveData<Integer> minimal = new MutableLiveData<>();
    public LiveData<Integer> _minimal = minimal;





    public void getMaxTransaction(String id){
       RetrofitImpl.loginrequest().your_minimum(id).enqueue(new Callback<Pengajuan>() {
           @Override
           public void onResponse(Call<Pengajuan> call, Response<Pengajuan> response) {
               pengajuan = response.body();
               minimal.setValue(pengajuan.getSaldo() - ((pengajuan.getSaldo()*pengajuan.getPersen())/100));
           }

           @Override
           public void onFailure(Call<Pengajuan> call, Throwable t) {

           }
       });
    }

    public void getMaxTransaction(){
       //max_transaction;
    }


}
