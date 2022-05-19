package com.example.userbanksampah.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userbanksampah.model.Pengajuan;
import com.example.userbanksampah.retrofit.RetrofitImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyViewModel extends ViewModel {


    private Pengajuan pengajuan = new Pengajuan();

    private final MutableLiveData<Integer> minimal = new MutableLiveData<>();
    public  LiveData<Integer> _minimal = minimal;

    private final MutableLiveData<String> _pesan = new MutableLiveData<>();
    public  LiveData<String> pesan= _pesan;

    private final MutableLiveData<String> _pesanEror = new MutableLiveData<>();
    public  LiveData<String> pesanEror= _pesanEror;

    public void getMaxTransaction(String id){
       RetrofitImpl.loginrequest().your_minimum(id).enqueue(new Callback<Pengajuan>() {
           @Override
           public void onResponse(Call<Pengajuan> call, Response<Pengajuan> response) {

               if (response.isSuccessful()){
                   pengajuan = response.body();
                   minimal.setValue(pengajuan.getSaldo() - ((pengajuan.getSaldo()*pengajuan.getPersen())/100));
               }

           }

           @Override
           public void onFailure(Call<Pengajuan> call, Throwable t) {
            _pesanEror.setValue("Tedapat Kesalahan pada sistem");
           }
       });
    }
    public void addPengajuan(String id_pengajuan,String id_nasabah,int jumlah_ajuna,String tanggal_ajuan){
        RetrofitImpl.show_saldo().addPengajuan(id_pengajuan,id_nasabah,jumlah_ajuna,tanggal_ajuan).enqueue(new Callback<String>() {
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


}
