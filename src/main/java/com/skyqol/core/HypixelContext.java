package com.skyqol.core;

import net.minecraft.client.MinecraftClient;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.scoreboard.ScoreboardEntry;
import net.minecraft.text.Text;
import net.minecraft.scoreboard.ScoreboardDisplaySlot;


import java.util.Collection;

public final class HypixelContext {

    private static boolean onHypixel = false;
    private static boolean inSkyBlock = false;

    private HypixelContext() {}

    public static void tick() {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client == null || client.player == null || client.world == null) {
            onHypixel = false;
            inSkyBlock = false;
            return;
        }

        // ----- SERVER CHECK -----
        if (client.getCurrentServerEntry() != null) {
            String address = client.getCurrentServerEntry().address.toLowerCase();
            onHypixel = address.contains("hypixel");
        } else {
            onHypixel = false;
        }

        // ----- SKYBLOCK CHECK -----
        inSkyBlock = false;

        if (onHypixel) {
            Scoreboard scoreboard = client.world.getScoreboard();
            ScoreboardObjective objective =
                    scoreboard.getObjectiveForSlot(ScoreboardDisplaySlot.SIDEBAR);
// sidebar

            if (objective != null) {
                Collection<ScoreboardEntry> entries =
                        scoreboard.getScoreboardEntries(objective);

                for (ScoreboardEntry entry : entries) {
                    String line = entry.name().getString().toLowerCase();

                    if (line.contains("skyblock")) {
                        inSkyBlock = true;
                        break;
                    }
                }
            }
        }
    }

    public static boolean isOnHypixel() {
        return onHypixel;
    }

    public static boolean isInSkyBlock() {
        return inSkyBlock;
    }
}
