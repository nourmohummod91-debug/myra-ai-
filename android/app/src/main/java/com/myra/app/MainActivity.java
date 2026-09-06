package com.myra.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private RomanticManager romanticManager;
    private TextView chatText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        romanticManager = new RomanticManager(this);

        showHomeScreen();
    }

    private void showHomeScreen() {

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(24, 24, 24, 24);
        main.setGravity(Gravity.CENTER);
        main.setBackgroundColor(Color.BLACK);

        TextView title = new TextView(this);
        title.setText("MYRA");
        title.setTextSize(34);
        title.setTextColor(Color.WHITE);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        main.addView(title);

        TextView subtitle = new TextView(this);
        subtitle.setText("MYRA Assistant");
        subtitle.setTextSize(18);
        subtitle.setTextColor(Color.LTGRAY);
        subtitle.setGravity(Gravity.CENTER);
        subtitle.setPadding(0, 8, 0, 30);

        main.addView(subtitle);

        // Anime character placeholder
        TextView character = new TextView(this);
        character.setText("👧");
        character.setTextSize(120);
        character.setGravity(Gravity.CENTER);

        LinearLayout.LayoutParams characterParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0,
                        1
                );

        main.addView(character, characterParams);

        TextView status = new TextView(this);
        status.setText("● Myra is ready");
        status.setTextSize(17);
        status.setTextColor(Color.WHITE);
        status.setGravity(Gravity.CENTER);
        status.setPadding(0, 10, 0, 20);

        main.addView(status);

        Button chatButton = new Button(this);
        chatButton.setText("💬  Chat with Myra");
        chatButton.setTextSize(17);

        main.addView(chatButton);

        Button voiceButton = new Button(this);
        voiceButton.setText("🎙️  Hey Myra");
        voiceButton.setTextSize(17);

        main.addView(voiceButton);

        chatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChatScreen();
            }
        });

        voiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status.setText("🎙️ Listening for Hey Myra...");
            }
        });

        setContentView(main);
    }

    private void showChatScreen() {

        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setPadding(20, 20, 20, 20);
        main.setBackgroundColor(Color.BLACK);

        TextView title = new TextView(this);
        title.setText("MYRA");
        title.setTextSize(28);
        title.setTextColor(Color.WHITE);
        title.setTypeface(null, Typeface.BOLD);
        title.setGravity(Gravity.CENTER);

        main.addView(title);

        ScrollView scrollView = new ScrollView(this);

        chatText = new TextView(this);
        chatText.setText(
                "Myra: Hello! I am Myra. 😊\n\n" +
                "আমাকে কিছু লিখে পাঠাও।"
        );
        chatText.setTextSize(18);
        chatText.setTextColor(Color.WHITE);
        chatText.setPadding(15, 20, 15, 20);

        scrollView.addView(chatText);

        LinearLayout.LayoutParams scrollParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0,
                        1
                );

        main.addView(scrollView, scrollParams);

        EditText input = new EditText(this);
        input.setHint("Message Myra...");
        input.setTextColor(Color.WHITE);
        input.setHintTextColor(Color.GRAY);

        main.addView(input);

        Button sendButton = new Button(this);
        sendButton.setText("SEND");

        main.addView(sendButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String message =
                        input.getText().toString().trim();

                if (message.isEmpty()) {
                    return;
                }

                String response =
                        romanticManager.getResponse(message);

                chatText.append(
                        "\n\nYou: " + message +
                        "\nMyra: " + response
                );

                input.setText("");

                scrollView.post(new Runnable() {
                    @Override
                    public void run() {
                        scrollView.fullScroll(View.FOCUS_DOWN);
                    }
                });
            }
        });

        setContentView(main);
    }

    @Override
    public void onBackPressed() {
        showHomeScreen();
    }
}
