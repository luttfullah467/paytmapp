package com.example.paytmapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.*;

public class UserListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<String> userList = new ArrayList<>();
    UserAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        recyclerView = findViewById(R.id.recyclerViewUsers);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new UserAdapter(userList, user -> {
            Intent intent = new Intent(UserListActivity.this, ChatActivity.class);
            intent.putExtra("otherId", user);
            startActivity(intent);
        });

        recyclerView.setAdapter(adapter);

        loadUsers();
    }

    private void loadUsers() {
        String myEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail().replace(".", ",");
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

        usersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                userList.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    String email = snap.getKey().replace(",", ".");
                    if (!email.equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
                        userList.add(email);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });
    }

}
