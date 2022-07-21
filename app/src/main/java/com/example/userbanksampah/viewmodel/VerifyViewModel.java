package com.example.userbanksampah.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.userbanksampah.retrofit.RetrofitImpl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyViewModel extends ViewModel {


    private final MutableLiveData<String> _pesan = new MutableLiveData<>();
    public  LiveData<String> pesan= _pesan;

    private final MutableLiveData<String> _pesanEror = new MutableLiveData<>();
    public  LiveData<String> pesanEror= _pesanEror;

    private final MutableLiveData<Integer> _saldo = new MutableLiveData<>();
    public  LiveData<Integer> saldo= _saldo;


    public void addPengajuan(String id_nasabah,int jumlah_ajuna,String tanggal_ajuan,String id_pengajuan){
        RetrofitImpl.show_saldo().addPengajuan(id_nasabah,jumlah_ajuna,tanggal_ajuan,id_pengajuan).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && response.body() != null){
                    _pesan.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                _pesanEror.setValue("Tedapat Kesalahan pada sistem");
            }
        });
    }

    public void getSaldo(String id_nasabah){
        RetrofitImpl.show_saldo().your_minimum(id_nasabah).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()){
                    _saldo.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                _pesanEror.setValue("Tedapat Kesalahan pada sistem");
            }
        });
    }


}
