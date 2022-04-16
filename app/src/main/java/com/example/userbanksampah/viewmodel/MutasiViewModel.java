package com.example.userbanksampah.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userbanksampah.model.DetilMutasi;
import com.example.userbanksampah.model.Mutasi;
import com.example.userbanksampah.retrofit.RetrofitImpl;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MutasiViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<Mutasi>> _data = new MutableLiveData<>();
    public LiveData<ArrayList<Mutasi>> data =_data;

    private final MutableLiveData<Boolean> _loading =new MutableLiveData<>();
    public LiveData<Boolean> loading =_loading;

    private final MutableLiveData<DetilMutasi> _transaction =new MutableLiveData<>();
    public LiveData<DetilMutasi> transaction =_transaction;

    public void getData(String awal,String akhir,String id){
        _loading.setValue(true);
        RetrofitImpl.loginrequest().mutasi(awal,akhir,id).enqueue(new Callback<ArrayList<Mutasi>>() {
            @Override
            public void onResponse(Call<ArrayList<Mutasi>> call, Response<ArrayList<Mutasi>> response) {
                if (response.isSuccessful()){
                    _loading.setValue(false);
                    _data.setValue(response.body());
                    Log.d("Mutasi", "onResponse: ");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Mutasi>> call, Throwable t) {

            }
        });
    }


    public void data(){
        Log.d("Mutasi", "data:berhasil ");
    }
}
