package com.example.userbanksampah.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userbanksampah.model.Mutasi;
import com.example.userbanksampah.model.MutasiPenarikan;
import com.example.userbanksampah.retrofit.RetrofitImpl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MutasiPenarikanViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<MutasiPenarikan>> _data = new MutableLiveData<>();
    public LiveData<ArrayList<MutasiPenarikan>> data =_data;

    private final MutableLiveData<Boolean> _loading =new MutableLiveData<>();
    public LiveData<Boolean> loading =_loading;

    private final MutableLiveData<String> _pesanError =new MutableLiveData<>();
    public LiveData<String> pesanError =_pesanError;

    private final MutableLiveData<Date> _dataTanggal = new MutableLiveData<>();
    public LiveData<Date> dataTanggal =_dataTanggal;

    private final MutableLiveData<Date> _dataTanggalAkhir = new MutableLiveData<>();
    public LiveData<Date> dataTanggalAkhir =_dataTanggalAkhir;

    public LiveData<Date> getTanggalAwal(){
        Calendar calendar =  Calendar.getInstance();
        calendar.get(Calendar.YEAR);
        calendar.set(Calendar.MONTH,0);
        calendar.set(Calendar.DAY_OF_MONTH,1);
        Date date = calendar.getTime();
        _dataTanggal.setValue(date);
        return dataTanggal;
    }

    public LiveData<Date> getTanggalAkhir(){
        Calendar calendar =  Calendar.getInstance();
        calendar.get(Calendar.YEAR);
        calendar.get(Calendar.MONTH);
        calendar.get(Calendar.DAY_OF_MONTH);
        Date date = calendar.getTime();
        _dataTanggalAkhir.setValue(date);
        return dataTanggalAkhir;
    }

    public void getMutasi (String tglAwal,String tglAkhir,String id){
        _loading.setValue(true);
        RetrofitImpl.loginrequest().Mutasipenarikan(tglAwal,tglAkhir,id).enqueue(new Callback<ArrayList<MutasiPenarikan>>() {
            @Override
            public void onResponse(Call<ArrayList<MutasiPenarikan>> call, Response<ArrayList<MutasiPenarikan>> response) {
                if (response.isSuccessful()){
                    _loading.setValue(false);
                    _data.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MutasiPenarikan>> call, Throwable t) {
                _loading.setValue(false);
                _pesanError.setValue(t.getMessage());
            }
        });

    }
}
