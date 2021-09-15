package com.aca_disqo.generactive.controller.dto;

import com.aca_disqo.generactive.controller.enums.ItemClientType;
import com.aca_disqo.generactive.utils.Resolution;

public class ItemDTO {

    private Long id;
    private int price;
    private String name;
    private String currency;
    private Long groupId;
    private double finalPrice;
    private Resolution resolution;
    private String imageUrl;
    private ItemClientType itemClientType;

    public ItemDTO() {
    }

    public ItemDTO(Long id, int price, String currency, Long groupId,ItemClientType itemClientType) {
        this.id = id;
        this.price = price;
        this.currency = currency;
        this.groupId = groupId;
        this.itemClientType = itemClientType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public ItemClientType getItemClientType() {
        return itemClientType;
    }

    public void setItemClientType(ItemClientType itemClientType) {
        this.itemClientType = itemClientType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public Resolution getResolution() {
        return resolution;
    }

    public void setResolution(Resolution resolution) {
        this.resolution = resolution;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", groupId=" + groupId +
                ", finalPrice=" + finalPrice +
                ", resolution=" + resolution +
                ", imageUrl='" + imageUrl + '\'' +
                ", itemClientType=" + itemClientType +
                '}';
    }
}
