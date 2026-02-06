package com.skyqol.feature.bazaar.core;

import com.skyqol.core.HypixelContext;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class BazaarChatListener {

    // [Bazaar] Buy Order Setup! 64x Cobblestone for 64.0 coins.
    private static final Pattern BUY_SETUP = Pattern.compile(
            "\\[Bazaar] Buy Order Setup! (\\d+)x (.+) for ([\\d,.]+) coins\\.");

    // [Bazaar] Your Sell Offer for 64x Cobblestone was filled!
    private static final Pattern SELL_FILLED = Pattern.compile(
            "\\[Bazaar] Your Sell Offer for (\\d+)x (.+) was filled!");

    // [Bazaar] Claimed 36,864 coins from selling 64x Cobblestone!
    private static final Pattern SELL_CLAIM = Pattern.compile(
            "\\[Bazaar] Claimed ([\\d,.]+) coins from selling (\\d+)x (.+)!");

    private BazaarChatListener() {
    }

    public static void register() {
        ClientReceiveMessageEvents.GAME.register((message, overlay) -> {
            // DISABLED FOR TESTING - processes all chat now
            // if (!HypixelContext.isInSkyBlock()) return;

            String text = message.getString();

            // DEBUG: Log all chat messages to console
            System.out.println("[SkyQoL DEBUG] Chat: " + text);

            Matcher buy = BUY_SETUP.matcher(text);
            if (buy.matches()) {
                int qty = Integer.parseInt(buy.group(1));
                String item = buy.group(2);
                double total = parse(buy.group(3));

                BazaarService.recordBuy(item, qty, total / qty);
                System.out.println("[SkyQoL] Recorded BUY: " + qty + "x " + item + " @ " + (total / qty));
                return;
            }

            Matcher sellFilled = SELL_FILLED.matcher(text);
            if (sellFilled.matches()) {
                int qty = Integer.parseInt(sellFilled.group(1));
                String item = sellFilled.group(2);

                PendingSellCache.remember(item, qty);
                System.out.println("[SkyQoL] Remembered SELL: " + qty + "x " + item);
                return;
            }

            Matcher sellClaim = SELL_CLAIM.matcher(text);
            if (sellClaim.matches()) {
                double coins = parse(sellClaim.group(1));
                int qty = Integer.parseInt(sellClaim.group(2));
                String item = sellClaim.group(3);

                Integer remembered = PendingSellCache.consume(item);
                if (remembered != null && remembered == qty) {
                    BazaarService.recordSell(item, qty, coins / qty);
                    System.out.println("[SkyQoL] Recorded SELL: " + qty + "x " + item + " @ " + (coins / qty));
                }
            }
        });
    }

    private static double parse(String raw) {
        return Double.parseDouble(raw.replace(",", ""));
    }
}
