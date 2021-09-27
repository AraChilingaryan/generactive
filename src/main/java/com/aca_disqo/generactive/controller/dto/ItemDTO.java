package com.aca_disqo.generactive.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDTO {

    private Long id;
    private int price;
    private String name;
    private Long groupId;
    private String imageUrl;

    public ItemDTO() {
    }

    public ItemDTO(Long id, int price, Long groupId) {
        this.id = id;
        this.price = price;
        this.groupId = groupId;
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
                ", groupId=" + groupId +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
