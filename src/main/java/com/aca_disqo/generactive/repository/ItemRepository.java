package com.aca_disqo.generactive.repository;

import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.repository.model.Item;

import java.util.List;

public interface ItemRepository {

    List<Item> getAll();

    Item getItemById(int id);

    Item create(Item item);

    void deleteById(int id);

    List<Item> findItemByGroup(Group parentGroup);

    Item findHighestPricedItem();
}
