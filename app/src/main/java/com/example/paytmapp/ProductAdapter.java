package com.example.paytmapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    List<ProductItem> list;

    public ProductAdapter(Context context, List<ProductItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(android.R.layout.simple_list_item_1, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ProductItem item = list.get(position);
        holder.text.setText(item.getName());

        holder.itemView.setOnClickListener(v -> {

            Intent intent;

            // 👇 Send Money goes to SendMoneyActivity
            if (item.getName().equals("Send Money")) {
                intent = new Intent(context, SendMoneyActivity.class);
            }

               else if (item.getName().equals("QR Code")) {
                    context.startActivity(new Intent(context, QRScannerActivity.class));
                }
                else if (item.getName().equals("Logout")) {
                    FirebaseAuth.getInstance().signOut();
                    context.startActivity(new Intent(context, LoginActivity.class));
                    ((Activity) context).finish();
                }
            else if (item.getName().equals("AI Chatbot")) {
                context.startActivity(new Intent(context, ChatbotActivity.class));
            }

            else {
                intent = new Intent(context, FragmentsActivity.class);
            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text;

        ViewHolder(View itemView) {
            super(itemView);
            text = itemView.findViewById(android.R.id.text1);
        }
    }
}
