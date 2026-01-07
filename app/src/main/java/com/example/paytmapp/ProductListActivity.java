package com.example.paytmapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;
import android.content.Intent;

import java.util.ArrayList;
import com.google.firebase.auth.FirebaseAuth;

public class ProductListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<ProductItem> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        list.add(new ProductItem("Send Money"));
        list.add(new ProductItem("QR Code"));
        list.add(new ProductItem("Electricity Bill"));
        list.add(new ProductItem("Gas Bill"));
        list.add(new ProductItem("Logout"));

        ProductAdapter adapter = new ProductAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }
}
