package com.example.paytmapp;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.google.ai.client.generativeai.GenerativeModel;
import com.google.ai.client.generativeai.java.GenerativeModelFutures;
import com.google.ai.client.generativeai.type.Content;
import com.google.ai.client.generativeai.type.GenerateContentResponse;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChatbotActivity extends AppCompatActivity {

    private TextView chatResponse;
    private EditText userInput;
    private Button sendBtn;
    private GenerativeModelFutures model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);

        chatResponse = findViewById(R.id.chatResponse);
        userInput = findViewById(R.id.userInput);
        sendBtn = findViewById(R.id.sendBtn);

        // 1. Initialize the model
        GenerativeModel gm = new GenerativeModel("gemini-2.5-flash", "AIzaSyDOfMrVAl2CCIXoCiAkWqDD0miRlO4wbiw");
        model = GenerativeModelFutures.from(gm);

        sendBtn.setOnClickListener(v -> {
            String prompt = userInput.getText().toString();
            if (!prompt.isEmpty()) {
                chatResponse.append("\n\nYou: " + prompt);
                userInput.setText("");
                getResponse(prompt);
            }
        });
    }

    private void getResponse(String query) {
        Content content = new Content.Builder().addText(query).build();
        Executor executor = Executors.newSingleThreadExecutor();

        ListenableFuture<GenerateContentResponse> response = model.generateContent(content);

        Futures.addCallback(response, new FutureCallback<GenerateContentResponse>() {
            @Override
            public void onSuccess(GenerateContentResponse result) {
                String resultText = result.getText();
                runOnUiThread(() -> chatResponse.append("\n\nGemini: " + resultText));
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                runOnUiThread(() -> chatResponse.append("\n\nError: " + t.getMessage()));
            }
        }, executor);
    }
}