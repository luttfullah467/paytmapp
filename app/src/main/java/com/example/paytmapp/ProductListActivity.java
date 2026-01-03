package com.example.paytmapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

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
        list.add(new ProductItem("Mobile Recharge"));
        list.add(new ProductItem("Electricity Bill"));
        list.add(new ProductItem("Gas Bill"));
        list.add(new ProductItem("Movie Tickets"));
        

        ProductAdapter adapter = new ProductAdapter(this, list);
        recyclerView.setAdapter(adapter);
    }
}
