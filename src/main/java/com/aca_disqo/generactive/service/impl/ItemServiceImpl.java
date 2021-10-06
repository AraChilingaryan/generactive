package com.aca_disqo.generactive.service.impl;

import com.aca_disqo.generactive.controller.dto.ItemDTO;
import com.aca_disqo.generactive.repository.ItemRepository;
import com.aca_disqo.generactive.repository.model.Group;
import com.aca_disqo.generactive.repository.model.Item;
import com.aca_disqo.generactive.service.GroupService;
import com.aca_disqo.generactive.service.ItemService;
import com.aca_disqo.generactive.utils.Currency;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final GroupService groupService;
    private final ItemRepository itemRepository;

    public ItemServiceImpl(GroupService groupService1,
                           ItemRepository itemRepository) {
        this.groupService = groupService1;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<Item> findALl() {
        return this.itemRepository.findAll();
    }

    @Override
    public Item findItemBYId(Long id) {
        return this.itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found item by this id"));
    }

    @Override
    public Item create(ItemDTO itemDto) {
        final Item item = buildItemFrom(itemDto);
        Group group = groupService.get(item.getGroup().getId());
        group.addItem(item);
        itemRepository.save(item);
        return item;
    }

    @Override
    public Item update(Long id, ItemDTO itemDTO) {
        final Item item = this.itemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not found item by this id"));;
        item.setBasePrice(itemDTO.getPrice());
        item.setGroup(groupService.get(itemDTO.getGroupId()));
        item.setImageUrl(itemDTO.getImageUrl());
        item.setName(itemDTO.getName());
        return item;
    }

    @Override
    public Boolean deleteById(Long id) {
        itemRepository.deleteById(id);
        return itemRepository.existsById(id);
    }

    @Override
    public List<Item> findItemsByPriceRange(int priceFrom, int priceTo) {
        //return this.itemRepository.findItemsByPriceRange(priceFrom, priceTo);
        return null;
    }

    @Override
    public List<Item> findItemByGroup(Long parentGroupId) {
       // return itemRepository.findItemByGroup(groupService.get(parentGroupId));
        return null;
    }

    @Override
    public Item findHighestPricedItem() {
     //   return itemRepository.findHighestPricedItem();
        return null;
    }



    private Item buildItemFrom(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setGroup(groupService.get(itemDTO.getGroupId()));
        item.setBasePrice(itemDTO.getPrice());
        return item;
    }

    private Currency convertCurrency(String currencyType) {
        return Currency.valueOf(currencyType);
    }
}
