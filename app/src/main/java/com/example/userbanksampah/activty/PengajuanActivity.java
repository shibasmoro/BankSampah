package com.example.userbanksampah.activty;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.userbanksampah.databinding.ActivityVerifyBinding;
import com.example.userbanksampah.util.FormatAngka;
import com.example.userbanksampah.util.PreferencesApp;
import com.example.userbanksampah.util.Tanggal;
import com.example.userbanksampah.viewmodel.VerifyViewModel;

public class PengajuanActivity extends AppCompatActivity {
    private VerifyViewModel model;
    private String id_param;
    private ActivityVerifyBinding binding;
    private int yourSaldo;
    private String current="";
    private String cleanString;
    private String format;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //PreferencesApp pref = new PreferencesApp(this);
        id_param = PreferencesApp.getStr(PreferencesApp.Id);

        model = new ViewModelProvider(this).get(VerifyViewModel.class);
        model.getSaldo(id_param);
        model.saldo.observe(this,saldo-> this.yourSaldo =saldo);

        binding.jumlahAjuan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String data =charSequence.toString();
                if (!data.equals(current)){
                    binding.jumlahAjuan.removeTextChangedListener(this);
                    cleanString = data.replaceAll("[Rp. ]","");
                        if (!cleanString.isEmpty()){
                            format=FormatAngka.token(FormatAngka.format(Double.parseDouble(cleanString)));
                            current =format;
                            binding.jumlahAjuan.setText(format);
                        }else{
                            current="";
                            binding.jumlahAjuan.setText("");
                        }
                    binding.jumlahAjuan.setSelection(current.length());
                    binding.jumlahAjuan.addTextChangedListener(this);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        binding.verifikasi.setOnClickListener(view -> {
            if (cleanString.isEmpty()){
                showToast("Masukan jumlah yang di inginkan");
            }else{
                if (Integer.parseInt(cleanString) > this.yourSaldo){
                    showToast("Saldo Anda Tidak mencukupi");
                }else{
                    StringBuilder data = new StringBuilder(id_param);
                    data.append(Tanggal.getCurrentDateId());
                    //showToast(data.toString());
                    model.addPengajuan( id_param, Integer.parseInt(cleanString), Tanggal.getCurrentDate(),data.toString());
                }
            }
        });

        model.pesan.observe(PengajuanActivity.this, data -> {
            showToast(data);
            if (data.contains("berhasil")) {
                finish();
            }
        });

        model.pesanEror.observe(PengajuanActivity.this, this::showToast);
    }

    public void backbutton(View view) {
        startActivity(new Intent(this, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}