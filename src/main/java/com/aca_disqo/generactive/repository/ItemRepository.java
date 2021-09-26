package com.aca_disqo.generactive.repository;

import com.aca_disqo.generactive.controller.dto.ItemDTO;
import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.repository.model.Item;

import java.util.List;

public interface ItemRepository {

    List<Item> getAll();

    Item getItemById(Long id);

    Item save(Item item);

    boolean deleteById(Long id);

    List<Item> findItemByGroup(Group parentGroup);

    Item findHighestPricedItem();

    List<Item> findItemsByPriceRange(int from, int to);

    Item update(Long id, Item item);
}
