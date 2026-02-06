package com.skyqol.feature;

public interface Feature {

    String getName();

    void onInit();

    default boolean isEnabled() {
        return true;
    }
}
