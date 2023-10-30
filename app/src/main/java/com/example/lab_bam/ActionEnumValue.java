package com.example.lab_bam;

public enum ActionEnumValue {
    SUPPRESS_LOGGING("com.example.lab_bam.SUPRESS_LOGGING");
    private final String enumVaL;

    ActionEnumValue(String s) {
        enumVaL = s;
    }
    public String getAction() {
        return enumVaL;
    }
}
