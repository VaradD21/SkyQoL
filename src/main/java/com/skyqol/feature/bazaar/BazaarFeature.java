package com.skyqol.feature.bazaar;

import com.skyqol.feature.Feature;
import com.skyqol.feature.bazaar.core.BazaarChatListener;

public class BazaarFeature implements Feature {

    @Override
    public String getName() {
        return "Bazaar Profit Tracker";
    }

    @Override
    public void onInit() {
        BazaarChatListener.register();
    }
}
