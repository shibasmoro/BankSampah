package com.example.userbanksampah.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.userbanksampah.model.DetilMutasi;
import com.example.userbanksampah.model.Mutasi;
import com.example.userbanksampah.retrofit.RetrofitImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MutasiViewModel extends ViewModel {

    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd-MM-yyyy");

    private final MutableLiveData<ArrayList<Mutasi>> _data = new MutableLiveData<>();
    public LiveData<ArrayList<Mutasi>> data =_data;

    private final MutableLiveData<Date> _dataTanggal = new MutableLiveData<>();
    public LiveData<Date> dataTanggal =_dataTanggal;

    private final MutableLiveData<Date> _dataTanggalAkhir = new MutableLiveData<>();
    public LiveData<Date> dataTanggalAkhir =_dataTanggalAkhir;

    private final MutableLiveData<Boolean> _loading =new MutableLiveData<>();
    public LiveData<Boolean> loading =_loading;

    private final MutableLiveData<DetilMutasi> _transaction =new MutableLiveData<>();
    public LiveData<DetilMutasi> transaction =_transaction;

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

    public void getData(String awal,String akhir,String id){
        _loading.setValue(true);
        RetrofitImpl.loginrequest().mutasi(awal,akhir,id).enqueue(new Callback<ArrayList<Mutasi>>() {
            @Override
            public void onResponse(Call<ArrayList<Mutasi>> call, Response<ArrayList<Mutasi>> response) {
                if (response.isSuccessful()){
                    _loading.setValue(false);
                    _data.setValue(response.body());
                    Log.d("Mutasi", "onResponse: ");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Mutasi>> call, Throwable t) {

            }
        });
    }


    public void data(){
        Log.d("Mutasi", "data:berhasil ");
    }
}
