package com.skyqol.core;

import com.skyqol.feature.Feature;
import java.util.ArrayList;
import java.util.List;

public class FeatureManager {

    private final List<Feature> features = new ArrayList<>();

    public void register(Feature feature) {
        features.add(feature);
        SkyQoLMod.log("Registered feature: " + feature.getName());
    }

    public void initAll() {
        for (Feature feature : features) {
            SkyQoLMod.log("Initializing feature: " + feature.getName());
            feature.onInit();
        }
    }
}
