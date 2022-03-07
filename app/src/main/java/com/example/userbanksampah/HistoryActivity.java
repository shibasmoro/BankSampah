package com.example.userbanksampah;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SpinnerAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.userbanksampah.databinding.ActivityHistoryBinding;
import com.example.userbanksampah.util.Tahun;

public class HistoryActivity extends AppCompatActivity {

    ActivityHistoryBinding Binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Binding = ActivityHistoryBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        Tahun.setYears(2018);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Tahun.getYears());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Binding.spinnerYear.setAdapter(adapter);

    }
}