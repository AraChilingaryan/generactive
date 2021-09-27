package com.aca_disqo.generactive.repository.model;

import com.aca_disqo.generactive.config.Configuration;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_id_seq")
    @SequenceGenerator(name = "item_id_seq", sequenceName = "item_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "base_price")
    private int basePrice;

    @Column(name = "name")
    private String name;

    @Transient
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonBackReference
    private Group group;

    public Item() {
    }

    public Item(long id, int basePrice, String name) {
        this.id = id;
        this.basePrice = basePrice;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setGroup(Group group) {
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public int calculatePrice(Configuration configuration) {
        // FIXME
        return basePrice;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
