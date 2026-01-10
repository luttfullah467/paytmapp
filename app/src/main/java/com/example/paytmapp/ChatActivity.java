package com.example.paytmapp;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.*;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

import java.util.*;

public class ChatActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    EditText etMessage;
    Button btnSend;

    DatabaseReference chatRef;
    String myId, otherId;

    List<ChatMessage> list = new ArrayList<>();
    ChatAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = findViewById(R.id.chatRecycler);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ChatAdapter(list);
        recyclerView.setAdapter(adapter);

        // 🔹 Get current user email
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            myId = FirebaseAuth.getInstance().getCurrentUser().getEmail();
        } else {
            Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
            finish();
        }

        // 🔹 Get the other user from Intent
        otherId = getIntent().getStringExtra("otherId");
        if (otherId == null) {
            Toast.makeText(this, "No chat user selected", Toast.LENGTH_SHORT).show();
            finish();
        }

        String sanitizedMyId = myId.replace(".", ",");
        String sanitizedOtherId = otherId.replace(".", ",");
        String chatId = getChatId(sanitizedMyId, sanitizedOtherId);

        chatRef = FirebaseDatabase.getInstance()
                .getReference("chats")
                .child(chatId);


        // 🔹 Listen for messages in real-time
        chatRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    ChatMessage msg = snap.getValue(ChatMessage.class);
                    list.add(msg);
                }
                adapter.notifyDataSetChanged();
                recyclerView.scrollToPosition(list.size() - 1);
            }

            @Override
            public void onCancelled(DatabaseError error) {}
        });

        // 🔹 Send message
        btnSend.setOnClickListener(v -> {
            String text = etMessage.getText().toString().trim();
            if (text.isEmpty()) return;

            String key = chatRef.push().getKey();

            ChatMessage msg = new ChatMessage();
            msg.sender = myId;
            msg.receiver = otherId;
            msg.text = text;
            msg.time = System.currentTimeMillis();

            chatRef.child(key).setValue(msg);
            etMessage.setText("");
        });
    }

    // Generates chat node ID in consistent order
    private String getChatId(String a, String b) {
        return a.compareTo(b) < 0 ? a + "_" + b : b + "_" + a;
    }
}
