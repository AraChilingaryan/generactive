package com.aca_disqo.generactive.repository.model;

import com.aca_disqo.generactive.config.Configuration;

public class Generative extends Item {
    private double complexity = 1;

    public Generative() {
    }

    public Generative(Long id, int basePrice, String name) {
        super(id, basePrice, name);
    }

    public double getComplexity() {
        return complexity;
    }

    public void setComplexity(double complexity) {
        this.complexity = complexity;
    }

    @Override
    public double calculatePrice(Configuration configuration) {
        return (int)(getBasePrice() * complexity * configuration.getResolution().getCoefficient());
    }

}
