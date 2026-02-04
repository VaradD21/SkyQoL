package com.skyqol.client;

import net.fabricmc.api.ClientModInitializer;

public class SkyQoLClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        SkyQoLCommand.register();
    }
}
