package com.example.userbanksampah.viewmodel;
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
    public LiveData<Integer> data = _data;

    private final MutableLiveData<Boolean> _loading = new MutableLiveData<>();
    public LiveData<Boolean> loading = _loading;

    private final MutableLiveData<String> _pesanError = new MutableLiveData<>();
    public LiveData<String> pesanError = _pesanError;

    public void getSaldo(String id) {
        _loading.setValue(true);
        RetrofitImpl.show_saldo().your_saldo(id).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()) {
                    _data.setValue(response.body());
                    _loading.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                _loading.setValue(false);
                _pesanError.setValue(t.getMessage());
            }
        });
    }




}
