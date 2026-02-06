package com.skyqol.feature.bazaar.core;

import java.util.HashMap;
import java.util.Map;

public final class PendingSellCache {

    private static final Map<String, Integer> LAST_SELL_QTY = new HashMap<>();

    private PendingSellCache() {}

    public static void remember(String item, int qty) {
        LAST_SELL_QTY.put(item, qty);
    }

    public static Integer consume(String item) {
        return LAST_SELL_QTY.remove(item);
    }
}
