package com.anusha.coffee.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.anusha.coffee.Activities.ChatMessage;
import com.anusha.coffee.R;

import java.util.List;

public class ChatMessageAdapter extends ArrayAdapter<ChatMessage> {

    private Context mContext;
    private List<ChatMessage> mChatMessages;

    public ChatMessageAdapter(Context context, List<ChatMessage> chatMessages) {
        super(context, 0, chatMessages);
        mContext = context;
        mChatMessages = chatMessages;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.chat_message_row, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.senderTextView = convertView.findViewById(R.id.sender_text_view);
            viewHolder.messageTextView = convertView.findViewById(R.id.message_text_view);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ChatMessage chatMessage = mChatMessages.get(position);
        viewHolder.senderTextView.setText(chatMessage.getSenderName());
        viewHolder.messageTextView.setText(chatMessage.getMessageText());

        return convertView;
    }

    static class ViewHolder {
        TextView senderTextView;
        TextView messageTextView;
    }
}

