package com.myra.app;

import android.content.Context;

public class RomanticManager {

    private RomanticConfig config;
    private RomanticMode romanticMode;

    public RomanticManager(Context context) {

        config = new RomanticConfig(context);
        romanticMode = new RomanticMode();

        if (config.isRomanticSaved()) {
            romanticMode.setMode(
                    RomanticMode.ModeType.ROMANTIC
            );
        } else {
            romanticMode.setMode(
                    RomanticMode.ModeType.NORMAL
            );
        }
    }

    public boolean isRomanticActive() {
        return romanticMode.isRomantic();
    }

    public void toggleRomanticMode(
            Context context,
            boolean enable
    ) {

        if (enable) {
            romanticMode.setMode(
                    RomanticMode.ModeType.ROMANTIC
            );
        } else {
            romanticMode.setMode(
                    RomanticMode.ModeType.NORMAL
            );
        }

        config.saveRomanticState(enable);
    }

    public String getResponse(String userInput) {

        if (isRomanticActive()) {
            return getFriendlyResponse(userInput);
        }

        return getNormalResponse(userInput);
    }

    private String getFriendlyResponse(String input) {

        String text = input.toLowerCase().trim();

        if (text.contains("hi")
                || text.contains("hello")
                || text.contains("হাই")
                || text.contains("হ্যালো")) {

            return "হ্যালো! আমি Myra। 😊 কীভাবে সাহায্য করতে পারি?";
        }

        if (text.contains("love")
                || text.contains("ভালোবাসি")) {

            return "তোমার কথাটা শুনে ভালো লাগলো! 😊 আমি তোমার বন্ধুসুলভ AI assistant হিসেবে পাশে আছি।";
        }

        return "তোমার সাথে কথা বলতে ভালো লাগছে। 😊 কী জানতে চাও?";
    }

    private String getNormalResponse(String input) {

        return "Hello! I am Myra AI. How can I assist you today?";
    }
}
