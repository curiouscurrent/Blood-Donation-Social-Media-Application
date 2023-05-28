package com.anusha.coffee.Adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anusha.coffee.Message;
import com.anusha.coffee.R;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context mContext;
    private List<Message> mMessages;
    private String mCurrentUser;



    public MessageAdapter(Context context, List<Message> messages, String currentUser, String receiverPhotoUrl) {
        mContext = context;
        mMessages = messages;
        mCurrentUser = currentUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Message message = mMessages.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return mMessages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView messageTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.textView_messageBody);
        }

        public void bind(Message message) {
            messageTextView.setText(message.getMessageText());
            if (message.getSenderId().equals(mCurrentUser)) {
                // If the message was sent by the current user, align the message to the right
                messageTextView.setGravity(Gravity.END);
            } else {
                // If the message was sent by the other user, align the message to the left
                messageTextView.setGravity(Gravity.START);
            }
        }
    }
}
