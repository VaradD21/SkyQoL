package com.skyqol.feature.bazaar.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BazaarLedger {

    private final List<BazaarTrade> trades = new ArrayList<>();

    public void record(BazaarTrade trade) {
        trades.add(trade);
    }

    public List<BazaarTrade> getTrades() {
        return Collections.unmodifiableList(trades);
    }

    public double getNetProfit() {
        double profit = 0.0;

        for (BazaarTrade trade : trades) {
            double value = trade.getTotalValue();

            if (trade.getType() == BazaarTradeType.SELL) {
                profit += value;
            } else {
                profit -= value;
            }
        }
        return profit;
    }

    public double getTotalBought() {
        return trades.stream()
                .filter(t -> t.getType() == BazaarTradeType.BUY)
                .mapToDouble(BazaarTrade::getTotalValue)
                .sum();
    }

    public double getTotalSold() {
        return trades.stream()
                .filter(t -> t.getType() == BazaarTradeType.SELL)
                .mapToDouble(BazaarTrade::getTotalValue)
                .sum();
    }
}
