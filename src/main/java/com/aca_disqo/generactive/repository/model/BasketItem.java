package com.aca_disqo.generactive.repository.model;

import com.aca_disqo.generactive.config.Configuration;

public class BasketItem {

    private final Item item;
    private final Configuration configuration;

    public BasketItem(Item item, Configuration configuration) {
        this.item = item;
        this.configuration = configuration;
    }

    public Item getItem() {
        return item;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public int getPrice() {
        return (int) item.calculatePrice(configuration);
    }

    public void print() {
        System.out.printf("%s (%s) for %d $\n", item.getName(), configuration.getResolution(),
                getPrice());
    }
}

