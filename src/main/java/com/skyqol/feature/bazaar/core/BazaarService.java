package com.skyqol.feature.bazaar.core;

import com.skyqol.data.log.TradeLogEntry;
import com.skyqol.data.log.TradeLogStore;
import com.skyqol.feature.bazaar.model.BazaarTrade;
import com.skyqol.feature.bazaar.model.BazaarTradeType;
import com.skyqol.feature.bazaar.model.BazaarLedger;

public final class BazaarService {

    private static final BazaarLedger LEDGER = new BazaarLedger();

    private BazaarService() {
    }

    public static void recordBuy(String itemId, int qty, double price) {
        long ts = System.currentTimeMillis();

        LEDGER.record(new BazaarTrade(
                itemId, qty, price, BazaarTradeType.BUY, ts));

        TradeLogStore.append(new TradeLogEntry(
                itemId, qty, price, BazaarTradeType.BUY, ts));
    }

    public static void recordSell(String itemId, int qty, double price) {
        long ts = System.currentTimeMillis();

        LEDGER.record(new BazaarTrade(
                itemId, qty, price, BazaarTradeType.SELL, ts));

        TradeLogStore.append(new TradeLogEntry(
                itemId, qty, price, BazaarTradeType.SELL, ts));
    }

    public static BazaarLedger getLedger() {
        return LEDGER;
    }
}
