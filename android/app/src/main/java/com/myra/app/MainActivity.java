package com.myra.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

    private RomanticManager romanticManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        romanticManager = new RomanticManager(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(30, 30, 30, 30);
        layout.setBackgroundColor(Color.BLACK);

        TextView textView = new TextView(this);
        textView.setText("MYRA\n\nYour AI Assistant");
        textView.setTextSize(26);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);

        layout.addView(textView);

        Button romanticButton = new Button(this);

        if (romanticManager.isRomanticActive()) {
            romanticButton.setText("Friendly Mode: ON");
        } else {
            romanticButton.setText("Friendly Mode: OFF");
        }

        layout.addView(romanticButton);

        romanticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean newState =
                        !romanticManager.isRomanticActive();

                romanticManager.toggleRomanticMode(
                        MainActivity.this,
                        newState
                );

                if (newState) {
                    romanticButton.setText("Friendly Mode: ON");
                    textView.setText(
                            "MYRA\n\nFriendly Mode Active"
                    );
                } else {
                    romanticButton.setText("Friendly Mode: OFF");
                    textView.setText(
                            "MYRA\n\nYour AI Assistant"
                    );
                }
            }
        });

        setContentView(layout);
    }
}
