package com.skyqol.client;

import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class SkyQoLCommand {

    public static void register() {
        ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {
            dispatcher.register(
                    literal("skyqol")
                            .executes(ctx -> {
                                MinecraftClient client = MinecraftClient.getInstance();
                                client.execute(() -> client.setScreen(new SkyQoLScreen()));
                                return 1;
                            })
                            .then(
                                    literal("test").executes(ctx -> {
                                        MinecraftClient client = MinecraftClient.getInstance();
                                        if (client.player != null) {
                                            client.player.sendMessage(Text.literal("works"), false);
                                        }
                                        return 1;
                                    })
                            )
            );
        });
    }
}
