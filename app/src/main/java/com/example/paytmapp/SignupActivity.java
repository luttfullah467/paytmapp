package com.example.paytmapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class SignupActivity extends AppCompatActivity {

    EditText etUser, etPass;
    Button btnSignup, btnGoLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUser = findViewById(R.id.etUsername);
        etPass = findViewById(R.id.etPassword);
        btnSignup = findViewById(R.id.btnSignup);
        btnGoLogin = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(v -> {
            String user = etUser.getText().toString();
            String pass = etPass.getText().toString();

            SharedPreferences sp = getSharedPreferences("PaytmPrefs", MODE_PRIVATE);
            sp.edit()
                    .putString("regUser", user)
                    .putString("regPass", pass)
                    .apply();

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        btnGoLogin.setOnClickListener(v ->
                startActivity(new Intent(this, LoginActivity.class))
        );
    }
}
