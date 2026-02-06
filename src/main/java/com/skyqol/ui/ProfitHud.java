package com.skyqol.ui;

import com.skyqol.core.HypixelContext;
import com.skyqol.feature.bazaar.core.BazaarService;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public final class ProfitHud {

    private ProfitHud() {
    }

    public static void register() {
        HudRenderCallback.EVENT.register((drawContext, tickDelta) -> render(drawContext));
    }

    private static void render(DrawContext context) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client == null || client.player == null)
            return;
        // DISABLED FOR TESTING - HUD shows everywhere now
        // if (!HypixelContext.isInSkyBlock())

        double profit = BazaarService.getLedger().getNetProfit();

        // Determine color based on profit
        int color;
        String sign;
        if (profit > 0) {
            color = 0x55FF55; // Green
            sign = "+";
        } else if (profit < 0) {
            color = 0xFF5555; // Red
            sign = "";
        } else {
            color = 0xFFFFFF; // White
            sign = "";
        }

        String text = "§6Bazaar Profit: §r" + sign + String.format("%.2f", profit) + " coins";

        TextRenderer textRenderer = client.textRenderer;
        int screenWidth = client.getWindow().getScaledWidth();
        int x = screenWidth - textRenderer.getWidth(text) - 10;
        int y = 10;

        // Draw shadow for better visibility
        context.drawText(textRenderer, text, x, y, color, true);
    }
}
