package com.example.userbanksampah;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
   TextInputEditText username;
   TextView textView , Id,password;

   Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        init();
        textView = findViewById(R.id.register);
        login = findViewById(R.id.btnlogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NasabahImpl.login().validate_login(Id.getText().toString(),password.getText().toString()).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), response.body(), Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), response.body(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }
    });
    }

        // saat tombol login ditekan


    public void login(View view) {

    }

    // initialitation
    public void init (){
        Id =findViewById(R.id.edittext);
        password =findViewById(R.id.edittextpassword);

    }
}