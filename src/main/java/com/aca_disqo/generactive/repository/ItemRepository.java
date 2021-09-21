package com.aca_disqo.generactive.repository;

import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.repository.model.Item;

import java.util.List;

public interface ItemRepository {

    List<Item> getAll();

    Item getItemById(Long id);

    Item create(Item item);

    void deleteById(Long id);

    List<Item> findItemByGroup(Group parentGroup);

    Item findHighestPricedItem();

    List<Item> findItemsByPriceRange(int from, int to);
}
