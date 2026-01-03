package com.example.paytmapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp = getSharedPreferences("PaytmPrefs", MODE_PRIVATE);

        if (sp.getBoolean("isLoggedIn", false)) {
            startActivity(new Intent(this, ProductListActivity.class));
        } else {
            startActivity(new Intent(this, LoginActivity.class));
        }
        finish();
    }
}
