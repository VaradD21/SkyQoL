package com.skyqol.command;

import com.skyqol.core.HypixelContext;
import com.skyqol.feature.bazaar.core.BazaarService;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;

public final class SkyQoLCommand {

        public static void register() {
                ClientCommandRegistrationCallback.EVENT.register((dispatcher, registryAccess) -> {

                        dispatcher.register(
                                        literal("sqol")

                                                        // /sqol buy <item> <qty> <price>
                                                        .then(literal("buy")
                                                                        .then(argument("item",
                                                                                        StringArgumentType.word())
                                                                                        .then(argument("qty",
                                                                                                        IntegerArgumentType
                                                                                                                        .integer(1))
                                                                                                        .then(argument("price",
                                                                                                                        DoubleArgumentType
                                                                                                                                        .doubleArg(0))
                                                                                                                        .executes(ctx -> {
                                                                                                                                String item = StringArgumentType
                                                                                                                                                .getString(ctx, "item");
                                                                                                                                int qty = IntegerArgumentType
                                                                                                                                                .getInteger(ctx, "qty");
                                                                                                                                double price = DoubleArgumentType
                                                                                                                                                .getDouble(ctx, "price");

                                                                                                                                BazaarService.recordBuy(
                                                                                                                                                item,
                                                                                                                                                qty,
                                                                                                                                                price);

                                                                                                                                double totalValue = price
                                                                                                                                                * qty;
                                                                                                                                System.out.println(
                                                                                                                                                "[SkyQoL] BUY: " + qty
                                                                                                                                                                + "x "
                                                                                                                                                                + item
                                                                                                                                                                + " @ "
                                                                                                                                                                + price
                                                                                                                                                                + " each = -"
                                                                                                                                                                + totalValue
                                                                                                                                                                + " total");

                                                                                                                                send("§aRecorded BUY: "
                                                                                                                                                + qty
                                                                                                                                                + "x "
                                                                                                                                                + item
                                                                                                                                                + " @ "
                                                                                                                                                + price
                                                                                                                                                + " each");
                                                                                                                                send("§7Total Spent: §c-"
                                                                                                                                                + String.format("%.2f",
                                                                                                                                                                totalValue)
                                                                                                                                                + " coins");
                                                                                                                                return 1;
                                                                                                                        })))))

                                                        // /sqol sell <item> <qty> <price>
                                                        .then(literal("sell")
                                                                        .then(argument("item",
                                                                                        StringArgumentType.word())
                                                                                        .then(argument("qty",
                                                                                                        IntegerArgumentType
                                                                                                                        .integer(1))
                                                                                                        .then(argument("price",
                                                                                                                        DoubleArgumentType
                                                                                                                                        .doubleArg(0))
                                                                                                                        .executes(ctx -> {
                                                                                                                                String item = StringArgumentType
                                                                                                                                                .getString(ctx, "item");
                                                                                                                                int qty = IntegerArgumentType
                                                                                                                                                .getInteger(ctx, "qty");
                                                                                                                                double price = DoubleArgumentType
                                                                                                                                                .getDouble(ctx, "price");

                                                                                                                                BazaarService.recordSell(
                                                                                                                                                item,
                                                                                                                                                qty,
                                                                                                                                                price);

                                                                                                                                double totalValue = price
                                                                                                                                                * qty;
                                                                                                                                System.out.println(
                                                                                                                                                "[SkyQoL] SELL: "
                                                                                                                                                                + qty
                                                                                                                                                                + "x "
                                                                                                                                                                + item
                                                                                                                                                                + " @ "
                                                                                                                                                                + price
                                                                                                                                                                + " each = +"
                                                                                                                                                                + totalValue
                                                                                                                                                                + " total");

                                                                                                                                send("§aRecorded SELL: "
                                                                                                                                                + qty
                                                                                                                                                + "x "
                                                                                                                                                + item
                                                                                                                                                + " @ "
                                                                                                                                                + price
                                                                                                                                                + " each");
                                                                                                                                send("§7Total Earned: §a+"
                                                                                                                                                + String.format("%.2f",
                                                                                                                                                                totalValue)
                                                                                                                                                + " coins");
                                                                                                                                return 1;
                                                                                                                        })))))

                                                        // /sqol profit
                                                        .then(literal("profit")
                                                                        .executes(ctx -> {
                                                                                double totalBought = BazaarService
                                                                                                .getLedger()
                                                                                                .getTotalBought();
                                                                                double totalSold = BazaarService
                                                                                                .getLedger()
                                                                                                .getTotalSold();
                                                                                double profit = BazaarService
                                                                                                .getLedger()
                                                                                                .getNetProfit();

                                                                                String profitColor = profit >= 0 ? "§a"
                                                                                                : "§c";
                                                                                String profitSign = profit >= 0 ? "+"
                                                                                                : "";

                                                                                send("§6§l[SkyQoL Profit Report]");
                                                                                send("§7Total Spent (Buys): §c-"
                                                                                                + String.format("%.2f",
                                                                                                                totalBought)
                                                                                                + " coins");
                                                                                send("§7Total Earned (Sells): §a+"
                                                                                                + String.format("%.2f",
                                                                                                                totalSold)
                                                                                                + " coins");
                                                                                send("§7━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                                                                                send("§7Net Profit: " + profitColor
                                                                                                + "§l" + profitSign
                                                                                                + String.format("%.2f",
                                                                                                                profit)
                                                                                                + " coins");
                                                                                return 1;
                                                                        }))

                                                        // /sqol status (debug command)
                                                        .then(literal("status")
                                                                        .executes(ctx -> {
                                                                                boolean onHypixel = HypixelContext
                                                                                                .isOnHypixel();
                                                                                boolean inSkyBlock = HypixelContext
                                                                                                .isInSkyBlock();

                                                                                send("§6§l[SkyQoL Status]");
                                                                                send("§7On Hypixel: " + (onHypixel
                                                                                                ? "§a✔"
                                                                                                : "§c✖"));
                                                                                send("§7In SkyBlock: " + (inSkyBlock
                                                                                                ? "§a✔"
                                                                                                : "§c✖"));
                                                                                send("§7Trades Recorded: §e"
                                                                                                + BazaarService.getLedger()
                                                                                                                .getTrades()
                                                                                                                .size());
                                                                                return 1;
                                                                        })));
                });
        }

        private static void send(String msg) {
                MinecraftClient client = MinecraftClient.getInstance();
                if (client.player != null) {
                        client.player.sendMessage(Text.literal(msg), false);
                }
        }
}
