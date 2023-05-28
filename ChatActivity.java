package com.anusha.coffee.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.anusha.coffee.Adapters.ChatMessageAdapter;
import com.anusha.coffee.R;
import com.anusha.coffee.databinding.ActivityChatBinding;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private ListView mChatListView;
    private EditText mMessageEditText;
    private Button mSendButton;

    private List<ChatMessage> mChatMessages = new ArrayList<>();
    private ChatMessageAdapter mChatMessageAdapter;

    private String mDonorName;
    private String mRecipientName;

    ActivityChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        // Get the donor's and recipient's names from the intent extras
        Bundle extras = getIntent().getExtras();
        mDonorName = extras.getString("donor_name");
        mRecipientName = extras.getString("recipient_name");

        // Set the title of the activity to the recipient's name
        setTitle(mRecipientName);

        // Get references to the UI elements
        mChatListView = findViewById(R.id.chat_list_view);
        mMessageEditText = findViewById(R.id.message_edit_text);
        mSendButton = findViewById(R.id.send_button);

        // Set up the adapter for the ListView
        mChatMessageAdapter = new ChatMessageAdapter(this, mChatMessages);
        mChatListView.setAdapter(mChatMessageAdapter);

        // Set up the click listener for the send button
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the text from the message EditText
                String messageText = mMessageEditText.getText().toString().trim();

                // If the message is not empty, add it to the chat messages and clear the EditText
                if (!messageText.isEmpty()) {
                    ChatMessage message = new ChatMessage("senderID", "message", "senderName", "messageText");

                    mChatMessages.add(message);
                    mChatMessageAdapter.notifyDataSetChanged();
                    mMessageEditText.setText("");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        // If the user presses the back button, finish the activity
        finish();
    }
}
