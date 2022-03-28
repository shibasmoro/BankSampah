package com.example.userbanksampah.ViewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userbanksampah.Model.DetilMutasi;
import com.example.userbanksampah.Model.Mutasi;
import com.example.userbanksampah.Retrofit.RetrofitImpl;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MutasiViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Mutasi>> _data = new MutableLiveData<>();
    public LiveData<ArrayList<Mutasi>> data =_data;

    private MutableLiveData<Boolean> _loading =new MutableLiveData<>();
    public LiveData<Boolean> loading =_loading;

    private MutableLiveData<DetilMutasi> _transaction =new MutableLiveData<>();
    public LiveData<DetilMutasi> transaction =_transaction;

    public void getData(String id){
        _loading.setValue(true);
        RetrofitImpl.loginrequest().mutasi(id).enqueue(new Callback<ArrayList<Mutasi>>() {
            @Override
            public void onResponse(Call<ArrayList<Mutasi>> call, Response<ArrayList<Mutasi>> response) {
                if (response.isSuccessful()){
                    _loading.setValue(false);
                    _data.setValue(response.body());
                    //Log.d("Mutasi", "onResponse: "+_data);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Mutasi>> call, Throwable t) {

            }
        });
    }

    public void getMoney(String id){
      RetrofitImpl.loginrequest().your_transaction(id).enqueue(new Callback<DetilMutasi>() {
          @Override
          public void onResponse(Call<DetilMutasi> call, Response<DetilMutasi> response) {
              if (response.isSuccessful()){
                  _transaction.setValue(response.body());
                  Log.d("Mutasi", "money: "+response.body().getHarga());
              }
          }
          @Override
          public void onFailure(Call<DetilMutasi> call, Throwable t) {
              Log.d("Mutasi", "onFailure:Gagal ");
          }
      });
    }
    public void data(){
        Log.d("Mutasi", "data:berhasil ");
    }
}
