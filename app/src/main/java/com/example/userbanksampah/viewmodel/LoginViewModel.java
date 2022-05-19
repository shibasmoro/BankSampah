package com.example.userbanksampah.viewmodel;

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


    private MutableLiveData<Boolean> _loading =new MutableLiveData<>();
    public LiveData<Boolean> loading =_loading;

    public void login(String username, String password){
        _loading.setValue(true);
        RetrofitImpl.loginrequest().validate_login(username,password).enqueue(new Callback<Nasabah>() {
            @Override
            public void onResponse(Call<Nasabah> call, Response<Nasabah> response) {
                if (response.isSuccessful()){
                    _loading.setValue(false);
                   _nasabah.setValue(response.body());
                    }
                }
            @Override
            public void onFailure(Call<Nasabah> call, Throwable t) {
                _loading.setValue(false);
                _message.setValue(t.getMessage());
            }
        });

    }

}
