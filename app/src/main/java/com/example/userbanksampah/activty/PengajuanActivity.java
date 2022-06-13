package com.example.userbanksampah.activty;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.userbanksampah.R;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //PreferencesApp pref = new PreferencesApp(this);
        id_param = PreferencesApp.getStr(PreferencesApp.Id);

        model = new ViewModelProvider(this).get(VerifyViewModel.class);
        model.getSaldo(id_param);
        model.saldo.observe(this,saldo->{
            this.yourSaldo =saldo;
        });


        binding.verifikasi.setOnClickListener(view -> {
            String nominal = binding.nama.getText().toString();
            if (Integer.parseInt(nominal) > this.yourSaldo ){
                showToast("Saldo anda tidak mencukupi");
            }else{
                model.addPengajuan(Tanggal.epochTime(), id_param, Integer.parseInt(nominal), Tanggal.getCurrentDate());
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