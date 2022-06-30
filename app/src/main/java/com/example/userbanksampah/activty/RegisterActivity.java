package com.example.userbanksampah.activty;


import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.userbanksampah.databinding.ActivityRegisterBinding;
import com.example.userbanksampah.viewmodel.RegisterViewModel;


public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;
    private RegisterViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        model = new ViewModelProvider(this).get(RegisterViewModel.class);
        binding.btnregist.setOnClickListener(view -> Regist());
        //model.loading.observe(this, this::showLoading);
        model.message.observe(this,result->{
            showToast(result.getPesan());
            if (result.getPesan().contains("Berhasil")){
                finish();
            }
        });
        model.eror.observe(this, this::showToast);
    }

    private void Regist() {

         String nama = binding.nama.getText().toString();
         String notelp = binding.number.getText().toString().trim();
         String alamat = binding.address.getText().toString();
         String password = binding.password1.getText().toString();

         if (nama.isEmpty()){
             binding.nama.setError("Kolom ini wajib di isi");
         }else if (notelp.isEmpty()){
             binding.number.setError("Kolom ini wajib di isi");
         }else if (alamat.isEmpty()){
             binding.address.setError("Kolom ini wajib di isi");
         }else if (password.isEmpty()){
             binding.password1.setError("Kolom ini wajib di isi");
         } else {
             model.register(nama,alamat,password,notelp);
         }

    }

//    public void showLoading(Boolean isLoad){
//        if (isLoad){
//            binding.progressBar.setVisibility(View.VISIBLE);
//        }else{
//            binding.progressBar.setVisibility(View.GONE);
//        }
//    }
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}