package com.example.paytmapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class UserInfoActivity extends AppCompatActivity {

    EditText etName, etAccount, etDate;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);

        etName = findViewById(R.id.etName);
        etAccount = findViewById(R.id.etAccount);
        etDate = findViewById(R.id.etDate);
        btnSend = findViewById(R.id.btnSend);

        etDate.setOnClickListener(v -> showDatePicker());

        btnSend.setOnClickListener(v -> {
            String name = etName.getText().toString();
            String account = etAccount.getText().toString();
            String date = etDate.getText().toString();
            String amount = getIntent().getStringExtra("amount");

            if(name.isEmpty() || account.isEmpty() || date.isEmpty()){
                etName.setError(name.isEmpty() ? "Enter name" : null);
                etAccount.setError(account.isEmpty() ? "Enter account" : null);
                etDate.setError(date.isEmpty() ? "Select date" : null);
            } else {
                Intent intent = new Intent(UserInfoActivity.this, TransactionSuccessActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("account", account);
                intent.putExtra("date", date);
                intent.putExtra("amount", amount);
                startActivity(intent);
            }
        });
    }

    private void showDatePicker(){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = new DatePickerDialog(this,
                (view, year1, month1, dayOfMonth) -> etDate.setText(dayOfMonth + "/" + (month1+1) + "/" + year1),
                year, month, day);
        dpd.show();
    }
}
