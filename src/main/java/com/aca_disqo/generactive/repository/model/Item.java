package com.aca_disqo.generactive.repository.model;

import com.aca_disqo.generactive.config.Configuration;
import com.aca_disqo.generactive.utils.Currency;

public abstract class Item {

    private Long id;
    private int basePrice;
    private String name;
    private String imageUrl;
    private Group group;
    private Currency currency;

    public Item() {
    }

    public Item(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Item(Long id, int basePrice, String name) {
        this.id = id;
        this.basePrice = basePrice;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public abstract double calculatePrice(Configuration configuration);

    public void print() {
        System.out.printf("ITEM(%s) - id: {%d} {%s} {%d}%n",
                this.getClass().getSimpleName(), id, name, basePrice);
    }
}
