package com.example.paytmapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SendMoneyActivity extends AppCompatActivity {

    EditText etAmount;
    Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_money);

        etAmount = findViewById(R.id.etAmount);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(v -> {
            String amount = etAmount.getText().toString();
            if(amount.isEmpty()){
                Toast.makeText(this, "Enter amount", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(SendMoneyActivity.this, UserInfoActivity.class);
                intent.putExtra("amount", amount);
                startActivity(intent);
            }
        });
    }
}
