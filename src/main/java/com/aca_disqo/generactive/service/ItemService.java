package com.aca_disqo.generactive.service;

import com.aca_disqo.generactive.controller.dto.ItemDTO;
import com.aca_disqo.generactive.repository.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> findALl();

    Item findItemBYId(int id);

    Item create(ItemDTO itemDto);

    Item update(int id, ItemDTO itemDTO);

    void deleteById(int id);

    List<Item> findItemsByPriceRange(int priceFrom, int priceTo);

    List<Item> findItemByGroup(int parentGroupId);

    Item findHighestPricedItem();

}
