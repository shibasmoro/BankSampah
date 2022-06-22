package com.example.userbanksampah.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userbanksampah.model.Message;
import com.example.userbanksampah.retrofit.RetrofitImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    private MutableLiveData<Message> _message =new MutableLiveData<>();
    public LiveData<Message> message =_message;

    private MutableLiveData<String> _eror =new MutableLiveData<>();
    public LiveData<String> eror =_eror;

    private MutableLiveData<Boolean> _loading =new MutableLiveData<>();
    public LiveData<Boolean> loading =_loading;

    public void register(String nama,String alamat ,String password,String notelp){
        _loading.setValue(true);
        RetrofitImpl.loginrequest().addUser(nama,alamat,password,notelp).enqueue(new Callback<Message>() {
            @Override
            public void onResponse(Call<Message> call, Response<Message> response) {
                if (response.isSuccessful()){
                    _message.setValue(response.body());
                    _loading.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<Message> call, Throwable t) {
                _eror.setValue(t.getMessage());
                _loading.setValue(false);
            }
        });

    }
}
