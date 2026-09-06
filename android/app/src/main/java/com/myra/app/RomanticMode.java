package com.myra.app;

public class RomanticMode {

    public enum ModeType {
        NORMAL,
        ROMANTIC
    }

    private ModeType currentMode;

    public RomanticMode() {
        currentMode = ModeType.NORMAL;
    }

    public ModeType getCurrentMode() {
        return currentMode;
    }

    public void setMode(ModeType mode) {
        currentMode = mode;
    }

    public boolean isRomantic() {
        return currentMode == ModeType.ROMANTIC;
    }
}
