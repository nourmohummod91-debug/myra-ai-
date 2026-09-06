package com.myra.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TextView textView = new TextView(this);
        textView.setText("MYRA\n\nYour AI Assistant");
        textView.setTextSize(26);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);
        textView.setBackgroundColor(Color.BLACK);

        setContentView(textView);
    }
}
package com.myra.app;

import android.app.Activity;
import android.os.Bundle;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;

public class MainActivity extends Activity {

    private RomanticManager romanticManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Myra Romantic Mode Manager
        romanticManager = new RomanticManager(this);

        // Main Layout
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setGravity(Gravity.CENTER);
        layout.setPadding(30, 30, 30, 30);
        layout.setBackgroundColor(Color.BLACK);

        // Myra title
        TextView textView = new TextView(this);

        // আগের Myra লেখা রাখা হয়েছে
        textView.setText("MYRA\n\nYour AI Assistant");
        textView.setTextSize(26);
        textView.setTextColor(Color.WHITE);
        textView.setGravity(Gravity.CENTER);

        layout.addView(textView);

        // Romantic Mode Button
        Button romanticButton = new Button(this);

        if (romanticManager.isRomanticActive()) {
            romanticButton.setText("Romantic Mode: ON");
        } else {
            romanticButton.setText("Romantic Mode: OFF");
        }

        layout.addView(romanticButton);

        // Button click
        romanticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean newState = !romanticManager.isRomanticActive();

                romanticManager.toggleRomanticMode(MainActivity.this, newState);

                if (newState) {
                    romanticButton.setText("Romantic Mode: ON");
                    textView.setText("MYRA\n\nFriendly Mode Active 💕");
                } else {
                    romanticButton.setText("Romantic Mode: OFF");
                    textView.setText("MYRA\n\nYour AI Assistant");
                }
            }
        });

        setContentView(layout);
    }
}
