package com.example.userbanksampah.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userbanksampah.retrofit.Inter.RetrofitConfig;
import com.example.userbanksampah.retrofit.RetrofitImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {
    private final MutableLiveData<Boolean> _loading = new MutableLiveData<>();
    public LiveData<Boolean> loading = _loading;

    private final MutableLiveData<String> _pesan = new MutableLiveData<>();
    public LiveData<String> pesan = _pesan;

    public void updateData(String nama,String no_telp,String alamat,String id){
        RetrofitImpl.show_saldo().updateProfile(nama,no_telp,alamat,id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                _loading.setValue(true);
                if (response.isSuccessful() && response.body() != null){
                    _pesan.setValue(response.body());

                }else{
                    _pesan.setValue("data tidak ada");
                }
                _loading.setValue(false);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                _pesan.setValue(t.getMessage());
                _loading.setValue(false);
            }
        });
    }
}
