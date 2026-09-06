package com.myra.app;

import android.content.Context;
import android.content.SharedPreferences;

public class RomanticConfig {

    private static final String PREF_NAME = "MyraRomanticPrefs";
    private static final String KEY_ROMANTIC_MODE = "is_romantic_enabled";

    private SharedPreferences preferences;

    public RomanticConfig(Context context) {
        preferences = context.getSharedPreferences(
                PREF_NAME,
                Context.MODE_PRIVATE
        );
    }

    public void saveRomanticState(boolean isEnabled) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_ROMANTIC_MODE, isEnabled);
        editor.apply();
    }

    public boolean isRomanticSaved() {
        return preferences.getBoolean(
                KEY_ROMANTIC_MODE,
                false
        );
    }
}
