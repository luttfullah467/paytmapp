package com.example.paytmapp;

import android.view.*;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.VH> {

    interface OnUserClick {
        void onClick(String userEmail);
    }

    List<String> list;
    OnUserClick listener;

    public UserAdapter(List<String> list, OnUserClick listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView tv = new TextView(parent.getContext());
        tv.setPadding(20,20,20,20);
        return new VH(tv);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        String user = list.get(position);
        holder.tv.setText(user);
        holder.tv.setOnClickListener(v -> listener.onClick(user));
    }

    @Override
    public int getItemCount() { return list.size(); }

    static class VH extends RecyclerView.ViewHolder {
        TextView tv;
        VH(View v) {
            super(v);
            tv = (TextView) v;
        }
    }
}
