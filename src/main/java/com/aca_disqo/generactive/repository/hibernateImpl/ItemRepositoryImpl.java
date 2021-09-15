package com.aca_disqo.generactive.repository.hibernateImpl;

import com.aca_disqo.generactive.repository.ItemRepository;
import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.repository.model.Item;

import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {
    @Override
    public List<Item> getAll() {
        return null;
    }

    @Override
    public Item getItemById(Long id) {
        return null;
    }

    @Override
    public Item create(Item item) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Item> findItemByGroup(Group parentGroup) {
        return null;
    }

    @Override
    public Item findHighestPricedItem() {
        return null;
    }

    @Override
    public List<Item> findItemsByPriceRange(int from, int to) {
        return null;
    }
}
