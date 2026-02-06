package com.skyqol.client;

import com.skyqol.core.HypixelContext;
import com.skyqol.core.SkyQoLMod;
import com.skyqol.ui.ProfitHud;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;

public class SkyQoLClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        SkyQoLMod.init();

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            HypixelContext.tick();
        });

        ProfitHud.register();
    }
}
