package com.aca_disqo.generactive.repository.model;

import com.aca_disqo.generactive.config.Configuration;

public class Stock extends Item {

    public Stock() {
    }

    public Stock(Long id, int basePrice, String name) {
        super(id, basePrice, name);
    }


    @Override
    public double calculatePrice(Configuration configuration) {
        return getBasePrice() * configuration.getResolution().getCoefficient();
    }
}
