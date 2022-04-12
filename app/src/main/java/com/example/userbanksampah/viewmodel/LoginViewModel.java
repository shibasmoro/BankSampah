package com.example.userbanksampah.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.userbanksampah.model.Nasabah;
import com.example.userbanksampah.retrofit.RetrofitImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private MutableLiveData<Nasabah> _nasabah =new MutableLiveData<>();
    public LiveData<Nasabah> nasabah =_nasabah;
    private MutableLiveData<String> _message =new MutableLiveData<>();
    public LiveData<String> message =_message;

    public void login(String username, String password){
        RetrofitImpl.loginrequest().validate_login(username,password).enqueue(new Callback<Nasabah>() {
            @Override
            public void onResponse(Call<Nasabah> call, Response<Nasabah> response) {
                if (response.isSuccessful()){
                   _nasabah.setValue(response.body());

                    }
                }
            @Override
            public void onFailure(Call<Nasabah> call, Throwable t) {
                String nama = t.getMessage();
                _message.setValue("Terdapat Eror");
                Log.d("Login", "onFailure: "+nama);
            }
        });

    }

}
