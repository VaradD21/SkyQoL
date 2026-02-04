package com.skyqol.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class SkyQoLScreen extends Screen {

    public SkyQoLScreen() {
        super(Text.literal("SkyQoL"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        // background
        context.fill(0, 0, this.width, this.height, 0xAA000000);

        // text (manual centering)
        int x = this.width / 2 - this.textRenderer.getWidth("SkyQoL GUI opened") / 2;
        int y = this.height / 2;

        context.drawText(
                this.textRenderer,
                "SkyQoL GUI opened",
                x,
                y,
                0xFFFFFF,
                true
        );
    }
}