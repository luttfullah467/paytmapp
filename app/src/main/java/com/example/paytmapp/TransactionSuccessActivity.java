package com.example.paytmapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TransactionSuccessActivity extends AppCompatActivity {

    TextView tvSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_success);

        tvSuccess = findViewById(R.id.tvSuccess);

        String name = getIntent().getStringExtra("name");
        String account = getIntent().getStringExtra("account");
        String date = getIntent().getStringExtra("date");
        String amount = getIntent().getStringExtra("amount");

        tvSuccess.setText("Transaction Successful!\n\n" +
                "Name: " + name + "\n" +
                "Account: " + account + "\n" +
                "Date: " + date + "\n" +
                "Amount: $" + amount);
    }
}
