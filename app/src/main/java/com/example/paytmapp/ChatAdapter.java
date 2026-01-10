package com.example.paytmapp;

import android.view.*;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.VH> {

    List<ChatMessage> list;

    public ChatAdapter(List<ChatMessage> list) {
        this.list = list;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView tv = new TextView(parent.getContext());
        tv.setPadding(12,12,12,12);
        return new VH(tv);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        ChatMessage m = list.get(position);
        holder.tv.setText(m.sender + ": " + m.text);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class VH extends RecyclerView.ViewHolder {
        TextView tv;
        VH(View v) {
            super(v);
            tv = (TextView) v;
        }
    }
}
