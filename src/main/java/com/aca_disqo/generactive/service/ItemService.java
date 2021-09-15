package com.aca_disqo.generactive.service;

import com.aca_disqo.generactive.controller.dto.ItemDTO;
import com.aca_disqo.generactive.repository.model.Item;

import java.util.List;

public interface ItemService {

    List<Item> findALl();

    Item findItemBYId(Long id);

    Item create(ItemDTO itemDto);

    Item update(Long id, ItemDTO itemDTO);

    void deleteById(Long id);

    List<Item> findItemsByPriceRange(int priceFrom, int priceTo);

    List<Item> findItemByGroup(Long parentGroupId);

    Item findHighestPricedItem();

}
