package com.example.paytmapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    EditText etUsername;
    Button btnLogin;
    TextView tvSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.etUsername);
        btnLogin = findViewById(R.id.btnLogin);
        tvSignup = findViewById(R.id.tvSignup);

        btnLogin.setOnClickListener(v -> {
            SharedPreferences sp = getSharedPreferences("PaytmPrefs", MODE_PRIVATE);
            sp.edit().putBoolean("isLoggedIn", true).apply();

            startActivity(new Intent(LoginActivity.this, ProductListActivity.class));
            finish();
        });

        tvSignup.setOnClickListener(v ->
                startActivity(new Intent(LoginActivity.this, SignupActivity.class))
        );
    }
}
