package com.example.userbanksampah.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userbanksampah.model.KategoriSampah;
import com.example.userbanksampah.model.Sampah;
import com.example.userbanksampah.retrofit.RetrofitImpl;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeviewModel extends ViewModel {
    private final MutableLiveData<Integer> _data = new MutableLiveData<>();
    public  LiveData<Integer> data =_data;

    private final MutableLiveData<ArrayList<KategoriSampah>> _dataKategori  = new MutableLiveData<>();
    public LiveData<ArrayList<KategoriSampah>>dataKategori =_dataKategori;

    private final MutableLiveData<ArrayList<Sampah>> _dataSampah  = new MutableLiveData<>();
    public LiveData<ArrayList<Sampah>>dataSampah =_dataSampah;

    private final MutableLiveData<Boolean> _loading =new MutableLiveData<>();
    public LiveData<Boolean> loading =_loading;


    public void getKategori(){
       RetrofitImpl.loginrequest().kategoriSampah().enqueue(new Callback<ArrayList<KategoriSampah>>() {
           @Override
           public void onResponse(Call<ArrayList<KategoriSampah>> call, Response<ArrayList<KategoriSampah>> response) {
               if (response.isSuccessful()){
                   _dataKategori.setValue(response.body());
               }
           }

           @Override
           public void onFailure(Call<ArrayList<KategoriSampah>> call, Throwable t) {

           }
       });
    }

    public void getSaldo(String id){
        _loading.setValue(true);
        RetrofitImpl.show_saldo().your_saldo(id).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()){
                   _data.setValue(response.body());
                    _loading.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }

    public void getSampah(String id){
        RetrofitImpl.loginrequest().detailSampah(id).enqueue(new Callback<ArrayList<Sampah>>() {
            @Override
            public void onResponse(Call<ArrayList<Sampah>> call, Response<ArrayList<Sampah>> response) {
                if (response.isSuccessful()){
                    Log.d("HomeviewModel", "onResponse: "+response.body());
                    _dataSampah.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Sampah>> call, Throwable t) {

            }
        });
    }



}
