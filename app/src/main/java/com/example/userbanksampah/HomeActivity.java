package com.example.userbanksampah;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.userbanksampah.Retrofit.RetrofitImpl;
import com.example.userbanksampah.Retrofit.VerifyActivity;
import com.example.userbanksampah.databinding.ActivityHomeBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements UpdateRecyclerView {
    private RecyclerView recyclerView,recyclerView2;
    private StaticRvAdapter staticRvAdapter;
    ArrayList<DynamicRvModel> items = new ArrayList<>();
    DynamicRvAdapter dynamicRvAdapter;

    private ActivityHomeBinding Binding;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
        ,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(Binding.getRoot());

        init();
        Binding.verify.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, VerifyActivity.class)));
        Binding.history.setOnClickListener(view -> startActivity(new Intent(HomeActivity.this, HistoryActivity.class)));

        ArrayList<StaticRvModel> item = new ArrayList<>();
        item.add(new StaticRvModel(R.drawable.kertas,"KERTAS"));
        item.add(new StaticRvModel(R.drawable.besi,"BESI"));
        item.add(new StaticRvModel(R.drawable.logam,"LOGAM"));
        item.add(new StaticRvModel(R.drawable.lainlain,"LAIN - LAIN"));

        recyclerView = findViewById(R.id.rv_1);
        staticRvAdapter = new StaticRvAdapter(item,this,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(  this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(staticRvAdapter);

        items = new ArrayList<>();
        recyclerView2 = findViewById(R.id.rv_2);
        dynamicRvAdapter = new DynamicRvAdapter(items);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerView2.setAdapter(dynamicRvAdapter);

    }

    public void init(){
        preferences =getSharedPreferences(LoginActivity.MyPreferences, Context.MODE_PRIVATE);
        Binding.nama.setText(preferences.getString(LoginActivity.Nama,"kosong"));

        RetrofitImpl.show_saldo().your_saldo(preferences.getString(LoginActivity.Id,"kosong")).enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful()){

                    Binding.saldo.setText(response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
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

    @Override
    public void callback(int position, ArrayList<DynamicRvModel> items) {
        dynamicRvAdapter = new DynamicRvAdapter(items);
        dynamicRvAdapter.notifyDataSetChanged();
        recyclerView2.setAdapter(dynamicRvAdapter);
    }
}