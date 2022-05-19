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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //PreferencesApp pref = new PreferencesApp(this);
        id_param = PreferencesApp.getStr(PreferencesApp.Id);

        model = new ViewModelProvider(this).get(VerifyViewModel.class);
        model.getMaxTransaction(id_param);

        model._minimal.observe(this, data -> {
            if (data <= 1000) {
                binding.verifikasi.setEnabled(false);
                binding.textView.setText(getString(R.string.saldo_kurang));
            } else {
                binding.textView.setText(getString(R.string.format_angka, FormatAngka.format(data)));
            }

        });

        binding.verifikasi.setOnClickListener(view -> {
            String nominal = binding.nama.getText().toString();
            model.addPengajuan("421", id_param, Integer.parseInt(nominal), Tanggal.getCurrentDate());
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