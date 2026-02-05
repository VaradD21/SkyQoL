package com.skyqol.client;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class SkyQoLScreen extends Screen {

    public SkyQoLScreen() {
        super(Text.literal("SkyQoL"));
    }

    @Override
    protected void init() {
        this.addDrawableChild(
                ButtonWidget.builder(Text.literal("Close"), b -> this.close())
                        .dimensions(this.width / 2 - 50, this.height / 2 + 20, 100, 20)
                        .build()
        );
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        context.fill(0, 0, this.width, this.height, 0xFF202020);
        context.drawText(
                this.textRenderer,
                "SkyQoL GUI OPEN",
                this.width / 2 - 50,
                this.height / 2 - 10,
                0xFFFFFF,
                true
        );
        super.render(context, mouseX, mouseY, delta);
    }
}
