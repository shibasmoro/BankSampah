package com.example.userbanksampah.Activty;

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

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Tahun.this_year);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Binding.spinnerYear.setAdapter(adapter);

    }
}