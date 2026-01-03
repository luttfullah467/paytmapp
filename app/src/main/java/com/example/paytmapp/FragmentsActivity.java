package com.example.paytmapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.widget.Button;

// Add these imports for your fragments
import com.example.paytmapp.RedFragment;
import com.example.paytmapp.GreenFragment;
import com.example.paytmapp.BlueFragment;

public class FragmentsActivity extends AppCompatActivity {

    Button btnRed, btnGreen, btnBlue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragments);

        btnRed = findViewById(R.id.btnRed);
        btnGreen = findViewById(R.id.btnGreen);
        btnBlue = findViewById(R.id.btnBlue);

        btnRed.setOnClickListener(v -> loadFragment(new RedFragment()));
        btnGreen.setOnClickListener(v -> loadFragment(new GreenFragment()));
        btnBlue.setOnClickListener(v -> loadFragment(new BlueFragment()));
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragmentContainer, fragment); // Make sure this ID exists in your layout
        ft.commit();
    }
}
