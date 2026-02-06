package com.skyqol.data.log;

import com.skyqol.feature.bazaar.model.BazaarTradeType;

public final class TradeLogEntry {

    public String itemId;
    public int quantity;
    public double pricePerUnit;
    public BazaarTradeType type;
    public long timestamp;

    // Gson needs a no-arg constructor
    public TradeLogEntry() {}

    public TradeLogEntry(
            String itemId,
            int quantity,
            double pricePerUnit,
            BazaarTradeType type,
            long timestamp
    ) {
        this.itemId = itemId;
        this.quantity = quantity;
        this.pricePerUnit = pricePerUnit;
        this.type = type;
        this.timestamp = timestamp;
    }
}
