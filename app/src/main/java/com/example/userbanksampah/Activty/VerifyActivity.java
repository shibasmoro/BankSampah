package com.example.userbanksampah.Activty;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.userbanksampah.R;
import com.example.userbanksampah.ViewModel.LoginViewModel;
import com.example.userbanksampah.ViewModel.VerifyViewModel;
import com.example.userbanksampah.databinding.ActivityVerifyBinding;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Formatter;
import java.util.List;

public class VerifyActivity extends AppCompatActivity {
    private  SharedPreferences preferences;
    private VerifyViewModel model;
    private String id_param;

    private ActivityVerifyBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVerifyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferences =getSharedPreferences(LoginActivity.MyPreferences, Context.MODE_PRIVATE);
        id_param =preferences.getString(LoginActivity.Id,"kosong");

        model = new ViewModelProvider(this).get(VerifyViewModel.class);
        model.getMaxTransaction("321");
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        //imm.hideSoftInputFromWindow(this.getWindowToken(), 0);
        model.getMaxTransaction();
        model._minimal.observe(this,data->{

            if (data<=5000){
                binding.verifikasi.setEnabled(false);
                binding.textView.setText("Maaf Saldo Anda Tidak cukup");
            }else{
                NumberFormat numberFormat = new DecimalFormat("#,###");
                binding.textView.setText("Rp."+numberFormat.format(data));
            }

       });

    }

    public void backbutton(View view) {
        startActivity(new Intent(this, HomeActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
    }
}