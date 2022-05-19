package com.example.userbanksampah.viewmodel;



import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userbanksampah.model.DetilMutasi;

import com.example.userbanksampah.retrofit.RetrofitImpl;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetilMutasimodel extends ViewModel {

    private final MutableLiveData<ArrayList<DetilMutasi>> _data = new MutableLiveData<>();
    public LiveData<ArrayList<DetilMutasi>> data =_data;

    private final MutableLiveData<Boolean> _loading =new MutableLiveData<>();
    public LiveData<Boolean> loading =_loading;

    private final MutableLiveData<String> _pesanError =new MutableLiveData<>();
    public LiveData<String> pesanEror =_pesanError;


    public void getDetilMutasi(String id){
        _loading.setValue(true);
        RetrofitImpl.loginrequest().your_transaction(id).enqueue(new Callback<ArrayList<DetilMutasi>>() {
            @Override
            public void onResponse(Call<ArrayList<DetilMutasi>> call, Response<ArrayList<DetilMutasi>> response) {
                if (response.isSuccessful()){
                    _data.setValue(response.body());
                }
                _loading.setValue(false);
            }

            @Override
            public void onFailure(Call<ArrayList<DetilMutasi>> call, Throwable t) {
                _pesanError.setValue(t.getMessage());
            }
        });
    }
}
