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

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(20, 20, 20, 20);
        mainLayout.setBackgroundColor(Color.BLACK);

        // MYRA ICON
        TextView myraIcon = new TextView(this);
        myraIcon.setText("🤖");
        myraIcon.setTextSize(70);
        myraIcon.setGravity(Gravity.CENTER);

        mainLayout.addView(myraIcon);

        // MYRA NAME
        TextView title = new TextView(this);
        title.setText("MYRA");
        title.setTextSize(30);
        title.setTypeface(null, Typeface.BOLD);
        title.setTextColor(Color.WHITE);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 5, 0, 20);

        mainLayout.addView(title);

        // CHAT AREA
        ScrollView scrollView = new ScrollView(this);

        chatText = new TextView(this);
        chatText.setText(
                "Myra: Hello! আমি Myra। 😊\n\n" +
                "আমাকে কিছু লিখে পাঠাও।"
        );
        chatText.setTextSize(18);
        chatText.setTextColor(Color.WHITE);
        chatText.setPadding(15, 15, 15, 15);

        scrollView.addView(chatText);

        LinearLayout.LayoutParams scrollParams =
                new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        0,
                        1
                );

        mainLayout.addView(scrollView, scrollParams);

        // INPUT
        EditText input = new EditText(this);
        input.setHint("Myra-কে কিছু বলো...");
        input.setTextColor(Color.WHITE);
        input.setHintTextColor(Color.GRAY);

        mainLayout.addView(input);

        // SEND BUTTON
        Button sendButton = new Button(this);
        sendButton.setText("SEND");

        mainLayout.addView(sendButton);

        // FRIENDLY MODE
        Button modeButton = new Button(this);

        if (romanticManager.isRomanticActive()) {
            modeButton.setText("Friendly Mode: ON");
        } else {
            modeButton.setText("Friendly Mode: OFF");
        }

        mainLayout.addView(modeButton);

        // SEND ACTION
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userMessage =
                        input.getText().toString().trim();

                if (userMessage.isEmpty()) {
                    return;
                }

                String response =
                        romanticManager.getResponse(userMessage);

                chatText.append(
                        "\n\nYou: " + userMessage +
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

        // MODE ACTION
        modeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean newState =
                        !romanticManager.isRomanticActive();

                romanticManager.toggleRomanticMode(
                        MainActivity.this,
                        newState
                );

                if (newState) {
                    modeButton.setText("Friendly Mode: ON");
                } else {
                    modeButton.setText("Friendly Mode: OFF");
                }
            }
        });

        setContentView(mainLayout);
    }
}
