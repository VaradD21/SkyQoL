package com.skyqol.feature.bazaar.model;

public final class BazaarTrade {

    private final String itemId;
    private final int quantity;
    private final double pricePerUnit;
    private final BazaarTradeType type;
    private final long timestamp;

    public BazaarTrade(
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

    public String getItemId() {
        return itemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public BazaarTradeType getType() {
        return type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getTotalValue() {
        return pricePerUnit * quantity;
    }
}
