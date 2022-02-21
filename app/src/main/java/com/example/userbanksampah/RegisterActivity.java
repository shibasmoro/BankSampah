package com.example.userbanksampah;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    private EditText NAMA, USERNAME, NUMBER, PASSWORD, p2;
    private Button btnregist;
    private String link_regist = "https://ublmobilekmmi.web.id/public_html/bank_sampah/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        NAMA = findViewById(R.id.nama);
        USERNAME = findViewById(R.id.username);
        NUMBER = findViewById(R.id.number);
        PASSWORD = findViewById(R.id.password1);
        p2 = findViewById(R.id.password2);
        btnregist = findViewById(R.id.btnregist);

        btnregist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Regist();
                btnregist.setVisibility(View.GONE);

            }
        });

    }

    private void Regist() {
        btnregist.setVisibility(View.VISIBLE);
        final String nama = this.NAMA.getText().toString().trim();
        final String username = this.USERNAME.getText().toString().trim();
        final String number = this.NUMBER.getText().toString().trim();
        final String password = this.PASSWORD.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, link_regist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");

                    if(success.equals("1")){
                        Toast.makeText(RegisterActivity.this,"Register Success",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        RegisterActivity.this.startActivity(intent);
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(RegisterActivity.this,"Register Failed" + e.toString(),Toast.LENGTH_SHORT).show();
                    btnregist.setVisibility(View.VISIBLE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(RegisterActivity.this,"Register Failed" + error.toString(),Toast.LENGTH_SHORT).show();
                btnregist.setVisibility(View.VISIBLE);

            }
        })
        {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("nama",nama);
                params.put("username",username);
                return params;
            }
        };



    }

}