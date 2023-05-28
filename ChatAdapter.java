package com.anusha.coffee.Adapters;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.anusha.coffee.Activities.ChatMessage;
import com.anusha.coffee.R;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MessageViewHolder> {

    private List<ChatMessage> messageList;
    private Context context;
    private FirebaseAuth mAuth;

    public ChatAdapter(List<ChatMessage> messageList, Context context) {
        this.messageList = messageList;
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_message, parent, false);
        return new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        ChatMessage message = messageList.get(position);

        // Set the message text and sender name
        holder.messageTextView.setText(message.getMessageText());
        if (message.getSenderID().equals(mAuth.getCurrentUser().getUid())) {
            holder.senderNameTextView.setText("You");
        } else {
            holder.senderNameTextView.setText("Other User");
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    public static class MessageViewHolder extends RecyclerView.ViewHolder {
        public TextView messageTextView;
        public TextView senderNameTextView;

        public MessageViewHolder(View view) {
            super(view);
            messageTextView = view.findViewById(R.id.textView_messageBody);
            senderNameTextView = view.findViewById(R.id.textView_senderName);
        }
    }
}

