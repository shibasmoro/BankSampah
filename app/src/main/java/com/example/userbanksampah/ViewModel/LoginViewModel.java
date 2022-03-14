package com.example.userbanksampah.ViewModel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.userbanksampah.Model.Nasabah;
import com.example.userbanksampah.Retrofit.RetrofitImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private final MutableLiveData<Nasabah> _nasabah =new MutableLiveData<>();
    public LiveData<Nasabah> getNasabah(){
        return _nasabah;
    }

    public LoginViewModel() {

    }

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

            }
        });

    }

}
