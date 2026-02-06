package com.skyqol.core;

import com.skyqol.command.SkyQoLCommand;
import com.skyqol.data.log.TradeLogStore;
import com.skyqol.feature.bazaar.BazaarFeature;

public final class SkyQoLMod {

    private static FeatureManager featureManager;

    public static void init() {
        log("Initializing SkyQoL");

        TradeLogStore.load();

        featureManager = new FeatureManager();
        featureManager.register(new BazaarFeature());
        featureManager.initAll();

        SkyQoLCommand.register();

        log("SkyQoL initialized");
    }

    public static void log(String msg) {
        System.out.println("[SkyQoL] " + msg);
    }
}
