package com.example.userbanksampah.Activty;


import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userbanksampah.DynamicRvAdapter;
import com.example.userbanksampah.DynamicRvModel;
import com.example.userbanksampah.R;
import com.example.userbanksampah.StaticRvAdapter;
import com.example.userbanksampah.StaticRvModel;
import com.example.userbanksampah.UpdateRecyclerView;
import com.example.userbanksampah.ViewModel.HomeviewModel;
import com.example.userbanksampah.databinding.ActivityHomeBinding;
import com.example.userbanksampah.util.PreferencesApp;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity implements UpdateRecyclerView {

    private RecyclerView recyclerView, recyclerView2;
    private StaticRvAdapter staticRvAdapter;

    ArrayList<DynamicRvModel> items = new ArrayList<>();
    DynamicRvAdapter dynamicRvAdapter;

    private ActivityHomeBinding Binding;
    private HomeviewModel model;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        PreferencesApp pref = new PreferencesApp(this);

        Binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());
        model = new ViewModelProvider(this).get(HomeviewModel.class);

        Binding.nama.setText(PreferencesApp.getStr(PreferencesApp.Nama));
        model.getSaldo(PreferencesApp.getStr(PreferencesApp.Id));

        model.data.observe(this, data -> {
            NumberFormat formatter = new DecimalFormat("#,###");
            Binding.saldo.setText("Rp." + formatter.format(data));

        });

        Binding.verify.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, VerifyActivity.class)));
        Binding.history.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, HistoryActivity.class)));

        ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.kertas, "KERTAS"));
        item.add(new StaticRvModel(R.drawable.besi, "BESI"));
        item.add(new StaticRvModel(R.drawable.logam, "LOGAM"));
        item.add(new StaticRvModel(R.drawable.lainlain, "LAIN - LAIN"));

        recyclerView = findViewById(R.id.rv_1);
        staticRvAdapter = new StaticRvAdapter(item, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(staticRvAdapter);

        items = new ArrayList<>();
        recyclerView2 = findViewById(R.id.rv_2);
        dynamicRvAdapter = new DynamicRvAdapter(items);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView2.setAdapter(dynamicRvAdapter);

    }

    /*
    public void logout(View view) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(LoginActivity.Id,"Kosong");
        editor.putString(LoginActivity.Nama,"Kosong");
        editor.putString(LoginActivity.Alamat,"Kosong");
        editor.commit();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        finish();
    }
     */

    @Override
    public void callback(int position, ArrayList<DynamicRvModel> items) {
        dynamicRvAdapter = new DynamicRvAdapter(items);
        dynamicRvAdapter.notifyDataSetChanged();
        recyclerView2.setAdapter(dynamicRvAdapter);
    }

}