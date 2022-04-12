package com.example.userbanksampah.activty;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.userbanksampah.databinding.ActivityHistoryBinding;
import com.example.userbanksampah.model.Mutasi;
import com.example.userbanksampah.R;
import com.example.userbanksampah.viewmodel.MutasiViewModel;
import com.example.userbanksampah.adapter.MutasiAdapter;
import com.example.userbanksampah.util.PreferencesApp;
import com.example.userbanksampah.util.Tahun;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private ActivityHistoryBinding Binding;
    private final String[]bulanDalamAngka ={
            "01","02","03","04","05","06",
            "07","08","09","10","11","12"
    };
    // sebagai param buat cek mutasi
    String temp;
    String result;
    String resultSend;
    private final StringBuilder builder = new StringBuilder();
    private final NumberFormat formatter = new DecimalFormat("#,###");

    private MutasiViewModel mutasiModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PreferencesApp data = new PreferencesApp(this);
        super.onCreate(savedInstanceState);
        Binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        mutasiModel =new ViewModelProvider(this).get(MutasiViewModel.class);

        builder.append(PreferencesApp.getStr(PreferencesApp.Id));
        builder.append(Tahun.this_year);
        temp =builder.toString();

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.bulan));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Binding.spinnerYear.setAdapter(adapter);
        Binding.spinnerYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               switch (i){
                   case 0: case 1: case 2: case 3: case 4: case 5: case 6:
                   case 7: case 8: case 9: case 10: case 11:
                     result =temp+bulanDalamAngka[i];
                     resultSend =result;
                     result ="";
                   break;
                   default:showToast("yang bener dong");
               }
           }
           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {
           }
       });

        Log.d("history", "onCreate: "+temp);
        Binding.rvMutasi.setHasFixedSize(false);
        Binding.btnCekMutasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showToast(resultSend);

                mutasiModel.getData(resultSend);
                mutasiModel.getMoney(resultSend);
                mutasiModel.data();

                mutasiModel.loading.observe(HistoryActivity.this,data->{
                    showLoading(data);
                });
                mutasiModel.data.observe(HistoryActivity.this,response->{

                    if (response.isEmpty()){
                        showData(response);
                        Binding.notFound.setVisibility(View.VISIBLE);
                        Binding.notFound.setText("Tidak Ada Riwayat Transaksi");
                    }else{
                        Binding.notFound.setVisibility(View.GONE);
                        showData(response);
                    }

                });

                mutasiModel.transaction.observe(HistoryActivity.this,data->{
                    if (data.getHarga()>0){
                        Binding.total.setText(""+formatter.format(data.getHarga()));
                        Binding.adminTugas.setText(data.getNama_admin());
                    }else{
                        Binding.total.setText("");
                        Binding.adminTugas.setText("");
                    }

                });

            }
        });

    }
    public void showToast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    public void showData(ArrayList<Mutasi> paramData){
        Binding.rvMutasi.setLayoutManager(new LinearLayoutManager(this));
        MutasiAdapter adapter = new MutasiAdapter(paramData);
        Binding.rvMutasi.setAdapter(adapter);

    }
    public void showLoading(Boolean isLoad){
        if (isLoad){
            Binding.progressBar.setVisibility(View.VISIBLE);
        }else{
            Binding.progressBar.setVisibility(View.GONE);
        }
    }
}